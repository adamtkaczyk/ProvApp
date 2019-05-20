package com.ita.provapp.authenticator;

import com.ita.provapp.common.json.LoginUser;
import com.ita.provapp.common.json.NewUser;

public interface ServerAuthenticate {
    public String userSignUp(final NewUser user) throws Exception;
    public LoginUser userSignIn(final String username, final String password, String authType) throws Exception;
}
