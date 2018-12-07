package com.aula.victoriozansavio.umlp5.API;

import com.aula.victoriozansavio.umlp5.library.Submission;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface SubmissionServiceAPI {

    @GET("submissions/author/{id}")
    Call<List<Submission>> getSubmissionsByAuthor(@Header("x-access-tokne") String token, @Path("id") String id);

    @POST("submissions")
    Call<Submission> saveSubmission(@Header("x-access-tokne") String token, @Body Submission submission);
}
