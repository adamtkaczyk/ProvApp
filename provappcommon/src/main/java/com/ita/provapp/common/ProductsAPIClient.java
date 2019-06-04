package com.ita.provapp.common;

import com.google.gson.Gson;
import com.ita.provapp.common.exceptions.ServerException;
import com.ita.provapp.common.json.ErrorMessage;
import com.ita.provapp.common.json.Product;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class ProductsAPIClient extends APIClient {

    public ProductsAPIClient() {
        super();
    }

    public ProductsAPIClient(String url) {
        super(url);
    }

    public Product getProduct(String productId) throws IOException, ServerException {
        Product product = null;
        try {
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
