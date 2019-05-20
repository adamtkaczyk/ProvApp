package com.ita.provapp.common;

import com.google.gson.Gson;
import com.ita.provapp.common.exceptions.ServerException;
import com.ita.provapp.common.json.ErrorMessage;
import com.ita.provapp.common.json.User;

import java.io.IOException;
import java.net.ConnectException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AccountAPIClient {

    public AccountAPIClient() {
        this.url = "http://192.168.1.14:8080/";
    }

    public AccountAPIClient(String url) {
        this.url = url;
    }

    private String url;

    public User getUser(String token, String userId) throws IOException, ServerException {
        User user = null;
        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ProvAppService service = retrofit.create(ProvAppService.class);

            Call<User> call = service.getUser(token, userId);

            Response<User> response = call.execute();
            if(response.isSuccessful()) {
                user = response.body();
            } else {
                Gson gson = new Gson();
                ErrorMessage loginError = gson.fromJson(response.errorBody().string(),ErrorMessage.class);
                throw new ServerException(loginError);
               // System.out.println("response is not successful");
            }
        } catch (IOException e) {
            throw new IOException("Error with connection to server: " + url);
        }

        return user;
    }
}
