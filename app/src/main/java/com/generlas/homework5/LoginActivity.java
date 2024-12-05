package com.generlas.homework5;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {

    private EditText mEtAccount;
    private EditText mEtPassword;
    private Button mBtnLogin;
    private Button mBtnSign;
    private CheckBox mCbRemember;

    public  static void startActivity(Context context) {
        Intent intent = new Intent(context,LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initInfo();
        initEvent();
    }
    private void initView() {
        mEtAccount = findViewById(R.id.et_login_account);
        mEtPassword = findViewById(R.id.et_login_password);
        mBtnLogin = findViewById(R.id.btn_login_login);
        mBtnSign = findViewById(R.id.btn_login_sign);
        mCbRemember = findViewById(R.id.cb_login_remember);
    }

    private void initInfo() {
        SharedPreferences spreferences = getSharedPreferences("userdata",MODE_PRIVATE);
        if(spreferences.getBoolean("isRemembered",false)) { //如果记住过就自动set
            mEtAccount.setText(spreferences.getString("remembered_username",""));
            mEtPassword.setText(spreferences.getString("remembered_password",""));
            mCbRemember.setChecked(true);
        }
    }
    private void initEvent() {

        /*
        mCbRemember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = getSharedPreferences("userdata",MODE_PRIVATE).edit();
                if(isChecked) {
                    editor.putBoolean("isRemembered",true);
                    editor.commit();
                }
                else {
                    editor.putBoolean("isRemembered",false);
                    editor.commit();
                }
            }
        });*/

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        mBtnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sign();
            }
        });
    }

    private void login() {
        String username = mEtAccount.getText().toString();
        String password = mEtPassword.getText().toString();
        SharedPreferences spreference = getSharedPreferences("userdata",MODE_PRIVATE);
        SharedPreferences.Editor editor = getSharedPreferences("userdata",MODE_PRIVATE).edit();
        String get_Password = spreference.getString(username,"");

        if(mCbRemember.isChecked()) { //判断checkbox是否勾选
            editor.putBoolean("isRemembered",true);
            editor.putString("remembered_username",mEtAccount.getText().toString());
            editor.putString("remembered_password",mEtPassword.getText().toString());
        }
        else {
            editor.putBoolean("isRemembered",false);
            editor.putString("remembered_username","");
            editor.putString("remembered_password","");
        }
        editor.commit();

        if(password.equals(get_Password)) {
            login_success();
        }
        else {
            login_failed();
        }
    }

    private void login_success() {
        Toast.makeText(this,"登录成功",Toast.LENGTH_SHORT).show();
        HomeActivity.startActivity(this);
    }

    private void login_failed() {
        Toast.makeText(this,"登录失败",Toast.LENGTH_SHORT).show();
    }

    private void sign() {
        SignActivity.startActivity(this);
    }
}