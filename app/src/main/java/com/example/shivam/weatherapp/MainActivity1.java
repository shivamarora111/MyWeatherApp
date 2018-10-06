package com.example.shivam.weatherapp;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.MalformedJsonException;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.Date;
public class MainActivity1 extends AppCompatActivity {
    TextView textView2;
    TextView textView20;
    TextView textView18;
    TextView textView22;
    TextView textView21;
    TextView textView3;
    ImageView imageView;
    ImageView imageView3;
    EditText editText3;
    Button button2;
    public void onClick(View view)
    {
        InputMethodManager mg=(InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        mg.hideSoftInputFromWindow(editText3.getWindowToken(),0);
        try {
            String encode= URLEncoder.encode(editText3.getText().toString(),"UTF-8");
            weather();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        textView2 = (TextView) findViewById(R.id.textView2);
        textView20 = (TextView) findViewById(R.id.textView20);
        textView18 = (TextView) findViewById(R.id.textView18);
        textView22 = (TextView) findViewById(R.id.textView22);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView21 = (TextView) findViewById(R.id.textView21);
        imageView=(ImageView)findViewById(R.id.imageView);
        imageView3=(ImageView)findViewById(R.id.imageView3);
        editText3=(EditText)findViewById(R.id.editText3);
        button2=(Button)findViewById(R.id.button2);

        String currdat = DateFormat.getDateInstance().format(new Date());
        textView2.setText(currdat);
button2.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        weather();

    }
});
editText3.setSingleLine(true);
    }
    public void weather() {
        String url = "https://api.openweathermap.org/data/2.5/weather?q="+editText3.getText().toString()+"&appid=bdcf285f6e6ad216dac2624aad4b5801";
        JsonObjectRequest jor = new JsonObjectRequest(Request.Method.GET, url,null,new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONObject j = response.getJSONObject("main");
                    JSONArray arr=response.getJSONArray("weather");
                    JSONObject obj=arr.getJSONObject(0);
                    String temp = j.getString("temp");
                    String pressure=j.getString("pressure");
                    String description=obj.getString("description");
                    String humid = j.getString("humidity");
                    String city=response.getString("name");
                    textView3.setText(city);
                    textView3.setVisibility(View.VISIBLE);

                    double temp1=Double.parseDouble(temp);
                    double centi=temp1-273.15;
                    Math.round(centi);
                    int cent=(int)centi;
                    textView18.setText(String.valueOf(cent));
                    textView21.setText(pressure+" pa");
                    textView20.setText(description);
                    textView22.setText(humid+"%");
                    if(description.equals("mist")){
                        imageView.setImageResource(R.drawable.mist);
                    }
                    else if(description.equals("haze")){
                        imageView.setImageResource(R.drawable.haze_weather);

                    }  else if(description.equals("scattered clouds")){
                        imageView.setImageResource(R.drawable.cloud);
                        imageView3.setImageResource(R.drawable.cloudy);

                    }
                    else if(description.equals("rain")||description.equals("light rain")){
                        imageView.setImageResource(R.drawable.rain);
                        imageView3.setImageResource(R.drawable.rain1);

                    }
                    else if(description.equals("broken clouds")){
                        imageView.setImageResource(R.drawable.cloud);
                        imageView3.setImageResource(R.drawable.cloudy);

                    }
                    else if(description.equals("heavy intensity rain")){
                        imageView.setImageResource(R.drawable.rain);
                        imageView3.setImageResource(R.drawable.rain1);

                    }
                    else if(description.equals("few clouds")){
                        imageView.setImageResource(R.drawable.cloud);
                        imageView3.setImageResource(R.drawable.cloudy);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                if (error instanceof NoConnectionError||error instanceof TimeoutError) {
                Toast.makeText(MainActivity1.this, "Can't establish a connection", Toast.LENGTH_LONG).show();
                }
                    else if(error instanceof NetworkError){
                Toast.makeText(MainActivity1.this, "Can't establish a connection", Toast.LENGTH_LONG).show();

            }
            else if(error instanceof ServerError){
                    Toast.makeText(MainActivity1.this, "place not found", Toast.LENGTH_LONG).show();
                }}



        }
        );
        RequestQueue queue= Volley.newRequestQueue(this);
        queue.add(jor);
    }
}
