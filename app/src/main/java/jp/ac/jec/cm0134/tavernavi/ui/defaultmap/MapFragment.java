package jp.ac.jec.cm0134.tavernavi.ui.defaultmap;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import jp.ac.jec.cm0134.tavernavi.R;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    // region Properties
    private MapViewModel mapViewModel;
    private MapView mapView;
    private GoogleMap map;
    private String restaurantName;
    private String address;
    private String latitude;
    private String longitude;
    // endregion Properties

    // region Setter
    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
    // endregion Setter

    // region Override
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mapViewModel =
                ViewModelProviders.of(this).get(MapViewModel.class);
        return inflater.inflate(R.layout.fragment_navigation_map, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mapView = view.findViewById(R.id.navMapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);

        setTextView(view);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        double lat = Double.parseDouble(this.latitude);
        double lng = Double.parseDouble(this.longitude);
        LatLng latLng = new LatLng(lat, lng);
        Log.d("MapFragment", "latitude : " +lat + " & longitude : " + lng);
        map.addMarker(new MarkerOptions().position(latLng).title(this.restaurantName));
        map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
    }
    // endregion Override

    // region Private Function
    private void setTextView(View view) {
        TextView textName = view.findViewById(R.id.map_restaurant_name);
        textName.setText(this.restaurantName);

        TextView textAddress = view.findViewById(R.id.map_restaurant_address);
        textAddress.setText(this.address);
    }
    // endregion Private Function
}
