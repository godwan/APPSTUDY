package com.example.asus.useurlresponse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.useurlresponse.dbmanage.model.SqliteDB;
import com.example.asus.useurlresponse.dbmanage.model.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private int[] imageID = new int[]{R.drawable.walk1,R.drawable.walk2,
             R.drawable.walk3,R.drawable.walk4,R.drawable.walk5 ,
             R.drawable.walk6,R.drawable.walk7,R.drawable.walk8 };

    private EditText text_Name;
    private EditText text_Password;
    private List<User> userList;
    private List<User> dataList = new ArrayList<>();
    private Button button_login;
    private TextView textView_register;
    private TextView textView_forgetpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entry);


        button_login =(Button)findViewById(R.id.button_login);
        textView_register =(TextView)findViewById(R.id.textView_register);
        textView_forgetpassword =(TextView)findViewById(R.id.textView_forgetpassword);

        text_Name = (EditText)findViewById(R.id.editText_userName);
        text_Password = (EditText)findViewById(R.id.editText_password);

        button_login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String name=text_Name.getText().toString().trim();
                String pass=text_Password.getText().toString().trim();
                //userList=SqliteDB.getInstance(getApplicationContext()).loadUser();
                if("".equals(name)|| "".equals(pass)){
                        Toast.makeText(MainActivity.this, "用户名或密码不能为空！", Toast.LENGTH_SHORT).show();
                }else{
                    int result= SqliteDB.getInstance(getApplicationContext()).Quer(pass,name);
                    if (result == 1) {
                        Intent intent = new Intent(MainActivity.this,SuccessActivity.class);
                        startActivity(intent);
                        Toast.makeText(MainActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();
                    }
                    else if (result==0){
                        Toast.makeText(MainActivity.this, "用户名或密码不存在！", Toast.LENGTH_SHORT).show();

                    }
                    else if(result==-1)
                    {
                        Toast.makeText(MainActivity.this, "Sorry Wrong！", Toast.LENGTH_SHORT).show();
                    }
                }


/*                for (User user : userList) {

                    if (user.getUsername().equals(name))
                    {
                        if (user.getUserpwd().equals(pass))
                        {
                            state.setText("登录成功！");

                        }else {
                            state.setText("密码错误！");

                        }
                    }
                    else {
                        state.setText("用户名不存在！");

                    }

                }*/

            }
        });

        textView_register.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                    startActivity(intent);
            }
        });

        textView_forgetpassword.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this,ForgetPassWordActivity.class);
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
