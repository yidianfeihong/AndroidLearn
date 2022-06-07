package com.example.mvxdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mvxdemo.model.User;
import com.example.mvxdemo.presenter.ILoginPresenter;
import com.example.mvxdemo.presenter.LoginPresenterImpl;
import com.example.mvxdemo.view.ILoginView;

public class LoginActivity extends AppCompatActivity implements ILoginView, View.OnClickListener {

    private EditText mUsernameEt;
    private EditText mPasswordEt;
    private Button mLoginBtn;
    private Button mClearBtn;

    private ILoginPresenter mLoginPresenter;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUsernameEt = findViewById(R.id.et_username);
        mPasswordEt = findViewById(R.id.et_password);
        mLoginBtn = findViewById(R.id.btn_login);
        mClearBtn = findViewById(R.id.btn_clear);
        mProgressBar = findViewById(R.id.pb_loading);
        mLoginBtn.setOnClickListener(this);
        mClearBtn.setOnClickListener(this);

        mLoginPresenter = new LoginPresenterImpl(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                User user = new User(mUsernameEt.getText().toString(), mPasswordEt.getText().toString());
                mLoginPresenter.login(user);
                break;
            case R.id.btn_clear:
                mLoginPresenter.clear();
                break;
            default:
                break;
        }
    }

    @Override
    public void clearText() {
        mUsernameEt.setText("");
        mPasswordEt.setText("");
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showLoginState(String state) {

        Toast.makeText(this, "login:" + state, Toast.LENGTH_SHORT).show();
    }

}