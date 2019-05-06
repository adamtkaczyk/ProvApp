package com.ita.provapp.common;


import java.io.IOException;
import java.io.UnsupportedEncodingException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductsAPIClient {
    final String url = "http://192.168.1.14:8080/";

    public Product getProduct(String productId) {
        Product product = null;
        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ProvAppService service = retrofit.create(ProvAppService.class);

            Call<Product> call = service.getProduct("product1");

            Response<Product> response = call.execute();
            if(response.isSuccessful()) {
                product = response.body();
            } else {
                System.out.println("response is not successful");
                //TODO: Error handling
            }
        } catch (IOException e) {
            //TODO: Error handle
            e.printStackTrace();
        }

        return product;
    }
}
