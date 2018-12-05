package com.aula.victoriozansavio.umlp5.API;

import com.aula.victoriozansavio.umlp5.library.LoginResult;
import com.aula.victoriozansavio.umlp5.library.User;

import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Victorio Zansavio on 04/12/2018.
 */

public interface UserServiceAPI {


    @POST("login")
    Call<LoginResult> login(@Body User user);

    @GET("users/{id}")
    Call<String> getUser(@Path("id") String id, @Header("x-access-token") String token);

    @POST("users")
    Call<User> register(@Body User user);
}
