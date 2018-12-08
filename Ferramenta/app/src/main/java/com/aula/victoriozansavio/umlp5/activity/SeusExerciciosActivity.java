package com.aula.victoriozansavio.umlp5.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.TextView;

import com.aula.victoriozansavio.umlp5.R;
import com.aula.victoriozansavio.umlp5.activity.util.Utils;
import com.aula.victoriozansavio.umlp5.adapter.SeusExerciciosAdapter;
import com.aula.victoriozansavio.umlp5.inteface.ExerciseActionInterface;
import com.aula.victoriozansavio.umlp5.library.Exercise;
import com.aula.victoriozansavio.umlp5.library.User;
import com.aula.victoriozansavio.umlp5.model.ExerciseModel;

import java.util.ArrayList;
import java.util.List;

public class SeusExerciciosActivity extends AppCompatActivity implements ExerciseActionInterface {

    TextView tvNome;
    TextView tvQtdEx;
    EditText edtBuscaEx;

    String filter = "";
    String token = "";
    String id = "";
    User professor = new User();
    RecyclerView recyclerViewProfessores;


    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    List<Exercise>  exerciseList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = Utils.getId(getBaseContext());
        token = Utils.getToken(getBaseContext());
        if(!Utils.verifyUserTokenValidation(id, token, getBaseContext())){
            Utils.redirectToLoginPage(getBaseContext());
        }
        setContentView(R.layout.activity_seus_exercicios);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            this.professor = (User) bundle.getSerializable("professor");
        }

        initViews();
        configRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        ExerciseModel.getExercisesByAuthor(token, id, filter, this);
    }

    private void initViews() {
        tvNome = findViewById(R.id.activity_seus_exercios_tvNome);
        tvQtdEx = findViewById(R.id.activity_seus_exercios_tvQtdEx);
        edtBuscaEx = findViewById(R.id.activity_seus_exercios_edtBuscaEx);

    }

    private void updateRecyclerView(List<Exercise> exercises) {
        exerciseList.clear();
        exerciseList.addAll(exercises);
        mAdapter.notifyDataSetChanged();
    }

    private void configRecyclerView() {
        recyclerViewProfessores = findViewById(R.id.activity_seus_exercios_rvExercicios);
        recyclerViewProfessores.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new SeusExerciciosAdapter(exerciseList, this);
        recyclerViewProfessores.setLayoutManager(mLayoutManager);
        recyclerViewProfessores.setAdapter(mAdapter);
    }

    @Override
    public void workWithExercises(List<Exercise> exerciseList) {
        updateRecyclerView(exerciseList);
    }
}
