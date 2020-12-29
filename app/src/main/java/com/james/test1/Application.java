package com.james.test1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.audiofx.Virtualizer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;


public class Application extends AppCompatActivity {
    EditText ed_name;
    EditText ed_idnum;
    EditText ed_account;
    EditText ed_password;
    EditText ed_repassword;
    EditText ed_email;
    String name;
    String idnum;
    String account;
    String password;
    String repassword;
    String email;
    String deviceID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (getSupportActionBar() != null){

            getSupportActionBar().hide();

        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);
        ed_name = findViewById(R.id.ed_name);
        ed_idnum = findViewById(R.id.ed_idnum);
        ed_account = findViewById(R.id.ed_account);
        ed_password = findViewById(R.id.ed_password);
        ed_repassword = findViewById(R.id.ed_repassword);


    }
    public void apply(View view){
        ed_email = findViewById(R.id.ed_email);
        name = ed_name.getText().toString();
        idnum = ed_idnum.getText().toString();
        account = ed_account.getText().toString();
        password = ed_password.getText().toString();
        repassword = ed_repassword.getText().toString();
        email = ed_email.getText().toString();
        deviceID = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);

        if (!password.equals(repassword)) {
            new AlertDialog.Builder(this)
                    .setTitle("Warmming")
                    .setMessage("密碼確認錯誤")
                    .setPositiveButton("OK",null)
                    .show();
        }else if(name.length() == 0 || idnum.length() == 0 || account.length() == 0 || password.length() == 0 || repassword.length() == 0 || email.length() == 0){
            new AlertDialog.Builder(this)
                    .setTitle("Warmming")
                    .setMessage("不可輸入空白")
                    .setPositiveButton("OK",null)
                    .show();
        }else if(!email.contains("@")){
            new AlertDialog.Builder(this)
                    .setTitle("Warmming")
                    .setMessage("信箱格式錯誤")
                    .setPositiveButton("OK",null)
                    .show();
        }else {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    //Starting Write and Read data with URL
                    //Creating array for parameters
                    String[] field = new String[6];
                    field[0] = "name";
                    field[1] = "numid";
                    field[2] = "account";
                    field[3] = "password";
                    field[4] = "email";
                    field[5] = "deviceID";
                    //Creating array for data
                    String[] data = new String[6];
                    data[0] = name;
                    data[1] = idnum;
                    data[2] = account;
                    data[3] = password;
                    data[4] = email;
                    data[5] = deviceID;
                    PutData putData = new PutData("http://172.20.10.2/msc/signup.php", "POST", field, data);
                    if (putData.startPut()) {
                        if (putData.onComplete()) {
                            String result = putData.getResult();
                            if(result.equals("Sign Up Success")){
                                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
//                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
 //                               startActivity(intent);
                                finish();
                            }else{
                                Toast.makeText(getApplicationContext(),result,Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                    //End Write and Read data with URL
                }
            });
        Log.d("hahaha",deviceID);
        }
    }
    public void  back(View view){
        finish();
    }
}