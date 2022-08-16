package com.app.colorscramble.View;

public interface ILoginView {
    void OnLoginSuccess(String message);
    void OnLoginError(String message);
    void OnSignup();
}