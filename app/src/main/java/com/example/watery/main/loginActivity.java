package com.example.watery.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.watery.R;
import com.example.watery.service.UserService;
import com.example.watery.utils.MainActivity;
import com.example.watery.utils.User;

public class loginActivity extends AppCompatActivity {
    private TextView register_change;
    private EditText login_username,login_password;
    private Button login_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login_username = (EditText) findViewById(R.id.username);
        login_password = (EditText) findViewById(R.id.password);
        login_btn = (Button) findViewById(R.id.sign_in);
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username= login_username.getText().toString().trim();
                String password = login_password.getText().toString().trim();
                UserService userService = new UserService(loginActivity.this);
                boolean flag = userService.login(username,password);
                if(flag){
                    Toast.makeText(loginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(loginActivity.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(loginActivity.this,"登录失败",Toast.LENGTH_SHORT).show();
                }

            }
        });
        register_change = (TextView) findViewById(R.id.register_change);
        register_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(loginActivity.this,registerActivity.class);
                startActivity(intent);
            }
        });
    }
}