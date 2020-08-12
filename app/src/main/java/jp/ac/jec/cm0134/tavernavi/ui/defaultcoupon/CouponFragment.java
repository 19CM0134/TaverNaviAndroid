package jp.ac.jec.cm0134.tavernavi.ui.defaultcoupon;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import jp.ac.jec.cm0134.tavernavi.R;

public class CouponFragment extends Fragment {

    // region Properties
    private CouponViewModel couponViewModel;
    private WebView webView;
    private TextView textView;
    private String couponUrl;
    // endregion Properties

    // region Setter
    public void setCouponUrl(String couponUrl) {
        this.couponUrl = couponUrl;
    }
    // endregion Setter

    // region Override
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        couponViewModel =
                ViewModelProviders.of(this).get(CouponViewModel.class);
        return inflater.inflate(R.layout.fragment_navigation_coupon, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        webView = view.findViewById(R.id.nav_coupon_web);
        textView = view.findViewById(R.id.textView4);
        webView.setVisibility(View.INVISIBLE);
        textView.setVisibility(View.INVISIBLE);

        if (couponUrl.equals("")) {
            showTextView();
        } else {
            showWebView();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        webView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        webView.onPause();
    }

    // endregion Override

    // region Private Function
    private void showWebView() {
        webView.setVisibility(View.VISIBLE);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(couponUrl);
    }

    private void showTextView() {
        textView.setVisibility(View.VISIBLE);
        textView.setText("取得できるクーポンがありません");
    }
    // endregion Private Function
}
