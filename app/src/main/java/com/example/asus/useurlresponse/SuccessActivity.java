package com.example.asus.useurlresponse;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.asus.useurlresponse.tools.MusicPlayer;
import com.example.asus.useurlresponse.tools.ShowPicture;
import com.example.asus.useurlresponse.tools.TakePhoto;

public class SuccessActivity extends AppCompatActivity {
    private MediaPlayer player;
    private boolean  isPause =false;
    private TextView hint;
    private boolean flag =true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_login_success_main);
        ImageButton imageButton_bluetooth = (ImageButton)findViewById(R.id.imageButton_bluetooth);
        ImageButton imageButton_calculator = (ImageButton)findViewById(R.id.imageButton_calculator);
        ImageButton imageButton_calendar = (ImageButton)findViewById(R.id.imageButton_calendar);
        ImageButton imageButton_camera = (ImageButton)findViewById(R.id.imageButton_camera);
        ImageButton imageButton_find = (ImageButton)findViewById(R.id.imageButton_find);
        ImageButton imageButton_fitness = (ImageButton)findViewById(R.id.imageButton_fitness);
        ImageButton imageButton_heartbeatbluer = (ImageButton)findViewById(R.id.imageButton_heartbeatbluer);
        ImageButton imageButton_hotspotfind = (ImageButton)findViewById(R.id.imageButton_hotspotfind);
        ImageButton imageButton_music = (ImageButton)findViewById(R.id.imageButton_music);
        ImageButton imageButton_noteslg3 = (ImageButton)findViewById(R.id.imageButton_noteslg3);
        ImageButton imageButton_picture = (ImageButton)findViewById(R.id.imageButton_picture);
        ImageButton imageButton_player = (ImageButton)findViewById(R.id.imageButton_player);

        imageButton_music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SuccessActivity.this,MusicPlayer.class);
                startActivity(intent);
            }
        });

        imageButton_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SuccessActivity.this,ShowPicture.class);
                startActivity(intent);
            }
        });

        imageButton_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SuccessActivity.this,TakePhoto.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_success, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void onDestroy(){
        if(player.isPlaying()){
            player.stop();
        }
        player.release();
        super.onDestroy();
    }


}

