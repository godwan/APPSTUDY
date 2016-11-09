package com.example.asus.useurlresponse;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int[] imageID = new int[]{R.drawable.walk1,R.drawable.walk2,
             R.drawable.walk3,R.drawable.walk4,R.drawable.walk5 ,
             R.drawable.walk6,R.drawable.walk7,R.drawable.walk8 };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);
        ImageView imageView_login =(ImageView)findViewById(R.id.imageView_login);
        TextView textView_register =(TextView)findViewById(R.id.textView_register);
        TextView textView_forgetpassword =(TextView)findViewById(R.id.textView_forgetpassword);

        imageView_login.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,SuccessActivity.class);
//                Intent intent1 = new Intent(MainActivity.this,FalseActivity.class);
                EditText text_Name = (EditText)findViewById(R.id.editText_userName);
                EditText text_Password = (EditText)findViewById(R.id.editText_password);

                if(text_Name.getText().toString().equals("demo") && text_Password.getText().toString().equals("123")) {
                    startActivity(intent);
                }
                else {
                    AlertDialog alert  = new AlertDialog.Builder(MainActivity.this).create();
                    alert.setIcon(R.drawable.plum) ;
                    alert.setTitle("您输入的用户名或密码错误");
                    alert.setMessage("请重新输入");
                    alert.setButton(DialogInterface.BUTTON_NEGATIVE, "取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MainActivity.this, "您单击了取消按钮", Toast.LENGTH_SHORT).show();
                        }
                    });

                    alert.setButton(DialogInterface.BUTTON_POSITIVE, "确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(MainActivity.this,"您单击了确定按钮",Toast.LENGTH_SHORT).show() ;
                        }
                    });

//                    alert.setButton(DialogInterface.BUTTON_NEUTRAL, "中立", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//
//                        }
//                    });
                    alert.show();

//                    startActivity(intent1);
                }
            }
        });

        textView_register.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,RegisterActivity.class);

                    startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
