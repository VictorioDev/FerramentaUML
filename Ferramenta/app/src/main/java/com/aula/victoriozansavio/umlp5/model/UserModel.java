package com.aula.victoriozansavio.umlp5.model;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.aula.victoriozansavio.umlp5.API.UserServiceAPI;
import com.aula.victoriozansavio.umlp5.activity.util.RetrofitBuilder;
import com.aula.victoriozansavio.umlp5.inteface.UserActionInterface;
import com.aula.victoriozansavio.umlp5.library.User;
import com.google.gson.Gson;


import org.json.JSONException;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class UserModel {

    public static void getUserById(String id, String token, final Context context, final UserActionInterface userActionInterface) {
        Retrofit retrofit = RetrofitBuilder.build(ScalarsConverterFactory.create());
        UserServiceAPI serviceAPI = retrofit.create(UserServiceAPI.class);
        Log.i("App", "Getting User, ID: " + id);

        serviceAPI.getUser(id, token).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    Log.i("App", "LoginResponse: " + response.body());
                    try {
                        org.json.JSONObject userJson = new org.json.JSONObject(response.body());
                        User user = new Gson().fromJson(userJson.get("user").toString(), User.class);
                        userActionInterface.workWithUser(user);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else {
                    Toast.makeText(context,"Falha ao buscar as informações!", Toast.LENGTH_SHORT ).show();
                    try {
                        Log.i("App", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("App", "Falha na requisição! \n " + t.getMessage());
            }
        });


    }
}