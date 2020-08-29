package com.example.watery.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.watery.R;
import com.example.watery.service.UserService;
import com.example.watery.utils.User;

public class registerActivity extends AppCompatActivity {
    private TextView login_change;
    private EditText register_username,register_password,comfirm_password;
    private Button register_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register_username = (EditText) findViewById(R.id.register_username);
        register_password = (EditText) findViewById(R.id.register_password);
        comfirm_password = (EditText) findViewById(R.id.password_confirm);
        register_btn = (Button) findViewById(R.id.register_btn);
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = register_username.getText().toString();
                String password = register_password.getText().toString();
                if (TextUtils.isEmpty(register_username.getText().toString())){
                    Toast.makeText(registerActivity.this,"用户名不能为空",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(register_password.getText().toString())){
                    Toast.makeText(registerActivity.this,"密码不能为空",Toast.LENGTH_SHORT).show();
                }else if(!(comfirm_password.getText().toString()).equals(register_password.getText().toString())){
                    Toast.makeText(registerActivity.this,"请输入两次一样的密码",Toast.LENGTH_SHORT).show();
                }else{
                    UserService userService = new UserService(registerActivity.this);
                    User user = new User();
                    user.setUsername(username);
                    user.setPassword(password);
                    userService.register(user);
                    Toast.makeText(registerActivity.this,"注册成功！",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(registerActivity.this,loginActivity.class);
                    startActivity(intent);
                }
            }
        });

        login_change = (TextView) findViewById(R.id.login_change);
        login_change.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(registerActivity.this,loginActivity.class);
                startActivity(intent);
            }
        });
    }
}