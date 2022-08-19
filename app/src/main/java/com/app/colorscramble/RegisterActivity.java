package com.app.colorscramble;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.app.colorscramble.Controller.RegisterController;
import com.app.colorscramble.View.IRegisterView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity implements IRegisterView {

    private final String TAG = "RegisterActivity";

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_email)
    EditText edt_email;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_name)
    EditText edt_name;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_password)
    EditText edt_password;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_cfmpassword)
    EditText edt_cfmpassword;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_register)
    Button btn_register;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_login)
    TextView txt_login;

    RegisterController registerController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);
        Log.w("Activity -->",TAG);

        registerController = new RegisterController(this);

        btn_register.setOnClickListener(v -> registerController.OnRegister(edt_name.getText().toString().trim(),edt_email.getText().toString().trim(),edt_password.getText().toString().trim(),edt_cfmpassword.getText().toString().trim()));

        txt_login.setOnClickListener(view -> registerController.OnLogin());


    }

    @Override
    public void OnRegisterSuccess(String message) {

        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();

        //Call Main activity
        Intent i=new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void OnRegisterError(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();

    }

    @Override
    public void OnLogIn() {

        onBackPressed();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        //Call register activity

        Intent i=new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(i);
    }

}