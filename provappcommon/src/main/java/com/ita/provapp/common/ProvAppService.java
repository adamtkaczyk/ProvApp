package com.ita.provapp.common;


import com.ita.provapp.common.json.Credential;
import com.ita.provapp.common.json.LoginUser;
import com.ita.provapp.common.json.NewUser;
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

    @GET("user/{userId}")
    Call<User> getUser(@Header("Authorization") String token, @Path("userId") String userId);

    @POST("user/authtoken")
    Call<LoginUser> signIn(@Body Credential credential);

    @POST("user")
    Call<ResponseBody> signUp(@Body NewUser user);

    @GET("product/{productId}")
    Call<Product> getProduct(@Path("productId") String productId);

    @GET("product")
    Call<List<Product>> listProduct();
}
