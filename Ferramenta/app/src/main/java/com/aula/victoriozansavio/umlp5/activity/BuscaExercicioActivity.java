package com.aula.victoriozansavio.umlp5.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.aula.victoriozansavio.umlp5.API.ExerciseServiceAPI;
import com.aula.victoriozansavio.umlp5.API.UserServiceAPI;
import com.aula.victoriozansavio.umlp5.R;
import com.aula.victoriozansavio.umlp5.activity.util.RetrofitBuilder;
import com.aula.victoriozansavio.umlp5.activity.util.Utils;
import com.aula.victoriozansavio.umlp5.adapter.BuscaExercicioAdapter;
import com.aula.victoriozansavio.umlp5.adapter.BuscaProfessorAdapter;
import com.aula.victoriozansavio.umlp5.library.Exercise;
import com.aula.victoriozansavio.umlp5.library.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BuscaExercicioActivity extends AppCompatActivity {

    User professor = new User();
    EditText edtExercicio;
    TextView tvNomeProfessor;


    String filter = "";

    RecyclerView recyclerViewProfessores;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    String token;

    List<Exercise> exerciseList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String id = Utils.getId(getBaseContext());
        token = Utils.getToken(getBaseContext());
        if(!Utils.verifyUserTokenValidation(id, token, getBaseContext())){
            Utils.redirectToLoginPage(getBaseContext());
        }
        setContentView(R.layout.activity_busca_exercicio);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            this.professor = (User) bundle.getSerializable("professor");
        }

        initViews();

    }


    private void initViews(){
        edtExercicio = findViewById(R.id.activity_busca_exercicioR_edtExer);
        tvNomeProfessor = findViewById(R.id.activity_busca_exercicioR_tvNome);
        tvNomeProfessor.setText(professor.getNome());

        configRecyclerView();
        updateRecyclerView();

        edtExercicio.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter = edtExercicio.getText().toString();
                Log.i("App", "Text: " + filter);
                updateRecyclerView();
            }
        });



    }

    private void updateRecyclerView() {
        buscar();
    }

    private void configRecyclerView() {
        recyclerViewProfessores = findViewById(R.id.activity_busca_exercicioR_rvExer);
        recyclerViewProfessores.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new BuscaExercicioAdapter(exerciseList, this);
        recyclerViewProfessores.setLayoutManager(mLayoutManager);
        recyclerViewProfessores.setAdapter(mAdapter);
    }

    private void buscar(){
        Retrofit retrofit = RetrofitBuilder.build(GsonConverterFactory.create());
        ExerciseServiceAPI serviceAPI = retrofit.create(ExerciseServiceAPI.class);

        serviceAPI.getExerciseByAuthor(token,
                professor.getId(), filter).enqueue(new Callback<List<Exercise>>() {
            @Override
            public void onResponse(Call<List<Exercise>> call, Response<List<Exercise>> response) {
                Log.i("App", "URL: ");
                if (response.isSuccessful()){
                    Log.i("App", "Deu certo!");
                    exerciseList.clear();
                    exerciseList.addAll(response.body());
                    mAdapter.notifyDataSetChanged();
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
