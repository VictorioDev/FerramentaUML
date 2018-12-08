package com.aula.victoriozansavio.umlp5.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.aula.victoriozansavio.umlp5.R;
import com.aula.victoriozansavio.umlp5.activity.util.Utils;
import com.aula.victoriozansavio.umlp5.library.User;

public class HomePageProfessorActivity extends AppCompatActivity {

    TextView tvNome;
    TextView tvInstituicao;
    View containerEx;
    TextView tvSair;
    ImageView ivSair;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_professor);

        initViews();

        Bundle bundle = getIntent().getExtras();

        if(bundle != null){
            User userLogged = (User) bundle.get("user");
            tvNome.setText(userLogged.getNome());
            tvInstituicao.setText(userLogged.getOrganization());
        }
    }

    private void initViews() {
        tvNome = findViewById(R.id.activity_home_page_professor_tvNome);
        tvInstituicao = findViewById(R.id.activity_home_page_professor_tvInst);
        containerEx = findViewById(R.id.activity_home_page_professor_llExer);
        tvSair = findViewById(R.id.activity_home_page_professor_tvSair);
        ivSair = findViewById(R.id.activity_home_page_professor_ivSair);

        containerEx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =  new Intent(getBaseContext(), SeusExerciciosActivity.class);
                startActivity(i);
            }
        });

        ivSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.logOut(getBaseContext());
                finish();
            }
        });

        tvSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.logOut(getBaseContext());
                finish();
            }
        });
    }
}
