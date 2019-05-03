package com.ita.provapp.authenticator;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class LoginUser implements Serializable {
    @Expose
    public String authtoken;
    public User user;

    public LoginUser(User user, String authtoken) {
        this.user = user;
        this.authtoken = authtoken;
    }

    public String getAuthtoken() {
        return authtoken;
    }

    public void setAuthtoken(String authtoken) {
        this.authtoken = authtoken;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

