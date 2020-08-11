package jp.ac.jec.cm0134.tavernavi.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import jp.ac.jec.cm0134.tavernavi.R;
import jp.ac.jec.cm0134.tavernavi.RestaurantsListActivity;

public class GenreListFragment extends Fragment implements View.OnClickListener {

    // TODO: Buttonのイベント

    // region Properties
    private Button btnGenre;
    // endregion Properties

    // region Override
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_genre_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setButton(view);
        setImageButton(view);
    }

    @Override
    public void onClick(View view) {
//        Intent intent = new Intent(getActivity(), );
//        startActivity(intent);

    }
    // endregion Override

    // region Private Function
    private void setButton(View view) {
        btnGenre = view.findViewById(R.id.btnGenre);
        btnGenre.setOnClickListener(this);
    }

    private void setImageButton(View view) {
        ImageButton imageButton = view.findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new ImageButtonClickListener());

        ImageButton imageButton2 = view.findViewById(R.id.imageButton2);
        imageButton2.setOnClickListener(new ImageButtonClickListener());

        ImageButton imageButton3 = view.findViewById(R.id.imageButton3);
        imageButton3.setOnClickListener(new ImageButtonClickListener());

        ImageButton imageButton4 = view.findViewById(R.id.imageButton4);
        imageButton4.setOnClickListener(new ImageButtonClickListener());
    }
    // endregion Private Function

    class ImageButtonClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {

            Intent intent = new Intent(getActivity(), RestaurantsListActivity.class);
            intent.putExtra("area", "指定なし");
            intent.putExtra("radius", "指定なし");
            intent.putExtra("minBudget", "下限なし");
            intent.putExtra("maxBudget", "上限なし");
            intent.putExtra("day", "夜");
            intent.putExtra("creditCard", 0);
            intent.putExtra("eMoney", 0);

            if (view.getId() == R.id.imageButton) {
                intent.putExtra("category", "RSFST09000");
            } else if (view.getId() == R.id.imageButton2) {
                intent.putExtra("category", "RSFST03000");
            } else if (view.getId() == R.id.imageButton3) {
                intent.putExtra("category", "RSFST06000");
            } else if (view.getId() == R.id.imageButton4) {
                intent.putExtra("category", "RSFST08000");
            }
            startActivity(intent);
        }
    }
}
