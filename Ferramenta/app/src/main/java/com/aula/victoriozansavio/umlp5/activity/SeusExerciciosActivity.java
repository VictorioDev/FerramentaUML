package com.aula.victoriozansavio.umlp5.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.aula.victoriozansavio.umlp5.R;
import com.aula.victoriozansavio.umlp5.util.Utils;
import com.aula.victoriozansavio.umlp5.adapter.SeusExerciciosAdapter;
import com.aula.victoriozansavio.umlp5.inteface.ExerciseActionInterface;
import com.aula.victoriozansavio.umlp5.library.Exercise;
import com.aula.victoriozansavio.umlp5.library.User;
import com.aula.victoriozansavio.umlp5.model.ExerciseModel;

import java.util.ArrayList;
import java.util.List;

public class SeusExerciciosActivity extends AppCompatActivity implements ExerciseActionInterface {

    TextView tvNome;
    EditText edtBuscaEx;

    ImageView ivNovo;

    String filter = "";
    String token = "";
    String id = "";
    User professor = new User();
    RecyclerView recyclerViewProfessores;
    ExerciseActionInterface exerciseActionInterface;



    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    List<Exercise>  exerciseList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        exerciseActionInterface = this;
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
        edtBuscaEx = findViewById(R.id.activity_seus_exercios_edtBuscaEx);
        ivNovo = findViewById(R.id.activity_seus_exercios_ivNovo);

        edtBuscaEx.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter = edtBuscaEx.getText().toString();
                ExerciseModel.getExercisesByAuthor(token, id, filter, exerciseActionInterface);
            }
        });

        ivNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), CadastroExercicioActivity.class);
                i.putExtra("professor", professor);
                i.putExtra("salvar", true);
                startActivity(i);
            }
        });
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
    public void onExercisesRetrieved(List<Exercise> exerciseList) {
        updateRecyclerView(exerciseList);
    }

    @Override
    public void onExerciseDeleted(int position) {

    }

    @Override
    public void onExerciseSaved() {

    }

    @Override
    public void onExerciseEdited() {

    }
}
