package jp.ac.jec.cm0134.tavernavi.ui.defaultmain;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import com.squareup.picasso.Picasso;

import jp.ac.jec.cm0134.tavernavi.R;
import jp.ac.jec.cm0134.tavernavi.fragment.AccordionFragment;
import jp.ac.jec.cm0134.tavernavi.fragment.PhoneFragment;

public class DefaultFragment extends Fragment {

    // region Properties
    private DefaultViewModel defaultViewModel;
    private ImageView imageView;
    private String title;
    private String image;
    private String phoneNumber;
    private String contents1;
    private String contents2;
    private String contents3;
    // endregion Properties

    // region Setter
    public void setTitle(String title) {
        this.title = title;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public void setContents1(String contents1) {
        this.contents1 = contents1;
    }
    public void setContents2(String contents2) {
        this.contents2 = contents2;
    }
    public void setContents3(String contents3) {
        this.contents3 = contents3;
    }
    // endregion Setter

    // region Override
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        defaultViewModel =
                ViewModelProviders.of(this).get(DefaultViewModel.class);

        View view = inflater.inflate(R.layout.fragment_navigation_default, container, false);
        imageView = view.findViewById(R.id.default_restaurant_imageView);

        final ViewTreeObserver.OnGlobalLayoutListener layoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                Log.d("DefaultFragment","imageView : " + imageView.getWidth() + " : " + imageView.getHeight());
                Picasso.get().load(image).resize(imageView.getWidth(), imageView.getHeight()).centerInside().into(imageView);

//                imageView.getViewTreeObserver().removeOnGlobalLayoutListener(layoutListener);
            }
        };
        imageView.getViewTreeObserver().addOnGlobalLayoutListener(layoutListener);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setFragment();
        setTitle(view);
    }
    // endregion Override

    // region Private Function
    private void setFragment() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.add(R.id.fragment, PhoneFragment.createInstance(this.phoneNumber));
        transaction.add(R.id.fragment, AccordionFragment.createInstance("基本情報", this.contents1));
        transaction.add(R.id.fragment, AccordionFragment.createInstance("店舗詳細", this.contents2));
        transaction.add(R.id.fragment, AccordionFragment.createInstance("詳細情報", this.contents3));
        transaction.commit();
    }

    private void setTitle(View view) {
        TextView title = (TextView)view.findViewById(R.id.default_restaurant_name);
        title.setText(this.title);
    }
    // endregion Private Function
}
