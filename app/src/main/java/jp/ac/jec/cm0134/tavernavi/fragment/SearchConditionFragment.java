package jp.ac.jec.cm0134.tavernavi.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import jp.ac.jec.cm0134.tavernavi.R;
import jp.ac.jec.cm0134.tavernavi.RestaurantsListActivity;

public class SearchConditionFragment extends Fragment implements View.OnClickListener {

    // region Properties
    private Spinner areaSpinner;
    private Spinner radiusSpinner;
    private Spinner minBudgetSpinner;
    private Spinner maxBudgetSpinner;
    private Spinner daySpinner;
    private CheckBox creditCardCheckBox;
    private CheckBox eMoneyCheckBox;
    private Button searchButton;

    // 各項目の選択された値を保持する変数
    private String areaItem;
    private String radiusItem;
    private String minBudItem;
    private String maxBudItem;
    private String dayItem;
    private int check1;
    private int check2;

    private String latitude;
    private String longitude;
    // endregion Properties

    // region Setter
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
    // endregion Setter

    // region Override
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_search_condition, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setSpinner(view);
        setCheckBox(view);
        setButton(view);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getActivity(), RestaurantsListActivity.class);
        intent.putExtra("area", areaItem);
        intent.putExtra("radius", radiusItem);
        intent.putExtra("minBudget", minBudItem);
        intent.putExtra("maxBudget", maxBudItem);
        intent.putExtra("day", dayItem);
        intent.putExtra("creditCard", check1);
        intent.putExtra("eMoney", check2);
        if (areaItem.equals("現在地")) {
            intent.putExtra("latitude", this.latitude);
            intent.putExtra("longitude", this.longitude);
        }
        intent.putExtra("category", "");
        startActivity(intent);
    }
    // endregion



    // region Private Function
    private void setSpinner(View view) {
        areaSpinner = view.findViewById(R.id.areaSpinner);
        ArrayAdapter areaAdapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.areaSpinner, R.layout.custom_spinner);
        areaAdapter.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        areaSpinner.setAdapter(areaAdapter);
        areaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Spinner spinner = (Spinner)adapterView;
                 areaItem = (String)spinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                areaItem = "指定なし";
            }
        });

        radiusSpinner = view.findViewById(R.id.radiusSpinner);
        final ArrayAdapter radiusAdapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.radiusSpinner, R.layout.custom_spinner);
        radiusAdapter.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        radiusSpinner.setAdapter(radiusAdapter);
        radiusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Spinner spinner = (Spinner)adapterView;
                radiusItem = (String)spinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                radiusItem = "指定なし";
            }
        });

        minBudgetSpinner = view.findViewById(R.id.minBudgetSpinner);
        ArrayAdapter minBudgetAdapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.minBudgetSpinner, R.layout.custom_spinner);
        minBudgetAdapter.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        minBudgetSpinner.setAdapter(minBudgetAdapter);
        minBudgetSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Spinner spinner = (Spinner)adapterView;
                minBudItem = (String)spinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                minBudItem = "下限なし";
            }
        });

        maxBudgetSpinner = view.findViewById(R.id.maxBudgetSpinner);
        final ArrayAdapter maxBudgetAdapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.maxBudgetSpinner, R.layout.custom_spinner);
        maxBudgetAdapter.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        maxBudgetSpinner.setAdapter(maxBudgetAdapter);
        maxBudgetSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Spinner spinner = (Spinner)adapterView;
                maxBudItem = (String)spinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                maxBudItem = "上限なし";
            }
        });

        daySpinner = view.findViewById(R.id.daySpinner);
        final ArrayAdapter dayAdapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.daySpinner, R.layout.custom_spinner);
        dayAdapter.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        daySpinner.setAdapter(dayAdapter);
        daySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Spinner spinner = (Spinner)adapterView;
                dayItem = (String)spinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                dayItem = "夜";
            }
        });

    }

    private void setCheckBox(View view) {
        creditCardCheckBox = view.findViewById(R.id.card);
        creditCardCheckBox.setChecked(false);
        creditCardCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (creditCardCheckBox.isChecked()) {
                    check1 = 1;
                } else {
                    check1 = 0;
                }
            }
        });
        eMoneyCheckBox = view.findViewById(R.id.eMoney);
        eMoneyCheckBox.setChecked(false);
        eMoneyCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (eMoneyCheckBox.isChecked()) {
                    check2 = 1;
                } else {
                    check2 = 0;
                }
            }
        });
    }

    private void setButton(View view) {
        searchButton = view.findViewById(R.id.searchButton);
        searchButton.setOnClickListener(this);
    }
    // endregion Private Function
}
