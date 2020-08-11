package jp.ac.jec.cm0134.tavernavi;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonHelper {

    public static ArrayList<RestaurantData> parseJson(String strJson) {
        ArrayList<RestaurantData> arrayList = new ArrayList<RestaurantData>();
        try {
            JSONObject json = new JSONObject(strJson);
            JSONArray rest = json.getJSONArray("rest");
            for (int i = 0; i < rest.length(); i++) {
                JSONObject entry = rest.getJSONObject(i);
                arrayList.add(parseToRestaurantData(entry));
            }
        } catch (Exception e) {
            Log.e("JsonHelper", e.getMessage(), e);
        }
        return arrayList;
    }

    public static RestaurantData parseToRestaurantData(JSONObject json) throws JSONException {
        RestaurantData data = new RestaurantData();
        data.setName( json.getString("name"));
        data.setCategory( json.getString("category"));
        data.setLatitude( json.getString("latitude"));
        data.setLongitude( json.getString("longitude"));
        data.setAddress( json.getString("address"));
        data.setTel( json.getString("tel"));
        data.setOpentime( json.getString("opentime"));
        data.setHoliday( json.getString("holiday"));
        data.setBudget( json.getInt("budget"));

        data.setLunch( json.get("lunch"));

        JSONObject pr = json.getJSONObject("pr");
        data.setPr_short( pr.getString("pr_short"));
        data.setPr_long( pr.getString("pr_long"));

        JSONObject imageUrl = json.getJSONObject("image_url");
        data.setShop_image1( imageUrl.getString("shop_image1"));

        JSONObject couponUrl = json.getJSONObject("coupon_url");
        data.setMobile( couponUrl.getString("mobile"));

        return data;
    }
}
