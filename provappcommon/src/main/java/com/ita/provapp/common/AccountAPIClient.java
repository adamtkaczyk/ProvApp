package com.ita.provapp.common;

import com.google.gson.Gson;
import com.ita.provapp.common.exceptions.ServerException;
import com.ita.provapp.common.json.ErrorMessage;
import com.ita.provapp.common.json.User;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class AccountAPIClient extends APIClient {

    public AccountAPIClient() {
        super();
    }

    public AccountAPIClient(String url) {
        super(url);
    }

    public User getUser(String token, String userId) throws IOException, ServerException {
        User user = null;
        try {
            Call<User> call = service.getUser(token, userId);

            Response<User> response = call.execute();
            if(response.isSuccessful()) {
                user = response.body();
            } else {
                Gson gson = new Gson();
                ErrorMessage loginError = gson.fromJson(response.errorBody().string(),ErrorMessage.class);
                throw new ServerException(loginError);
            }
        } catch (IOException e) {
            throw new IOException("Error with connection to server: " + url);
        }

        return user;
    }
}
