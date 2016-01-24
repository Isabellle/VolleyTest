package com.example.training.volleytest;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends Activity {

    TextView textView;
    Button start;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = (Button) findViewById(R.id.start);
        textView = (TextView) findViewById(R.id.textView);
        requestQueue = Volley.newRequestQueue(this);


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, 'http://api.ilepez.be/volley.json',

                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONArray jsonArray = response.getJSONArray("students");
                                    JSONObject student = jsonArray.getJSONObject(i);
                                    String firstname = student.getString("firstname");
                                    String lastname = student.getString("lastname");
                                    String age = student.getString("age");

                                    textView.append(firstname + " " + lastname + " " + age + "\n");


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("VOLLEY", "ERROR");
                            }
                        }

                );

                requestQueue.add(jsonObjectRequest);

            }


        });

    }
}
