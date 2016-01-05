package com.example.zloj.googlecloudmessagetest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private String Server_API_Key = "AIzaSyDIUDnc_FC--OYR7pFPTswD0vUR_O0j8dw";
    private String Sender_ID_help = "755649251323";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
