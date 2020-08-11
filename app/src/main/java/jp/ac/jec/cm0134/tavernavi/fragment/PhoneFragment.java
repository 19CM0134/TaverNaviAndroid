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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import jp.ac.jec.cm0134.tavernavi.R;

public class PhoneFragment extends Fragment {

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
    // endregion Override

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
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("発信します")
                .setTitle("000-000-0000")
                .setPositiveButton("発信", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getActivity(), "000-000-0000", Toast.LENGTH_SHORT).show();
                        Uri uri = Uri.parse("tel:080-6866-9301");
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
