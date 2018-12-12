package com.aula.victoriozansavio.umlp5.model;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.aula.victoriozansavio.umlp5.API.SubmissionServiceAPI;
import com.aula.victoriozansavio.umlp5.inteface.SubmissionActionInterface;
import com.aula.victoriozansavio.umlp5.library.Correction;
import com.aula.victoriozansavio.umlp5.library.Exercise;
import com.aula.victoriozansavio.umlp5.library.Submission;
import com.aula.victoriozansavio.umlp5.util.RetrofitBuilder;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class SubmissionModel {
    public static void doCorrection(String token, String id, final SubmissionActionInterface submissionActionInterface){
        Retrofit retrofit = RetrofitBuilder.build(GsonConverterFactory.create());
        SubmissionServiceAPI submissionServiceAPI = retrofit.create(SubmissionServiceAPI.class);
        Log.i("App", "Submission ID: " + id);
        submissionServiceAPI.doCorrection(token, id).enqueue(new Callback<Correction>() {
            @Override
            public void onResponse(Call<Correction> call, Response<Correction> response) {
                if (response.isSuccessful()){
                    Log.i("App", "Deu certo! \n Body: " + response.body());
                    submissionActionInterface.OnSubmissionCorrection(response.body());
                }else {
                    try {
                        Log.i("App", "Erro Correction Submission:  " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<Correction> call, Throwable t) {
                Log.i("App", "Falhou EditExercise: " + t.getMessage());
            }
        });
    }

    public static void saveSubmission(String token, final Submission submission, final Context context, final SubmissionActionInterface submissionActionInterface){
        Retrofit retrofit = RetrofitBuilder.build(GsonConverterFactory.create());
        SubmissionServiceAPI submissionServiceAPI = retrofit.create(SubmissionServiceAPI.class);

        submissionServiceAPI.saveSubmission(token,submission).enqueue(new Callback<Submission>() {
            @Override
            public void onResponse(Call<Submission> call, Response<Submission> response) {
                Log.i("App", "URL: ");
                if (response.isSuccessful()){
                    Log.i("App", "Deu certo!");
                    Toast.makeText(context, "Salvando Submissao...", Toast.LENGTH_SHORT).show();
                    submissionActionInterface.OnSubmissionSaved(response.body());
                }else {
                    try {
                        Log.i("App", "Erro Save Submission:  " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<Submission> call, Throwable t) {
                Log.i("App", "Falhou Submission: " + t.getMessage());
            }
        });

    }
}
