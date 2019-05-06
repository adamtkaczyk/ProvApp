package com.ita.provapp.authenticator;

import com.ita.provapp.common.LoginUser;

public interface ServerAuthenticate {
    public String userSignUp(final String name, final String email, final String pass, String authType) throws Exception;
    public LoginUser userSignIn(final String username, final String password, String authType) throws Exception;
}
