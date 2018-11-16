package com.codebaker.joan.trybindservice1116;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

public class BackgroundMusicWithBindService extends Service {
    private MediaPlayer mPlayer;

    @Override
    public IBinder onBind(Intent intent) {
        return new BackgroundMusicWithBindService.MyBinder();
    }


    public void play(){
        mPlayer =  MediaPlayer.create(this, R.raw.vibe);
        mPlayer.setLooping(true);
        mPlayer.setVolume(100,100);
        mPlayer.start();
    }

    public void stop() {
        mPlayer.stop();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);

    }

    /*@Override
    public void onDestroy() {
        mPlayer.release();
    }*/

    public class MyBinder extends Binder {
        BackgroundMusicWithBindService getService(){
            return BackgroundMusicWithBindService.this;
        }
    }
}
