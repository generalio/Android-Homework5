package com.generlas.homework5;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SignActivity extends AppCompatActivity {
    private EditText mEtAccount;
    private EditText mEtPassword;
    private Button mBtnSignIn;

    public static void startActivity(Context context) {
        Intent intent = new Intent(context,SignActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign);
        initView();
        initEvent();
    }

    private void initView() {
        mEtAccount = findViewById(R.id.et_sign_account);
        mEtPassword = findViewById(R.id.et_sign_password);
        mBtnSignIn = findViewById(R.id.btn_sign_signIn);
    }

    private void initEvent() {
        mBtnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignIn();
            }
        });
    }

    private void SignIn() {
        String account_username = mEtAccount.getText().toString();
        String account_password = mEtPassword.getText().toString();
        if(account_username.equals("") || account_password.equals("")) {
            Toast.makeText(this,"注册失败",Toast.LENGTH_SHORT).show();
        }
        else {
            SharedPreferences.Editor editor = getSharedPreferences("userdata",MODE_PRIVATE).edit();
            editor.putString(account_username,account_password);
            editor.apply();
            Toast.makeText(this,"注册成功",Toast.LENGTH_SHORT).show();
            LoginActivity.startActivity(this);
        }
    }
}