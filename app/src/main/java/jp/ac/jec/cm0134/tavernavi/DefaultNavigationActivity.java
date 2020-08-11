package jp.ac.jec.cm0134.tavernavi;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import jp.ac.jec.cm0134.tavernavi.ui.defaultcoupon.CouponFragment;
import jp.ac.jec.cm0134.tavernavi.ui.defaultmain.DefaultFragment;
import jp.ac.jec.cm0134.tavernavi.ui.defaultmap.MapFragment;

public class DefaultNavigationActivity extends AppCompatActivity {

    // region Properties
    private DefaultFragment defaultFragment;
    private CouponFragment couponFragment;
    private MapFragment mapFragment;

    private String title;
    private String image;
    private String phoneNumber;
    private String standardInfo;
    private String pr;
    private String defaultInfo;
    private String coupon_url;
    private double latitude;
    private double longitude;
    // endregion Properties

    // region Override
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_navigation);

        final BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        final AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_default, R.id.navigation_coupon, R.id.navigation_map)
                .build();
        final NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_default:
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.nav_host_fragment, defaultFragment);
                        transaction.commit();
                        return true;
                    case R.id.navigation_coupon:
                        FragmentTransaction cTransaction = getSupportFragmentManager().beginTransaction();
                        cTransaction.replace(R.id.nav_host_fragment, couponFragment);
                        cTransaction.commit();
                        return true;
                    case R.id.navigation_map:
                        FragmentTransaction mTransaction = getSupportFragmentManager().beginTransaction();
                        mTransaction.replace(R.id.nav_host_fragment, mapFragment);
                        mTransaction.commit();
                        return true;
                }
                return false;
            }
        });

        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        image = intent.getStringExtra("image");
        phoneNumber = intent.getStringExtra("tel");
        standardInfo = intent.getStringExtra("opentime") + "\n" + intent.getStringExtra("holiday");
        pr = intent.getStringExtra("pr_short") + "\n" + intent.getStringExtra("pr_long");
//        defaultInfo = intent.getStringExtra()
        coupon_url = intent.getStringExtra("coupon_url");
        latitude = intent.getDoubleExtra("latitude",35);
        longitude = intent.getDoubleExtra("longitude", 139);

        defaultFragment = new DefaultFragment();
        couponFragment = new CouponFragment();
        mapFragment = new MapFragment();
        setFragment();
    }
    // endregion Override

    // region Private Function
    private void setFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment, defaultFragment);
        transaction.commit();
        defaultFragment.setTitle(this.title);
        defaultFragment.setImage(this.image);
        defaultFragment.setContents1(this.standardInfo);
        defaultFragment.setContents2(this.pr);
//        defaultFragment.setContents3(this.defaultInfo);
    }
    // endregion Private Function
}
