package com.usrdetails.myapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    EditText usrEditTxt,passEditTxt;
    Button saveBtn,loadBtn;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usrEditTxt = findViewById(R.id.usr_edit_text_id);
        passEditTxt = findViewById(R.id.psd_edit_text_id);

        saveBtn = findViewById(R.id.saveBtnId);
        loadBtn = findViewById(R.id.loadBtnId);
        textView = findViewById(R.id.txtViewId);


        saveBtn.setOnClickListener(this);
        loadBtn.setOnClickListener(this);


    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View view) {

        if (view.getId()==R.id.saveBtnId){
            String usrTxt = usrEditTxt.getText().toString();
            String passTxt = passEditTxt.getText().toString();

            if (usrTxt.isEmpty() || passTxt.isEmpty()){

                Toast.makeText(MainActivity.this,"Please Enter Your UserName and Password",Toast.LENGTH_SHORT).show();
            }else {
                SharedPreferences sharedPreferences = getSharedPreferences("userDetails",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("usernameKey",usrTxt);
                editor.putString("passwordKey",passTxt);
                editor.apply();
                usrEditTxt.setText("");
                passEditTxt.setText("");
                Toast.makeText(MainActivity.this,"Successful Data Upload",Toast.LENGTH_SHORT).show();
            }

        }else if (view.getId()==R.id.loadBtnId){

            SharedPreferences sharedPreferences = getSharedPreferences("userDetails",Context.MODE_PRIVATE);
            if (sharedPreferences.contains("usernameKey") && sharedPreferences.contains("passwordKey")){

                String usrTxt = sharedPreferences.getString("usernameKey","Data Not Found");
                String passTxt = sharedPreferences.getString("passwordKey","Data not Found");
                textView.setText(usrTxt+"\n"+passTxt);
            }
        }

    }
}