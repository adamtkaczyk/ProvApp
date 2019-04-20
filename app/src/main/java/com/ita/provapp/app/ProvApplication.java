package com.ita.provapp.app;

import android.app.Application;
import android.content.Context;

public class ProvApplication extends Application {
    private static Context mContext;
    User loginUser;// = new User();

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public User getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(User loginUser) {
        this.loginUser = loginUser;
    }
}
