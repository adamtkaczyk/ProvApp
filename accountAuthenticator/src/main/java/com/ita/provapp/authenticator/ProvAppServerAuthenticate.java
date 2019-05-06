package com.ita.provapp.authenticator;

import com.google.gson.Gson;
import com.ita.provapp.common.Credential;
import com.ita.provapp.common.ErrorMessage;
import com.ita.provapp.common.LoginUser;
import com.ita.provapp.common.ProvAppService;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.io.Serializable;

public class ProvAppServerAuthenticate implements ServerAuthenticate {

    final String url = "http://192.168.1.14:8080/";

    @Override
    public LoginUser userSignIn(String username, String password, String authType) throws Exception {
        LoginUser user = null;
        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ProvAppService service = retrofit.create(ProvAppService.class);

            Credential credential = new Credential();
            credential.setUser(username);
            credential.setPassword(password);
            Call<LoginUser> call = service.SingIn(credential);

            Response<LoginUser> response = call.execute();

            if(response.code() == 201) {
                user = response.body();
            } else {
                Gson gson = new Gson();
                ErrorMessage loginError = gson.fromJson(response.errorBody().string(),ErrorMessage.class);

                throw new Exception("Error sign in [" + response.code() + "]: " + loginError.getMessage());
            }
        } catch (IOException e) {
            throw new Exception("Error sign in: " + e.getMessage());
        }

        return user;
    }

    @Override
    public String userSignUp(String name, String email, String pass, String authType) throws Exception {

        return "";
    }


    private class ParseComError implements Serializable {
        int code;
        String error;
    }
}

