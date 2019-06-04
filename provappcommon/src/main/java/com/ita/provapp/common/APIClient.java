package com.ita.provapp.common;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ita.provapp.common.exceptions.ServerException;
import com.ita.provapp.common.json.ErrorMessage;
import com.ita.provapp.common.json.Order;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import okhttp3.Headers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class APIClient {
    final protected String url;
    protected ProvAppService service = null;
    protected Gson gson = null;

    public APIClient() {
        this("http://192.168.1.14:8080/");
    }

    public APIClient(String url) {
        this.url = url;
        initialize();
    }

    public void initialize() {
        gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        service = retrofit.create(ProvAppService.class);
    }
}
