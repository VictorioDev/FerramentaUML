package com.aula.victoriozansavio.umlp5.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.aula.victoriozansavio.umlp5.R;
import com.aula.victoriozansavio.umlp5.library.User;

public class HomePageAlunoActivity extends AppCompatActivity {

    TextView tvNome;
    TextView tvEmail;

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
        tvEmail = (TextView) findViewById(R.id.activity_hp_alu_tvEmail);
        tvNome = (TextView) findViewById(R.id.activity_hp_alu_tvNome);
    }
}
