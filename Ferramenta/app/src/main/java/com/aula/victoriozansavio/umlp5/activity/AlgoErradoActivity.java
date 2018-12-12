package com.aula.victoriozansavio.umlp5.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.aula.victoriozansavio.umlp5.R;
import com.aula.victoriozansavio.umlp5.util.Utils;

public class AlgoErradoActivity extends AppCompatActivity {

    Button btnErrado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algo_errado);
        btnErrado = findViewById(R.id.activity_errado_btnLogin);

        btnErrado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.redirectToLoginPage(getBaseContext());
            }
        });
    }
}
