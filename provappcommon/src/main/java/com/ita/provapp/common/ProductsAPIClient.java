package com.ita.provapp.common;

import com.google.gson.Gson;
import com.ita.provapp.common.exceptions.ServerException;
import com.ita.provapp.common.json.ErrorMessage;
import com.ita.provapp.common.json.Product;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

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

    public ArrayList<Product> getProducts() throws IOException, ServerException {
        ArrayList<Product> products = new ArrayList<>();
        try {
            Call<List<Product>> call = service.getProducts();
            Response<List<Product>> response = call.execute();
            if(response.isSuccessful()) {
                List<Product> products1 = response.body();
                products.addAll(products1);
            } else {
                Gson gson = new Gson();
                ErrorMessage error = gson.fromJson(response.errorBody().string(), ErrorMessage.class);
                throw new ServerException(error);
            }
        } catch (IOException e) {
            throw new IOException("Error with connection to server: " + url);
        }

        return products;
    }
}
