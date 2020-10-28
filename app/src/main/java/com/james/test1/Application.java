package com.james.test1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



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
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("user");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);
        ed_name = findViewById(R.id.ed_name);
        ed_idnum = findViewById(R.id.ed_idnum);
        ed_account = findViewById(R.id.ed_account);
        ed_password = findViewById(R.id.ed_password);
        ed_repassword = findViewById(R.id.ed_repassword);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("user");



//        ContactsInfo contact0 = new ContactsInfo("Hank","00000","123456","a123456","123@gamil.com");
//        myRef.child("00000").setValue(contact0);

    }
    public void apply(View view){
        ed_email = findViewById(R.id.ed_email);
        name = ed_name.getText().toString();
        idnum = ed_idnum.getText().toString();
        account = ed_account.getText().toString();
        password = ed_password.getText().toString();
        repassword = ed_repassword.getText().toString();
        email = ed_email.getText().toString();

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
            ContactsInfo contact0 = new ContactsInfo(name, idnum, account, password, email);
            myRef.child(idnum).setValue(contact0);
        }
    }
}