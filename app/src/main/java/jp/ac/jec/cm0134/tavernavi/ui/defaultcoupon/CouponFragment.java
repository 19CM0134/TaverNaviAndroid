package jp.ac.jec.cm0134.tavernavi.ui.defaultcoupon;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import jp.ac.jec.cm0134.tavernavi.R;

public class CouponFragment extends Fragment {

    private CouponViewModel couponViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        couponViewModel =
                ViewModelProviders.of(this).get(CouponViewModel.class);
        return inflater.inflate(R.layout.fragment_navigation_coupon, container, false);
    }

}
