package com.example.drawing;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;


public class MainActivity extends AppCompatActivity {
    public int screenNo = 0;
    int i= 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_main);

    }

    private void altScreen() {
        if (screenNo == 1) {
            setContentView(R.layout.colour);
        }
        if (screenNo == 2) {
            setContentView(R.layout.pen);
            startVideo();
        }
        if (screenNo == 5) {
            setContentView(R.layout.selected);
        }
        if (screenNo == 6) {
            setContentView(R.layout.firsttime);
        }
    }

    private void startVideo() {
        final VideoView video =(VideoView)findViewById(R.id.videoView);

        MediaController mediaCOntroller = new MediaController(this);

        Uri videopath = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.drawvideo);
        video.setVideoPath(String.valueOf(videopath));
        video.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
            @Override
            public void onCompletion(MediaPlayer mp){
                setContentView(R.layout.users);
            }
        });
        video.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        if(event.getAction() == MotionEvent.ACTION_UP){
            screenNo++;
            altScreen();
        }
        return true;
    }
}

