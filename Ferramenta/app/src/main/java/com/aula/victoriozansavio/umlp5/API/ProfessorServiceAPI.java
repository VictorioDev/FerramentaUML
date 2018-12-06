package com.aula.victoriozansavio.umlp5.API;

import com.aula.victoriozansavio.umlp5.library.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface ProfessorServiceAPI {

    @GET("users")
    Call<List<User>>getProfessores(@Header("x-access-token") String token, @Query("s") String filter );
}
