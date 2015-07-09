package com.chanryma.servicedemo.activity;

import android.app.Activity;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.chanryma.aidldemo.R;
import com.chanryma.servicedemo.service.MyService;

public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    private Button btnStart;
    private Button btnStop;
    private Button btnBind;
    private Button btnUnbind;
    private ServiceConnection serviceConnection;
    private Service myService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        setListeners();
        serviceConnection = new MyServiceConnection();
    }

    private void initUI() {
        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStop);
        btnBind = (Button) findViewById(R.id.btnBind);
        btnUnbind = (Button) findViewById(R.id.btnUnbind);
    }

    private void setListeners() {
        btnStart.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                startService();
            }
        });

        btnStop.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService();
            }
        });

        btnBind.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                bindService();
            }
        });

        btnUnbind.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                unbindService();
            }
        });
    }

    private void startService() {
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
    }

    private void stopService() {
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);
    }

    private void bindService() {
        Intent intent = new Intent(this, MyService.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    private void unbindService() {
        unbindService(serviceConnection);
    }

    private class MyServiceConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i(TAG, "MyServiceConnection onServiceConnected");
            myService = ((MyService.MyBinder) service).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i(TAG, "MyServiceConnection onServiceDisconnected");
        }
    }
}
