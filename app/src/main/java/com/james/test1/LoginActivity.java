package com.james.test1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;

public class LoginActivity extends AppCompatActivity {
    EditText ed_account;
    EditText ed_password;
    String account;
    String password;
    String deviceID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getSupportActionBar() != null){

            getSupportActionBar().hide();

        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
    public  void show(View view){
        Intent intent = new Intent();
        intent.setClass(LoginActivity.this,EMRActivity.class);
        startActivity(intent);
    }
    public void login(View view){
        ed_account = findViewById(R.id.ed_account);
        ed_password = findViewById(R.id.ed_password);
        account = ed_account.getText().toString();
        password = ed_password.getText().toString();
        deviceID = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
/*        if(account.equals("123") && password.equals("123")){
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
         */
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
        public void run() {
            //Starting Write and Read data with URL
            //Creating array for parameters
            String[] field = new String[3];
            field[0] = "account";
            field[1] = "password";
            field[2] = "deviceID";
            //Creating array for data
            String[] data = new String[3];
            data[0] = account;
            data[1] = password;
            data[2] = deviceID;
            PutData putData = new PutData("http://172.20.10.2/msc/login.php", "POST", field, data);
            if (putData.startPut()) {
                if (putData.onComplete()) {
                    String result = putData.getResult();
                    Log.d("hahah", account+","+password+result);
                    if(result.equals("Login Success")){
                        Log.d("hahah1", account+","+password);
                        //Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                        Toast.makeText(LoginActivity.this,"hi",Toast.LENGTH_LONG);
                        getIntent().putExtra("LOGIN_ACCOUNT",account);
                        getIntent().putExtra("LOGIN_PASSWORD",password);


                        setResult(RESULT_OK,getIntent());


                        finish();
//                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//                        startActivity(intent);
//                        finish();
                    }else{
                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                    }
                }
            }
            //End Write and Read data with URL
        }
    });
    }

    public  void apply(View view){
        Intent intent = new Intent();
        intent.setClass(LoginActivity.this,Application.class);
        startActivity(intent);

    }
}