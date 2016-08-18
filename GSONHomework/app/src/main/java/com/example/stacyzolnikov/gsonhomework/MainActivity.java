package com.example.stacyzolnikov.gsonhomework;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static String mURL ="https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22nome%2C%20ak%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";
            //"http://xml.weather.yahoo.com/forecastrss?w=2459115&u=f";
    private YahooAsyncTask mTask;
    private Object mQuery;
    private Object mResults;
    private Object mChannel;
    private Object mItem;
    private ArrayAdapter<String> mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>());
        Button searchButton = (Button) findViewById(R.id.search_button);
        EditText searchEdit = (EditText) findViewById(R.id.search_text);
        ListView listView = (ListView) findViewById(R.id.result);
        listView.setAdapter(mAdapter);

        searchButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()) {
                    if (mTask != null && (mTask.getStatus() != AsyncTask.Status.FINISHED)) {
                        mTask.cancel(true);
                    }
                    mTask = new YahooAsyncTask();
                    String query = ((TextView) findViewById(R.id.search_text)).getText().toString();
                    mTask.execute(mURL);
                } else {
                    Toast.makeText(MainActivity.this, "No network connection", Toast.LENGTH_LONG).show();
                }

            }
        });

    }
    public class YahooAsyncTask extends AsyncTask<String, Void, WeatherItemResult>{


        @Override
        protected WeatherItemResult doInBackground(String... params) {
//            WeatherItemResult result= null;
//
//            OkHttpClient client = new OkHttpClient();
//
//            Request request = new Request.Builder()
//                    .url(params[0])
//                    .build();
            String data="";
            try {
              // Response response = client.newCall(request).execute();

                URL url = new URL(params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream inStream = connection.getInputStream();
                data = getInputData(inStream);
                Log.i(TAG, "MainActivity:test");
               // Log.d("Blah", response.body().string()+"");
              //  result = gson.fromJson(response.body().string(), WeatherItemResult.class);

                Log.i(TAG, "z: Test");
//                Log.d("Hey",
//                result.getQuery().getResults().getChannel().getItem().getForecast().getHigh());
            } catch (IOException e) {
                e.printStackTrace();
                Log.d("Hey", "didn't work");
            }

            Gson gson = new Gson();
            WeatherItemResult weatherItemResult = gson.fromJson(data, WeatherItemResult.class);
//            Query result = gson.fromJson(data, Query.class);
            Log.d("Hey", weatherItemResult+"");


            //for loop
          //  weatherItemResult.getQuery().getResults().getChannel().getItem().getForecast().get(i);
            //make an arraylist<forecast> .add


            return new WeatherItemResult();


        }



        @Override
        protected void onPostExecute(WeatherItemResult itemResult) {
            super.onPostExecute(itemResult);

//            itemResult.getQuery().getResults()
            mAdapter.clear();
           // mAdapter.addAll(String.valueOf(itemResult.getQuery().getResults().getChannel().getItem().getForecast()));

        }

        private String getInputData(InputStream inStream) throws IOException {
            StringBuilder builder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inStream));

            String data = null;

            while((data = reader.readLine()) != null) {
                builder.append(data);}
            reader.close();
            return builder.toString();
        }
    }


}
