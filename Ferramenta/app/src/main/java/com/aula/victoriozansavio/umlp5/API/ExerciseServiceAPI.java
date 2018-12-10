package com.aula.victoriozansavio.umlp5.API;

import com.aula.victoriozansavio.umlp5.library.Exercise;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ExerciseServiceAPI {

    @GET("exercises/author/{id}")
    Call<List<Exercise>> getExerciseByAuthor(@Header("x-access-token") String token, @Path("id") String id, @Query("s") String filter);

    @POST("exercises")
    Call<String> saveExercies(@Header("x-access-token") String token, @Body Exercise exercise);
}
