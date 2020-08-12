package jp.ac.jec.cm0134.tavernavi;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class RestaurantsListActivity extends AppCompatActivity {

    // region Properties
    protected RowModelAdapter adapter;
    private ListView list;
    private TextView txtHitPerPage;
    private String key = "764f2d74b71fbdc9df18eca86436379e";
    // endregion Properties

    // region Setter
    public void setCount(int count) {
        txtHitPerPage = findViewById(R.id.txtHitCount);
        txtHitPerPage.setText(count + "件表示");
    }
    // endregion Setter

    // region Override
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants_list);

        adapter = new RowModelAdapter(this);
        list = findViewById(R.id.resultList);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(RestaurantsListActivity.this, DefaultNavigationActivity.class);
                RestaurantData item = new RestaurantData();
                        item = adapter.getItem(i);
                        intent.putExtra("title"     , item.getName());
                        intent.putExtra("image"     , item.getShop_image1());
                        intent.putExtra("holiday"   , item.getHoliday());
                        intent.putExtra("tel"       , item.getTel());
                        intent.putExtra("address"   , item.getAddress());
                        intent.putExtra("opentime"  , item.getOpentime());
                        intent.putExtra("pr_short"  , item.getPr_short());
                        intent.putExtra("pr_long"   , item.getPr_long());
                        intent.putExtra("coupon_url", item.getMobile());
                        intent.putExtra("latitude"  , item.getLatitude());
                        intent.putExtra("longitude" , item.getLongitude());

                        startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        getJsonData();
    }
    // endregion Override

    // region Private Function
    private void getJsonData() {
        Intent intent = getIntent();
        Uri.Builder uriBuilder = new Uri.Builder();
        uriBuilder.scheme("https");
        uriBuilder.authority("api.gnavi.co.jp");
        uriBuilder.path("RestSearchAPI/v3/");
        uriBuilder.appendQueryParameter("keyid", key);

        if (intent.getStringExtra("area").equals("指定なし")) {
            uriBuilder.appendQueryParameter("area", "AREA110");
        } else {
            switch (intent.getStringExtra("area")) {
                case "現在地":
                    uriBuilder.appendQueryParameter("latitude", intent.getStringExtra("latitude"));
                    uriBuilder.appendQueryParameter("longitude", intent.getStringExtra("longitude"));

                    if (intent.getStringExtra("radius").equals("指定なし") || intent.getStringExtra("radius").equals("500m")) {
                        uriBuilder.appendQueryParameter("range", "2");
                    } else {
                        switch (intent.getStringExtra("radius")) {
                            case "300m":
                                uriBuilder.appendQueryParameter("range", "1");
                                break;
                            case "1,000m":
                                uriBuilder.appendQueryParameter("range", "3");
                                break;
                            case "2,000m":
                                uriBuilder.appendQueryParameter("range", "4");
                                break;
                            case "3,000m":
                                uriBuilder.appendQueryParameter("range", "5");
                                break;
                        }
                    }
                    break;
                case "東京都":
                    uriBuilder.appendQueryParameter("pref", "PREF13");
                    break;
                case "神奈川県":
                    uriBuilder.appendQueryParameter("pref", "PREF14");
                    break;
                case "埼玉県":
                    uriBuilder.appendQueryParameter("pref", "PREF11");
                    break;
                case "千葉県":
                    uriBuilder.appendQueryParameter("pref", "PREF12");
                    break;
                case "群馬県":
                    uriBuilder.appendQueryParameter("pref", "PREF10");
                    break;
                case "栃木県":
                    uriBuilder.appendQueryParameter("pref", "PREF09");
                    break;
                case "茨城県":
                    uriBuilder.appendQueryParameter("pref", "PREF08");
                    break;
            }
        }

        if (intent.getStringExtra("day").equals("昼")) {
            uriBuilder.appendQueryParameter("lunch", "1");
        }

        if (intent.getIntExtra("creditCard", 0) == 1) {
            uriBuilder.appendQueryParameter("card", "1");
        }

        if (intent.getIntExtra("eMoney", 0) == 1) {
            uriBuilder.appendQueryParameter("e_money", "1");
        }

        if (!intent.getStringExtra("category").equals("")) {
            uriBuilder.appendQueryParameter("category_l", intent.getStringExtra("category"));
        }
        uriBuilder.appendQueryParameter("hit_per_page", "50");
        AsyncHttpRequest request = new AsyncHttpRequest(this);
        request.execute(uriBuilder);
        Log.d("URL", String.valueOf(uriBuilder));
    }
    // endregion Private Function

    // region Inner Class
    class RowModelAdapter extends ArrayAdapter<RestaurantData> {

        RowModelAdapter(Context context) {
            super(context, R.layout.result_list_row_item);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final RestaurantData item = getItem(position);
            if (convertView == null) {
                LayoutInflater inflater = getLayoutInflater();
                convertView  = inflater.inflate(R.layout.result_list_row_item, null);
            }
            if (item != null) {
                TextView txtName = convertView.findViewById(R.id.restaurantName);
                if (txtName != null) {
                    txtName.setText(item.getName());
                }
                TextView txtCategory = convertView.findViewById(R.id.restaurantCategory);
                if (txtCategory != null) {
                    txtCategory.setText(item.getCategory());
                }
                ImageView iv = convertView.findViewById(R.id.restaurantImageView);
                if (iv != null) {
                    Picasso.get().load(item.getShop_image1()).resize(130, 130).into(iv);
                }
                TextView txtLunch = convertView.findViewById(R.id.txtLunch);
                if (txtLunch != null) {
                    if (item.getLunch() != 0) {
                        int l  = item.getLunch() + 500;
                        String lunch = "￥" + item.getLunch() + " 〜 ￥" + l;
                        txtLunch.setText(lunch);
                    } else {
                        txtLunch.setText(" ー");
                    }
                }
                TextView txtDinner = convertView.findViewById(R.id.txtDinner);
                if (txtDinner != null) {
                    if (item.getBudget() != 0) {
                        int d = item.getBudget() + 500;
                        String dinner = "￥" + item.getBudget() + " 〜 ￥" + d;
                        txtDinner.setText(dinner);
                    } else {
                        txtDinner.setText(" ー");
                    }
                }
                TextView txtHoliday = convertView.findViewById(R.id.txtHoliday);
                if (txtHoliday != null) {
                    if (item.getHoliday().contains("無")) {
                        if (item.getHoliday().contains("年中無休")) {
                            txtHoliday.setText("年中無休");
                        }
                        txtHoliday.setText("無");
                    } else if (item.getHoliday().contains("年末年始")) {
                        txtHoliday.setText("年末年始");
                    } else if (item.getHoliday().contains("不定休")){
                        txtHoliday.setText("不定休");
                    } else {
                        txtHoliday.setText(" ー");
                    }
                }
            }
            return convertView;
        }
    }
    // endregion Inner Class
}
