package com.example.zloj.googlecloudmessagetest;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private String Server_API_Key = "AIzaSyDIUDnc_FC--OYR7pFPTswD0vUR_O0j8dw";
    private String Sender_ID_help = "755649251323";

    private String SENDER_ID = "755649251323";
    private GoogleCloudMessaging gcm = null;
    private String tokenTAG = "token";

    private TextView textViewToken;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = MainActivity.this.getSharedPreferences(MainActivity.class.getName(),
                Context.MODE_PRIVATE);
        textViewToken = (TextView) findViewById(R.id.tv_token);
        String tokenFromShare = sharedPreferences.getString(tokenTAG, "");
        if (tokenFromShare.length() == 0) {
            regGCM();

        } else textViewToken.setText(tokenFromShare);
    }

    private void regGCM() {
        new AsyncGCMRegister().execute();
    }


    private class AsyncGCMRegister extends AsyncTask {
        String msg = "", regId = "";

        /**
         * Override this method to perform a computation on a background thread. The
         * specified parameters are the parameters passed to {@link #execute}
         * by the caller of this task.
         * <p>
         * This method can call {@link #publishProgress} to publish updates
         * on the UI thread.
         *
         * @param params The parameters of the task.
         * @return A result, defined by the subclass of this task.
         * @see #onPreExecute()
         * @see #onPostExecute
         * @see #publishProgress
         */
        @Override
        protected Object doInBackground(Object[] params) {

            try {
                if (gcm == null) {
                    gcm = GoogleCloudMessaging.getInstance(MainActivity.this);
                }
                regId = gcm.register(SENDER_ID);
                Log.d("in async task", regId);

                // try
                msg = "Device registered, registration ID= ::\n" + regId;

            } catch (IOException ex) {
                msg = "Error :" + ex.getMessage();
                System.out.println(msg + "\n");
                return msg;
            }
            System.out.println(msg + "\n");
            return msg;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            System.out.println(o.toString() + "\n");
            textViewToken.setText("Token = " + msg);
            if (!msg.contains("Error")) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(tokenTAG, msg);
                editor.commit();
            }
        }
    }
}

//https://developers.google.com/android/guides/setup
//        https://developers.google.com/mobile/add?platform=android&cntapi=gcm&cnturl=https:%2F%2Fdevelopers.google.com%2Fcloud-messaging%2Fandroid%2Fclient&cntlbl=Continue%20Adding%20GCM%20Support&%3Fconfigured%3Dtrue
//        https://developers.google.com/cloud-messaging/android/client
//        https://developers.google.com/cloud-messaging/android/start
//        https://github.com/google/gcm
//        https://github.com/googlesamples/google-services/tree/master/android
//        http://ru.stackoverflow.com/questions/410335/%D0%9A%D0%B0%D0%BA-%D0%BF%D1%80%D0%B0%D0%B2%D0%B8%D0%BB%D1%8C%D0%BD%D0%BE-%D0%BD%D0%B5-%D0%B4%D0%B0%D1%82%D1%8C-%D0%B7%D0%B0%D1%81%D0%BD%D1%83%D1%82%D1%8C-google-cloud-messaging-%D0%BD%D0%B0-android
//        http://ru.stackoverflow.com/questions/311424/%D0%A3%D1%81%D1%82%D0%B0%D0%BD%D0%BE%D0%B2%D0%BA%D0%B0-%D1%83%D0%B2%D0%B5%D0%B4%D0%BE%D0%BC%D0%BB%D0%B5%D0%BD%D0%B8%D0%B9-%D0%B2-android-%D0%B2-%D1%81%D0%B2%D0%BE%D0%B5-%D0%BF%D1%80%D0%B8%D0%BB%D0%BE%D0%B6%D0%B5%D0%BD%D0%B8%D0%B5
//
