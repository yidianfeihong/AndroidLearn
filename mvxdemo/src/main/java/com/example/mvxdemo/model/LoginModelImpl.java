package com.example.mvxdemo.model;

import android.os.Handler;
import android.text.TextUtils;

import com.example.mvxdemo.OnLoginListener;

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
public class LoginModelImpl implements ILoginModel {
    @Override
    public void requestLogin(User user, OnLoginListener listener) {
        final String username = user.getUsername();
        final String password = user.getPassword();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (TextUtils.isEmpty(username)) {
                    listener.onFailed();//model层里面回调listener
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    listener.onFailed();
                    return;
                }
                listener.onSuccess();
            }
        }, 2000);
    }
}
