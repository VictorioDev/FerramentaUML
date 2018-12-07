package com.aula.victoriozansavio.umlp5.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.aula.victoriozansavio.umlp5.API.ProfessorServiceAPI;
import com.aula.victoriozansavio.umlp5.R;
import com.aula.victoriozansavio.umlp5.activity.util.RetrofitBuilder;
import com.aula.victoriozansavio.umlp5.activity.util.Utils;
import com.aula.victoriozansavio.umlp5.adapter.BuscaProfessorAdapter;
import com.aula.victoriozansavio.umlp5.library.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BuscaProfessorActivity extends AppCompatActivity {

    EditText edtNomeProfessor;


    RecyclerView recyclerViewProfessores;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String token;

    private List<User> userList = new ArrayList<>();

    private String filter = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        token = Utils.getToken(getBaseContext());
        String id = Utils.getId(getBaseContext());
        if(!Utils.verifyUserTokenValidation(id, token, getBaseContext())){
            Utils.redirectToLoginPage(getBaseContext());
        }

        setContentView(R.layout.activity_busca_professor);
        edtNomeProfessor = (EditText) findViewById(R.id.activity_busca_prof_edtNome);



        Log.i("App", "Token:" + token);

        configRecyclerView();
        updateRecyclerView("");

        edtNomeProfessor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter = edtNomeProfessor.getText().toString();
                Log.i("App", "Text: " + filter);
                updateRecyclerView(filter);
            }
        });

    }

    private void configRecyclerView(){
        recyclerViewProfessores = findViewById(R.id.activity_busca_prof_rv);
        recyclerViewProfessores.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new BuscaProfessorAdapter(userList, this);
        recyclerViewProfessores.setLayoutManager(mLayoutManager);
        recyclerViewProfessores.setAdapter(mAdapter);
    }


    private void updateRecyclerView(String filter){
        buscar(filter);
    }

    /*
    * 1 - Admin
    * 2 - Professor
    * 3 - Aluno
    * */
    private void buscar(String filter){
        Retrofit retrofit = RetrofitBuilder.build(GsonConverterFactory.create());
        ProfessorServiceAPI serviceAPI = retrofit.create(ProfessorServiceAPI.class);
        serviceAPI.getProfessores(token
                , filter).enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                Log.i("App", "URL: ");
                if (response.isSuccessful()){
                    Log.i("App", "Deu certo!");
                    userList.clear();
                    userList.addAll(response.body());
                    mAdapter.notifyDataSetChanged();
                    Log.i("App", "Size: " + userList.size() );
                    for(User user: userList)
                        Log.i("App", "UserName: " + user.getNome());
                }else {
                    try {
                        Log.i("App", "Erro:  " + response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.i("App", "Falhou: " + t.getMessage());
            }
        });

    }
}
