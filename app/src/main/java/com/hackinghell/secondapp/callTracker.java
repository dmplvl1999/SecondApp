package com.hackinghell.secondapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.util.Log;

public class callTracker extends BroadcastReceiver {
    private static final String TAG = callTracker.class.getSimpleName();
    @Override
    public void onReceive(Context context, Intent intent) {

        // Get the phone state from the intent
        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);

        if (state != null) {
            String incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
            if (TelephonyManager.EXTRA_STATE_RINGING.equals(state)) {
                // Incoming call is ringing
                Log.i(TAG, "Incoming call from: " + incomingNumber);
            } else if (TelephonyManager.EXTRA_STATE_OFFHOOK.equals(state)) {
                Log.i("Calllog", "Outgoing call to " + incomingNumber);
            }
        }
    }
}
