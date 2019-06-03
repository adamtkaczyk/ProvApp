package com.ita.provapp.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ita.provapp.common.exceptions.ServerException;
import com.ita.provapp.common.json.ErrorMessage;
import com.ita.provapp.common.json.Order;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import okhttp3.Headers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OrderAPIClient {

    final private String url;

    public OrderAPIClient() {
        this.url = "http://192.168.1.14:8080/";
    }

    public OrderAPIClient(String url) {
        this.url = url;
    }

    public Integer createOrder(String authToken, Order order) throws IOException, ServerException {
        Integer orderId = null;
        try {
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd HH:mm:ss")
                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();

            ProvAppService service = retrofit.create(ProvAppService.class);

            Call<ResponseBody> call = service.createOrder(authToken, order);

            Response<ResponseBody> response = call.execute();
            if(response.isSuccessful()) {
                Headers headers = response.headers();
                String location = headers.get("Location");
                URI uri = new URI(location);
                String path = uri.getPath();
                String idStr = path.substring(path.lastIndexOf('/') + 1);
                orderId = Integer.parseInt(idStr);
            } else {
                ErrorMessage error = gson.fromJson(response.errorBody().string(),ErrorMessage.class);
                throw new ServerException(error);
            }
        } catch (IOException e) {
            throw new IOException("Error with connection to server: " + url);
        } catch (URISyntaxException e) {
            throw new ServerException(new ErrorMessage("Incorrect response message"));
        }

        return orderId;
    }
}
