package com.hackinghell.secondapp;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.hackinghell.academyfirstday.IAddListener;
import com.hackinghell.secondapp.databinding.ActivityAnyBinding;

public class AnyActivity extends AppCompatActivity {



    private ActivityAnyBinding bindind;
    IAddListener iRemoteService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bindind = ActivityAnyBinding.inflate(getLayoutInflater());
        View view = bindind.getRoot();

        setContentView(view);
    }

    @Override
    protected void onStart() {
        super.onStart();
        bindind.btnnotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showNotification();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        bindind.btnBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("btn", "button is working");
                Intent intent = new Intent("ineed.add");
                intent.setPackage("com.hackinghell.academyfirstday");
                bindService(intent, connection, BIND_AUTO_CREATE);
            }
        });
    }

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder aidlBinder) {
            Log.i("Client activity", "client activity connected to service");
            iRemoteService = IAddListener.Stub.asInterface(aidlBinder);
            int sum = 0;
            try {
                sum = iRemoteService.add(10, 20);
                bindind.textView.setText("" + sum);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }

        }
        @Override
        public void onServiceDisconnected(ComponentName name) {
            //IF it disconnects
        }
    };
}