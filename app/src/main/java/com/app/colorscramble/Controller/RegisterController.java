package com.app.colorscramble.Controller;


import android.text.TextUtils;

import com.app.colorscramble.Model.User;
import com.app.colorscramble.View.IRegisterView;

public class RegisterController implements IRegisterController {
    IRegisterView iRegisterView;


    public RegisterController(IRegisterView iRegisterView) {
        this.iRegisterView = iRegisterView;
    }


    @Override
    public void OnRegister(String name, String email, String password, String confirm_password) {
        User user = new User(name,email,password,confirm_password);
        int registerCode = user.isValid();

        if(TextUtils.isEmpty(name)) {
            iRegisterView.OnRegisterError("Please enter Username");

        }
        else if(registerCode == 0)
        {
            iRegisterView.OnRegisterError("Please enter Email");
        }else  if (registerCode == 1){
            iRegisterView.OnRegisterError("Please enter A valid Email");
        } else  if (registerCode == 2)
        {
            iRegisterView.OnRegisterError("Please enter Password");
        }else  if(registerCode == 3){
            iRegisterView.OnRegisterError("Please enter Password greater the 6 char");
        }else  if (TextUtils.isEmpty(confirm_password))
        {
            iRegisterView.OnRegisterError("Please enter Confirm Password");
        }else  if(confirm_password.length()<=6){
            iRegisterView.OnRegisterError("Please enter Confirm Password greater the 6 char");
        }else  if(!confirm_password.equals(password)){
            iRegisterView.OnRegisterError("Password and Confirm Password not match");
        }
        else {
            iRegisterView.OnRegisterSuccess("Registeration Successful");
        }
    }

    @Override
    public void OnLogin() {
        iRegisterView.OnLogIn();
    }
}
