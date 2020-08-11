package jp.ac.jec.cm0134.tavernavi;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class AsyncHttpRequest extends AsyncTask<Uri.Builder, Void, ArrayList<RestaurantData>> {

    // region Properties
    private RestaurantsListActivity activity;
    // endregion Properties

    // region Constructor
    public AsyncHttpRequest(RestaurantsListActivity activity) { this.activity = activity; }
    // endregion Constructor

    // region Override
    @Override
    protected ArrayList<RestaurantData> doInBackground(Uri.Builder... params) {
        String resStr = "取得失敗";
        HttpURLConnection connection = null;
        try {
            URL url = new URL(params[0].toString());
            connection = (HttpURLConnection)url.openConnection();
            resStr = inputStreamToString(connection.getInputStream());
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("AsyncHttpRequest", e.toString());
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        ArrayList<RestaurantData> arrayList = JsonHelper.parseJson(resStr);
        return arrayList;
    }

    @Override
    protected void onPostExecute(ArrayList<RestaurantData> restrantData) {

        for (RestaurantData data: restrantData) {
            activity.adapter.add(data);
        }
        ListView list = (ListView)activity.findViewById(R.id.resultList);
        list.setAdapter(activity.adapter);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
    // endregion Override

    // region Private Function
    private String inputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }
        bufferedReader.close();
        return stringBuilder.toString();
    }
    // endregion Private Function
}
