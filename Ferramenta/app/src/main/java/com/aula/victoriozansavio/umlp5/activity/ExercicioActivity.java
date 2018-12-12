package com.aula.victoriozansavio.umlp5.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.aula.victoriozansavio.umlp5.R;
import com.aula.victoriozansavio.umlp5.library.Exercise;

public class ExercicioActivity extends AppCompatActivity {

    TextView tvTitle;
    TextView tvTipoDiag;
    TextView tvAuthor;
    TextView tvInst;
    TextView tvDesc;
    Button btnResolver;

    Exercise exercise = new Exercise();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercicio);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            exercise = (Exercise) bundle.getSerializable("exercise");
        }

        initViews();


    }

    private void initViews() {
        tvTitle = findViewById(R.id.activity_exercicio_visu_tvTitle);
        tvTipoDiag = findViewById(R.id.activity_exercicio_visu_tvTipo);
        tvAuthor = findViewById(R.id.activity_exercicio_visu_tvAuthor);
        tvInst = findViewById(R.id.activity_exercicio_visu_tvInst);
        tvDesc = findViewById(R.id.activity_exercicio_visu_tvDesc);
        btnResolver = findViewById(R.id.activity_exercicio_visu_btnResolver);

        tvTitle.setText(exercise.getTitle());
        if(exercise.getType() == 1){
            tvTipoDiag.setText("Diagrama de Caso de Uso");
        }else {
            tvTipoDiag.setText("Diagrama de Classe");
        }
        tvAuthor.setText("Autor: " + exercise.getAuthor().getNome());
        tvInst.setText("Instituição: " + exercise.getAuthor().getOrganization());
        tvDesc.setText(exercise.getDescription());

        btnResolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i;

                i = new Intent(getBaseContext(), CasoDeUsoActivity.class);

                i.putExtra("exercise", exercise);
                i.putExtra("salvar", true);
                i.putExtra("sub", true);
                startActivity(i);
            }
        });
    }
}
