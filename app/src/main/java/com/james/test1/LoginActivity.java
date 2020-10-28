package com.james.test1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    EditText ed_account;
    EditText ed_password;
    String account;
    String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public void login(View view){
        ed_account = findViewById(R.id.ed_account);
        ed_password = findViewById(R.id.ed_password);
        account = ed_account.getText().toString();
        password = ed_password.getText().toString();
        if(account.equals("123") && password.equals("123")){
            getIntent().putExtra("LOGIN_ACCOUNT",account);
            getIntent().putExtra("LOGIN_PASSWORD",password);
            setResult(RESULT_OK,getIntent());
            finish();
        }
        else {
            new AlertDialog.Builder(this)
                    .setTitle("Warmming")
                    .setMessage("Wrong")
                    .setPositiveButton("OK",null)
                    .show();
        }
    }
    public  void apply(View view){
        Intent intent = new Intent();
        intent.setClass(LoginActivity.this,Application.class);
        startActivity(intent);

    }
}