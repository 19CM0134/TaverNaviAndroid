package jp.ac.jec.cm0134.tavernavi.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.CheckResult;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import jp.ac.jec.cm0134.tavernavi.R;

public class AccordionFragment extends Fragment {

    // region Properties
    private ImageButton ibtnArrow;
    private TextView txtTitle;
    private LinearLayout linearLayout;
    private TextView txtContents;
    // 情報を保持
    private final static String KEY_NAME = "key_name";
    private final static String KEY_CONTENTS = "key_contents";
    private String title;
    private String content;
    // アコーディオンのスイッチ
    private int num = 0;
    // endregion Properties

    // region Override
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_accordion, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setText(view);
        setAccordion(view);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();

        if (args != null) {
            title = args.getString(KEY_NAME);
            content = args.getString(KEY_CONTENTS);
        }
    }
    // endregion Override

    // region Initializer
    @CheckResult
    public static AccordionFragment createInstance(String name, String contents) {

        AccordionFragment fragment = new AccordionFragment();
        Bundle args = new Bundle();
        args.putString(KEY_NAME, name);
        args.putString(KEY_CONTENTS, contents);
        fragment.setArguments(args);

        return fragment;
    }
    // endregion Initializer

    // region Private Function
    private void setText(View view) {
        txtTitle = (TextView)view.findViewById(R.id.txtAccordion);
        txtTitle.setText(title);

        txtContents = (TextView)view.findViewById(R.id.txtContents);
        txtContents.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    private void setAccordion(View view) {
        ibtnArrow = view.findViewById(R.id.ibtnArrow);
        linearLayout = view.findViewById(R.id.contents_area_linear);
        linearLayout.setMinimumHeight(0);
        // 展開ボタン押下時
        ibtnArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (num == 0) {
                    // 内容エリアが閉じている時
                    ibtnArrow.setImageResource(R.drawable.arrow2);
                    txtContents.setText(content);
                    txtContents.setHeight(300);
                    num++;
                } else {
                    // 内容エリアが開いている時
                    ibtnArrow.setImageResource(R.drawable.arrow1);
                    txtContents.setHeight(linearLayout.getMinimumHeight());
                    num--;
                }
            }
        });
    }
    // endregion Private Function
}
