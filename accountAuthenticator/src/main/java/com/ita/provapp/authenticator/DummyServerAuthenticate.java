package com.ita.provapp.authenticator;

import com.ita.provapp.common.json.LoginUser;
import com.ita.provapp.common.json.NewUser;
import com.ita.provapp.common.json.User;

public class DummyServerAuthenticate implements ServerAuthenticate {
    @Override
    public String userSignUp(NewUser user) throws Exception {
        return "1234";
    }

    @Override
    public LoginUser userSignIn(String user, String pass, String authType) throws Exception {
        return new LoginUser(new User("user","","",""),"1234");
    }
}
