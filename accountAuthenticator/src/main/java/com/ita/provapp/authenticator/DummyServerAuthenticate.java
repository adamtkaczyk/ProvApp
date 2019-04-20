package com.ita.provapp.authenticator;

public class DummyServerAuthenticate implements ServerAuthenticate {
    @Override
    public String userSignUp(String name, String email, String pass, String authType) throws Exception {
        return "1234";
    }

    @Override
    public String userSignIn(String user, String pass, String authType) throws Exception {
        return "1234";
    }
}
