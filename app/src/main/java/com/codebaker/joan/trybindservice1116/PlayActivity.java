package com.codebaker.joan.trybindservice1116;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class PlayActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonPlay,buttonStop;
    private BackgroundMusicWithBindService mServiceBinder;
    String msg;

    private ServiceConnection myConnecection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder bind) {
            mServiceBinder = ((BackgroundMusicWithBindService.MyBinder)bind).getService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mServiceBinder = null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        buttonPlay = findViewById(R.id.buttonPlay);
        buttonStop = findViewById(R.id.buttonStop);
        buttonPlay.setOnClickListener(this);
        buttonStop.setOnClickListener(this);

        msg = getIntent().getStringExtra("intent") + " back to MainActivity";
        Log.e("INTENT-MSG",msg);

        Intent intent = new Intent(this, BackgroundMusicWithBindService.class);
        bindService(intent,myConnecection,Context.BIND_AUTO_CREATE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonPlay:
                mServiceBinder.play();
                break;
            case R.id.buttonStop:
                mServiceBinder.stop();
                break;
        }
    }

    @Override
    public void finish() {
        Intent intent = new Intent().putExtra("intent",msg);
        setResult(Activity.RESULT_OK,intent);

        super.finish();
    }

/*    @Override
    protected void onStop() {
        super.onStop();
        mServiceBinder.unbindService(myConnecection);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mServiceBinder.unbindService(myConnecection);
    }*/
}
