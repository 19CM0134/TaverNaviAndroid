package jp.ac.jec.cm0134.tavernavi.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import jp.ac.jec.cm0134.tavernavi.R;

public class PhoneFragment extends Fragment {

    // region Properties
    private final static String KEY_PhoneNumber = "key_phone_number";
    private String tel;
    // endregion Properties

    // region Override
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_phone, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setButton(view);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();

        if (args != null) {
            tel = args.getString(KEY_PhoneNumber);
        }
    }
    // endregion Override

    // region Initializer
    @CheckResult
    public static PhoneFragment createInstance(String tel) {
        PhoneFragment fragment = new PhoneFragment();
        Bundle args = new Bundle();
        args.putString(KEY_PhoneNumber, tel);
        fragment.setArguments(args);

        return fragment;
    }
    // endregion Initializer

    // region Private Function
    private void setButton(View view) {
        Button button = (Button)view.findViewById(R.id.btnPhone);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAlert();
            }
        });
    }

    // Alert表示
    private void showAlert() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.MyAlertDialogStyle);
        builder.setMessage("発信します")
                .setTitle(this.tel)
                .setPositiveButton("発信", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getActivity(), "開発段階の為、架空の電話番号を入力しています", Toast.LENGTH_LONG).show();
                        Uri uri = Uri.parse("tel:000-0000-0000");
                        Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("キャンセル", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        builder.show();
    }
    // endregion Private Function
}
