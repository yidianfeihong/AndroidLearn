package com.example.mvxdemo.presenter;

import com.example.mvxdemo.OnLoginListener;
import com.example.mvxdemo.model.ILoginModel;
import com.example.mvxdemo.model.LoginModelImpl;
import com.example.mvxdemo.model.User;
import com.example.mvxdemo.view.ILoginView;

/***********************************************************
 ** Copyright (C), 2010-2020, Oplus. All rights reserved.
 ** File: AndroidLearn
 ** Description: 
 ** Version: 1.0
 ** Date : 2022-06-29
 ** Author: shiwenming
 **
 ** ---------------------Revision History: ---------------------
 ** <author> <date> <version > <desc>
 ** shiwenming 2022/05/23 1.0 create
 ****************************************************************/
public class LoginPresenterImpl implements ILoginPresenter {

    ILoginView mLoginView;
    ILoginModel mLoginMode;

    public LoginPresenterImpl(ILoginView loginView) {
        mLoginView = loginView;
        mLoginMode = new LoginModelImpl();
    }

    @Override
    public void clear() {
        mLoginView.clearText();
    }

    @Override
    public void login(User user) {
        mLoginView.showProgress();
        mLoginMode.requestLogin(user, new OnLoginListener() {
            @Override
            public void onSuccess() {
                mLoginView.hideProgress();
                mLoginView.showLoginState("onSuccess");
            }

            @Override
            public void onFailed() {
                mLoginView.hideProgress();
                mLoginView.showLoginState("onFailed");
            }
        });
    }

}
