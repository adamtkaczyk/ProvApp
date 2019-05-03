package com.ita.provapp.authenticator;

public interface ServerAuthenticate {
    public String userSignUp(final String name, final String email, final String pass, String authType) throws Exception;
    public LoginUser userSignIn(final String user, final String pass, String authType) throws Exception;
}
