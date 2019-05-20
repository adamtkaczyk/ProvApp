package com.ita.provapp.app;

import android.app.Application;
import android.content.Context;
import com.ita.provapp.common.json.LoginUser;

public class ProvApplication extends Application {
    private static Context mContext;
    LoginUser loginUser;// = new User();

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public LoginUser getLoginUser() {
        return loginUser;
    }

    public void setLoginUser(LoginUser loginUser) {
        this.loginUser = loginUser;
    }
}
