package com.example.asus.useurlresponse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.asus.useurlresponse.dbmanage.model.SqliteDB;
import com.example.asus.useurlresponse.dbmanage.model.User;

public class RegisterActivity extends AppCompatActivity {

    private EditText et_user;
    private EditText et_password;
    private EditText et_password_again;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button buttonRE;
        et_user = (EditText)findViewById(R.id.reg_username);
        et_password = (EditText)findViewById(R.id.reg_password);
        et_password_again = (EditText)findViewById(R.id.reg_password_again);

        et_password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub
                if (hasFocus) {
                    //获得焦点处理
                    Log.i("<REG>", "onFocusChange ");
                } else {
                    //失去焦点处理
                    Log.i("<REG>", "noFocusChange ");
                }
            }
        });

        et_password_again.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // TODO Auto-generated method stub
                if (hasFocus) {
                    //获得焦点处理

                    Log.i("<REG>", "onFocusChange ");
                } else {
                    //失去焦点处理
                    if (et_password.getText().toString().equals( et_password_again.getText().toString())) {
                        Toast.makeText(RegisterActivity.this, "密码可用", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(RegisterActivity.this, "两次输入的密码不一致，请重新输入", Toast.LENGTH_SHORT).show();
                        et_password_again.setText("");
                    }

                    Log.i("<REG>", "noFocusChange ");
                }
            }
        });


        buttonRE =(Button)findViewById(R.id.reg_register);
        buttonRE.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.i("<REG>", et_user.getText().toString());
                String name=et_user.getText().toString().trim();
                String pass=et_password.getText().toString().trim();
                if("".equals(name) ||"".equals(pass)){
                    Toast.makeText(RegisterActivity.this, "用户名或密码不能为空！", Toast.LENGTH_SHORT).show();
                }else {
                    User user = new User();
                    user.setUsername(name);
                    user.setUserpwd(pass);

                    int result = SqliteDB.getInstance(getApplicationContext()).saveUser(user);
                    if (result == 1) {
                        Toast.makeText(RegisterActivity.this, "注册成功！", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else if (result == -1) {
                        Toast.makeText(RegisterActivity.this, "用户名已经存在或输入的用户名或密码为空！", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(RegisterActivity.this, "内部出错了！", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
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
