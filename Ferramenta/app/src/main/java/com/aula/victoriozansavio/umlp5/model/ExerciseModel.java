package com.aula.victoriozansavio.umlp5.model;

import android.util.Log;

import com.aula.victoriozansavio.umlp5.API.ExerciseServiceAPI;
import com.aula.victoriozansavio.umlp5.util.RetrofitBuilder;
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
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ExerciseModel {

    public static void getExercisesByAuthor(String token, String id, String filter, final ExerciseActionInterface exerciseActionInterface){
        Retrofit retrofit = RetrofitBuilder.build(GsonConverterFactory.create());
        ExerciseServiceAPI serviceAPI = retrofit.create(ExerciseServiceAPI.class);

        Log.i("App", "ID: " + id + " FIlter: " + filter);
        serviceAPI.getExerciseByAuthor(token,
                id, filter).enqueue(new Callback<List<Exercise>>() {
            @Override
            public void onResponse(Call<List<Exercise>> call, Response<List<Exercise>> response) {
                if (response.isSuccessful()){
                    List<Exercise> exerciseList = new ArrayList<>();
                    Log.i("App", "Deu certo!");
                    exerciseList = response.body();
                    exerciseActionInterface.onExercisesRetrieved(exerciseList);
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

    public static void saveExercise(String token, final Exercise exercise, final ExerciseActionInterface exerciseActionInterface){
        Retrofit retrofit = RetrofitBuilder.build(GsonConverterFactory.create());
        ExerciseServiceAPI  exerciseServiceAPI = retrofit.create(ExerciseServiceAPI.class);

        exerciseServiceAPI.saveExercies(token, exercise).enqueue(new Callback<Exercise>() {
            @Override
            public void onResponse(Call<Exercise> call, Response<Exercise> response) {
                if (response.isSuccessful()){
                    Log.i("App", "Deu certo! \n Body: " + response.body());

                    exerciseActionInterface.onExerciseSaved();
                }else {
                    try {
                        Log.i("App", "Erro save exercise:  " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Exercise> call, Throwable t) {
                Log.i("App", "Falhou SaveExercise: " + t.getMessage());
            }
        });

    }

    public static void deleteExercise(String token, String id, final int position, final ExerciseActionInterface exerciseActionInterface){
        Retrofit retrofit = RetrofitBuilder.build(ScalarsConverterFactory.create());
        ExerciseServiceAPI exerciseServiceAPI = retrofit.create(ExerciseServiceAPI.class);
        exerciseServiceAPI.deleteExercise(token, id).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()){
                    exerciseActionInterface.onExerciseDeleted(position);
                    Log.i("App", "Deu certo! \n Body: " + response.body());
                }else {
                    try {
                        Log.i("App", "Erro:  " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("App", "Falhou: " + t.getMessage());
            }
        });

    }

    public static void editExercise(String token, String id, Exercise exercise, final ExerciseActionInterface exerciseActionInterface){
        Retrofit retrofit = RetrofitBuilder.build(GsonConverterFactory.create());
        ExerciseServiceAPI exerciseServiceAPI = retrofit.create(ExerciseServiceAPI.class);
        exerciseServiceAPI.editExercise(token, id, exercise).enqueue(new Callback<Exercise>() {
            @Override
            public void onResponse(Call<Exercise> call, Response<Exercise> response) {
                if (response.isSuccessful()){
                    Log.i("App", "Deu certo! \n Body: " + response.body());
                    exerciseActionInterface.onExerciseEdited();
                }else {
                    try {
                        Log.i("App", "Erro Edit Exercise:  " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Exercise> call, Throwable t) {
                Log.i("App", "Falhou EditExercise: " + t.getMessage());
            }
        });

    }
}
