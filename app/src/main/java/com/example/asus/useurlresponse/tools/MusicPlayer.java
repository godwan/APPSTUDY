package com.example.asus.useurlresponse.tools;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.asus.useurlresponse.R;

/**
 * Created by asus on 2016/11/6.
 */
public class MusicPlayer extends AppCompatActivity {
    private MediaPlayer player;
    private boolean  isPause =false;
    private TextView hint;
    private boolean flag =true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.activity_login_success_main);


        setContentView(R.layout.activity_music);
        this.getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.id.action_success);


        final Button button1 =(Button)findViewById(R.id.button_play);// Play
        final Button button2 =(Button)findViewById(R.id.button_pause);// Pause
        final Button button3 =(Button)findViewById(R.id.button_stop);// stop
        player = MediaPlayer.create(this,R.raw.blue);


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.start();
                if(isPause){
                    button2.setText("暂停");
                    isPause =true;
                }
                button2.setEnabled(true);
                button3.setEnabled(true);
                button1.setEnabled(false);
            }

        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player.isPlaying()&&!isPause){
                    player.pause();
                    ((Button) v).setText("继续");
                    hint.setText("暂停播放音频...");
//                    button2.setEnabled(false);
//                    button3.setEnabled(false);
                    button1.setEnabled(true);
                    Log.i("<log>", "hah");
                }else{
                    player.start();
                    ((Button) v).setText("暂停");
                    hint.setText("继续播放音频...");
                    isPause=false;
                    button1.setEnabled(false);
                    Log.i("<log>", "qiqi");
                }
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (player.isPlaying()) {
                    player.stop();
                    isPause = true;
                    ((Button) v).setText("停止播放音频...");
                    button2.setEnabled(false);
                    button3.setEnabled(false);
                    button1.setEnabled(true);
                } else {
                    button3.setEnabled(false);
                    button2.setEnabled(false);
                    button1.setEnabled(true);
                }


            }
        });

        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                player.start();
            }
        });
//        RelativeLayout  ll = (RelativeLayout)findViewById(R.id.sucess_layout);
//        final AnimationDrawable animm = (AnimationDrawable)ll.getBackground();
//        ll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(flag){
//                    animm.start();
//                    flag =false;
//                }else{
//                    animm.stop();
//                    flag =true;
//                }
//            }
//        });

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