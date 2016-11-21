package com.example.asus.useurlresponse;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by asus on 2016/11/18.
 */
public class ForgetPassWordActivity extends AppCompatActivity {
        private MediaPlayer player;
        private boolean  isPause =false;
        private boolean flag =true;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_forgetpassword);
            ImageView submit = (ImageView)findViewById(R.id.imageView_submit);

            submit.setOnClickListener(new Button.OnClickListener(){
                                                 public void onClick(View v){

                                                     Uri uri =Uri.parse("https://www.baidu.com/");

                                                     Intent it = new Intent(Intent.ACTION_VIEW,uri);

                                                     startActivity(it);
                                                 }
                                             }
            );
//        RelativeLayout ll = (RelativeLayout)findViewById(R.id.fa);
//        final AnimationDrawable anim = (AnimationDrawable)ll.getBackground();
//        ll.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (flag) {
//                    anim.start();
//                    flag = false;
//                } else {
//                    anim.stop();
//                    flag = true;
//                }
//            }
//        });
        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.menu_false, menu);
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

}
