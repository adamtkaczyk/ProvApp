package com.ita.provapp.common;

import com.google.gson.Gson;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AccountAPIClient {
    final String url = "http://192.168.1.14:8080/";

    public User getUser(String token, String userId) throws Exception{
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
                throw new Exception("Error to get user: " + loginError.getMessage());
               // System.out.println("response is not successful");
                //TODO: Error handling
            }
        } catch (IOException e) {
            throw new Exception("Error with connection to server.");
            //TODO: Error handle
            //e.printStackTrace();
        }

        return user;
    }
}
