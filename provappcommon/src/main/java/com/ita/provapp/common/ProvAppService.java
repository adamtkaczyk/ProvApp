package com.ita.provapp.common;


import com.ita.provapp.common.json.Credential;
import com.ita.provapp.common.json.LoginUser;
import com.ita.provapp.common.json.NewUser;
import com.ita.provapp.common.json.Order;
import com.ita.provapp.common.json.Product;
import com.ita.provapp.common.json.User;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ProvAppService {

    @GET("users/{userId}")
    Call<User> getUser(@Header("Authorization") String token, @Path("userId") String userId);

    @POST("users/authtokens")
    Call<LoginUser> signIn(@Body Credential credential);

    @POST("users")
    Call<ResponseBody> signUp(@Body NewUser user);

    @GET("products/{productId}")
    Call<Product> getProduct(@Path("productId") String productId);

    @GET("products")
    Call<List<Product>> getProducts();

    @POST("orders")
    Call<ResponseBody> createOrder(@Header("Authorization") String token, @Body Order order);
}
