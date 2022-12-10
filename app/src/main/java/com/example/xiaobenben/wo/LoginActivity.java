package com.example.xiaobenben.wo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.xiaobenben.MainActivity;
import com.example.xiaobenben.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // 关联用户名、密码和登录、注册按钮
        EditText userName = (EditText) this.findViewById(R.id.UserNameEdit);
        EditText passWord = (EditText) this.findViewById(R.id.PassWordEdit);
        Button loginButton = (Button) this.findViewById(R.id.LoginButton);
        Button signUpButton = (Button) this.findViewById(R.id.SignUpButton);
        // 登录按钮监听器
        loginButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String strUserName = userName.getText().toString().trim();
                        String strPassWord = passWord.getText().toString().trim();
                        // 判断如果用户名是123456 密码是123456就是登录成功
                        if (strUserName.equals("123456") && strPassWord.equals("123456")) {
                            Toast.makeText(LoginActivity.this, "登录成功！", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            intent.putExtra("sign", "ok");
                            setResult(16, intent);
                            finish();

                        } else {
                            Toast.makeText(LoginActivity.this, "请输入正确的用户名或密码！", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );
        // 注册按钮监听器
        signUpButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                        startActivity(intent);
                    }
                }
        );

    }
}