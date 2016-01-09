package com.example.zloj.googlecloudmessagetest.gcm;

import android.content.Intent;

import com.google.android.gms.iid.InstanceIDListenerService;

/**
 * Created by zloj on 05.01.16.
 */
public class InstanceListener extends InstanceIDListenerService {


    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. This call is initiated by the
     * InstanceID provider.
     */
    // [START refresh_token]
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        // Fetch updated Instance ID token and notify our app's server of any changes (if applicable).
        Intent intent = new Intent(this, MyIntentService.class);
        startService(intent);
    }
}
