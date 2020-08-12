package jp.ac.jec.cm0134.tavernavi;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import jp.ac.jec.cm0134.tavernavi.fragment.GenreListFragment;
import jp.ac.jec.cm0134.tavernavi.fragment.SearchConditionFragment;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    // region Properties
    // Map & Location
    private GoogleMap map;
    private SupportMapFragment mapFragment;
    private final int REQUEST_PERMISSION = 1000;
    private LocationManager locationManager;
    private LatLng latLng;
    private String latitude;
    private String longitude;
    private static final int MinTime = 1000;
    private static final float MinDistance = 50;
    // Fragment
    private SearchConditionFragment searchConditionFragment;
    private GenreListFragment genreListFragment;
    // endregion Properties

    // region Override
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setFragment();
        // LocationManagerインスタンス生成
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        // Android 6, API 23以上でパーミッションの確認
        if (Build.VERSION.SDK_INT >= 23) {
            checkPermission();
        } else {
            startGPS();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        map.addMarker(new MarkerOptions().position(latLng).title("現在位置"));
        map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));

        searchConditionFragment.setLatitude(this.latitude);
        searchConditionFragment.setLongitude(this.longitude);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        // 使用が許可された時
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startGPS();
        } else {
            // それでも拒否された時
            showAlert();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (locationManager != null) {
            Log.d("LocationActivity", "locationManager.removeUpdates");
            // updateを止める
            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            locationManager.removeUpdates(this);
        }
    }

    @Override
    public void onLocationChanged(Location location) {

        latitude = String.valueOf(location.getLatitude());
        longitude = String.valueOf(location.getLongitude());

        latLng = new LatLng(location.getLatitude(), location.getLongitude());
        Log.d("TaverNavi", "latitude : " + location.getLatitude() + " & longitude : " + location.getLongitude());
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopGPS();
    }

    @Override
    public void onProviderEnabled(String s) {
    }

    @Override
    public void onProviderDisabled(String s) {
    }
    // endregion Override

    // region Private Function
    private void setFragment() {
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapFragment);
        searchConditionFragment = (SearchConditionFragment)getSupportFragmentManager()
                .findFragmentById(R.id.searchConditionFragment);
        genreListFragment = (GenreListFragment)getSupportFragmentManager()
                .findFragmentById(R.id.genreFragment);
    }

    // 現在位置取得の許可を求める
    private void requestLocationPermission() {
        ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                REQUEST_PERMISSION);
    }

    // GPS測位開始
    private void startGPS() {
        Log.d("LocationActivity", "GPS Enable");
        final boolean gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!gpsEnabled) {
            // GPSを設定するように促す
            enableLocationSettings();
        }

        if (locationManager != null) {
            Log.d("LocationActivity", "locationManager.requestLocationUpdates");
            try {
                // minTime = 1000msec, minDistance = 50m
                if (ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                        MinTime, MinDistance, this);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "例外が発生、位置情報の取得を許可していますか？", Toast.LENGTH_SHORT).show();
            }
        }
        super.onResume();
    }

    // GPS測位終了
    private void stopGPS() {
        if (locationManager != null) {
            Log.d("LocationActivity", "onStop()");
            // update を止める
            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION) !=
                    PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_COARSE_LOCATION) !=
                            PackageManager.PERMISSION_GRANTED) {
                return;
            }
            locationManager.removeUpdates(this);
        }
    }

    private void enableLocationSettings() {
        Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(settingsIntent);
    }

    // Alert表示
    private void showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("現在地の取得が拒否されました。")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        builder.show();
    }
    // endregion Private Function

    // region Public Function
    // 位置情報許可の確認
    public void checkPermission() {
        // 既に許可している
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            startGPS();
        } else {
            // 拒否していた場合
            requestLocationPermission();
        }
    }
    // endregion Public Function
}
