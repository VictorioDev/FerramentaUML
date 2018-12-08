package com.aula.victoriozansavio.umlp5.model;

import android.util.Log;

import com.aula.victoriozansavio.umlp5.API.ExerciseServiceAPI;
import com.aula.victoriozansavio.umlp5.activity.util.RetrofitBuilder;
import com.aula.victoriozansavio.umlp5.inteface.ExerciseActionInterface;
import com.aula.victoriozansavio.umlp5.library.Exercise;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ExerciseModel {

    public static void getExercisesByAuthor(String token, String id, String filter, final ExerciseActionInterface exerciseActionInterface){
        Retrofit retrofit = RetrofitBuilder.build(GsonConverterFactory.create());
        ExerciseServiceAPI serviceAPI = retrofit.create(ExerciseServiceAPI.class);

        serviceAPI.getExerciseByAuthor(token,
                id, filter).enqueue(new Callback<List<Exercise>>() {
            @Override
            public void onResponse(Call<List<Exercise>> call, Response<List<Exercise>> response) {
                if (response.isSuccessful()){
                    List<Exercise> exerciseList = new ArrayList<>();
                    Log.i("App", "Deu certo!");
                    exerciseActionInterface.workWithExercises(exerciseList);
                    Log.i("App", "Size: " + exerciseList.size());
                    for(Exercise exercise: exerciseList)
                        Log.i("App", "UserName: " + exercise.getTitle());
                }else {
                    try {
                        Log.i("App", "Erro:  " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Exercise>> call, Throwable t) {
                Log.i("App", "Falhou: " + t.getMessage());
            }
        });

    }
}
