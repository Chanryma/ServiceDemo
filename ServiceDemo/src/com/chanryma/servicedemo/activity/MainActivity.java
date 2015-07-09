package com.chanryma.servicedemo.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.chanryma.aidldemo.R;
import com.chanryma.servicedemo.service.MyService;

public class MainActivity extends Activity {
    private Button btnStart;
    private Button btnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        setListeners();
    }

    private void initUI() {
        btnStart = (Button) findViewById(R.id.btnStart);
        btnStop = (Button) findViewById(R.id.btnStop);
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
        startService(intent);
    }

    private void unbindService() {
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);
    }
}
