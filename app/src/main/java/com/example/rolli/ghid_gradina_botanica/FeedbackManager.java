package com.example.rolli.ghid_gradina_botanica;


import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class FeedbackManager extends AppCompatActivity {


    EditText nameInput;
    EditText messageInput;
    String output;
    TextView outputText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback_page);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        nameInput = (EditText) findViewById(R.id.nameInput);
        messageInput = (EditText) findViewById(R.id.messageInput);
        outputText = (TextView) findViewById(R.id.outputText);
    }



    public void sendJSONObject(String name, String message) throws JSONException, IOException {

        String url = "http://10.0.2.2:3000/receivefeedback";
        //String url = "http://192.168.0.105:3000/receivefeedback";
        URL object = new URL(url);
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("msg", message);

        Log.i("tag", "connection started");
        HttpURLConnection conn = (HttpURLConnection) object.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");


        OutputStream os = conn.getOutputStream();
        os.write(json.toString().getBytes());
        os.flush();


        BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));


        output = br.readLine();

        conn.disconnect();

    }

    public void sendFeedback(View view) {

        String name = nameInput.getText().toString();
        String message = messageInput.getText().toString();

        if( name.equals("") || message.equals("")) {
            Toast.makeText(this, "complete all fields", Toast.LENGTH_SHORT).show();
        } else {
            new Feedback().execute(name, message);
        }

    }

    public class Feedback extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {

            try {
                sendJSONObject(params[0], params[1]);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {

            outputText.setText(output);
            super.onPostExecute(s);
        }
    }


}

