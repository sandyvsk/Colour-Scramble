package com.app.colorscramble;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.app.colorscramble.Controller.ILoginController;
import com.app.colorscramble.Controller.LoginController;
import com.app.colorscramble.View.ILoginView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity  implements ILoginView {

    private final String TAG = "LoginActivity";

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_email)
    EditText edt_email;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.edt_password)
    EditText edt_password;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.btn_login)
    Button btn_login;

    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_signup)
    TextView txt_signup;

    ILoginController loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        Log.w("Activity -->",TAG);

        loginPresenter = new LoginController(this);

        btn_login.setOnClickListener(v -> loginPresenter.OnLogin(edt_email.getText().toString().trim(),edt_password.getText().toString().trim()));

        txt_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loginPresenter.OnSignup();
            }
        });
    }
    @Override
    public void OnLoginSuccess(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();

        //Call Main activity
        Intent i=new Intent(LoginActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }
    @Override
    public void OnLoginError(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnSignup() {
        //Call register activity
        Intent i=new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(i);
    }

    @Override
    public void onBackPressed() {
     //   super.onBackPressed();
    }
}