package com.ita.provapp.common;

import com.google.gson.Gson;
import com.ita.provapp.common.exceptions.ServerException;
import com.ita.provapp.common.json.ErrorMessage;
import com.ita.provapp.common.json.Product;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductsAPIClient {

    public ProductsAPIClient() {
        this.url = "http://192.168.1.14:8080/";
    }

    public ProductsAPIClient(String url) {
        this.url = url;
    }

    final String url;

    public Product getProduct(String productId) throws IOException, ServerException {
        Product product = null;
        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ProvAppService service = retrofit.create(ProvAppService.class);

            Call<Product> call = service.getProduct(productId);

            Response<Product> response = call.execute();
            if(response.isSuccessful()) {
                product = response.body();
            } else {
                Gson gson = new Gson();
                ErrorMessage error = gson.fromJson(response.errorBody().string(),ErrorMessage.class);
                throw new ServerException(error);
            }
        } catch (IOException e) {
            throw new IOException("Error with connection to server: " + url);
        }

        return product;
    }
}
