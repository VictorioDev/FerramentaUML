package com.aula.victoriozansavio.umlp5.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.aula.victoriozansavio.umlp5.API.SubmissionServiceAPI;
import com.aula.victoriozansavio.umlp5.R;
import com.aula.victoriozansavio.umlp5.activity.util.RetrofitBuilder;
import com.aula.victoriozansavio.umlp5.activity.util.Utils;
import com.aula.victoriozansavio.umlp5.adapter.SubmissoesAdapter;
import com.aula.victoriozansavio.umlp5.library.Submission;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SubmissoesActivity extends AppCompatActivity {

    RecyclerView recyclerViewProfessores;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    List<Submission> submissionList = new ArrayList<>();

    String token;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        token = Utils.getToken(getBaseContext());
        id = Utils.getId(getBaseContext());

        if(!Utils.verifyUserTokenValidation(id, token, getBaseContext())){
            Utils.redirectToLoginPage(getBaseContext());
            finish();
        }

        setContentView(R.layout.activity_submissoes);
        configRecyclerView();
        updateRecyclerView();

    }

    private void configRecyclerView(){
        recyclerViewProfessores = findViewById(R.id.activity_submissoes_rv);
        recyclerViewProfessores.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new SubmissoesAdapter(submissionList, this);
        recyclerViewProfessores.setLayoutManager(mLayoutManager);
        recyclerViewProfessores.setAdapter(mAdapter);
    }


    private void updateRecyclerView(){
        buscar();
    }

    /*
     * 1 - Admin
     * 2 - Professor
     * 3 - Aluno
     * */
    private void buscar(){
        Retrofit retrofit = RetrofitBuilder.build(GsonConverterFactory.create());
        SubmissionServiceAPI serviceAPI = retrofit.create(SubmissionServiceAPI.class);
        serviceAPI.getSubmissionsByAuthor(token
                , id).enqueue(new Callback<List<Submission>>() {
            @Override
            public void onResponse(Call<List<Submission>> call, Response<List<Submission>> response) {
                Log.i("App", "URL: ");
                if (response.isSuccessful()){
                    Log.i("App", "Deu certo!");
                    submissionList.clear();
                    submissionList.addAll(response.body());
                    mAdapter.notifyDataSetChanged();
                    Log.i("App", "Size: " + submissionList.size() );
                    for(Submission submission : submissionList)
                        Log.i("App", "UserName: " + submission.getExercise().getTitle());
                }else {
                    try {
                        Log.i("App", "Erro:  " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Submission>> call, Throwable t) {
                Log.i("App", "Falhou: " + t.getMessage());
            }
        });

    }


}
