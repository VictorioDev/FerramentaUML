package com.aula.victoriozansavio.umlp5.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.aula.victoriozansavio.umlp5.R;
import com.aula.victoriozansavio.umlp5.library.User;

public class HomePageAlunoActivity extends AppCompatActivity {

    TextView tvNome;
    TextView tvEmail;
    View exerciciosContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_aluno);
        initViews();

        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            User userLogged = (User) bundle.get("user");
            tvNome.setText(userLogged.getNome());
            tvEmail.setText(userLogged.getEmail());
        }

    }

    private void initViews() {
        tvEmail =  findViewById(R.id.activity_hp_alu_tvEmail);
        tvNome =  findViewById(R.id.activity_hp_alu_tvNome);
        exerciciosContainer = findViewById(R.id.activity_hp_alu_llExerc);
        exerciciosContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), BuscaProfessorActivity.class);
                startActivity(intent);
            }
        });

    }
}
