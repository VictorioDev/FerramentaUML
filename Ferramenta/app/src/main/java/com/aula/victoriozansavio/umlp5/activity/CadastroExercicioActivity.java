package com.aula.victoriozansavio.umlp5.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.aula.victoriozansavio.umlp5.R;
import com.aula.victoriozansavio.umlp5.inteface.UserActionInterface;
import com.aula.victoriozansavio.umlp5.library.Exercise;
import com.aula.victoriozansavio.umlp5.library.User;
import com.aula.victoriozansavio.umlp5.model.UserModel;

public class CadastroExercicioActivity extends AppCompatActivity {

    Spinner spnTpDiagrama;
    EditText edtTitulo;
    EditText edtDesc;
    Button btnCadCorre;

    int tipoDiag = 0;
    String titulo = "";
    String descricao = "";
    String id = "";
    String token = "";

    User professor = new User();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_exercicio);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            professor = (User) bundle.getSerializable("professor");
        }

        initViews();
    }

    private void initViews() {
        spnTpDiagrama = findViewById(R.id.activity_cadastro_exercicio_spnTipDiag);
        edtTitulo = findViewById(R.id.activity_cadastro_exercicio_edtTitExer);
        edtDesc = findViewById(R.id.activity_cadastro_exercicio_edtDescExer);
        btnCadCorre = findViewById(R.id.activity_cadastro_exercicio_btnCadastrarExercicio);

        btnCadCorre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validaCampos()){
                    Intent i;
                    Exercise exercise = buildObject();
                    if(exercise.getType() == 1){
                        i = new Intent(getBaseContext(), CasoDeUsoActivity.class);
                    }else {
                        i = new Intent(getBaseContext(), ClasseActivity.class);
                    }
                    i.putExtra("exercise", exercise);
                    startActivity(i);
                }
            }
        });


        String tpDiag[] = {"<<Selecione>>", "Diagrama de Caso de Uso", "Diagrama de Classe"};
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,R.layout.spinner_item, tpDiag);
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
        spnTpDiagrama.setAdapter(spinnerArrayAdapter);

    }

    private Exercise buildObject() {
        Exercise exercise = new Exercise();
        exercise.setAuthor(professor);
        exercise.setDescription(descricao);
        exercise.setTitle(titulo);
        exercise.setType(tipoDiag);
        return exercise;
    }


    private boolean validaCampos(){
        tipoDiag = 0;
        titulo = edtTitulo.getText().toString();
        descricao = edtDesc.getText().toString();
        int posicao = spnTpDiagrama.getSelectedItemPosition();

        if(titulo.isEmpty() || descricao.isEmpty() || spnTpDiagrama.getSelectedItemPosition() == 0){
            Toast.makeText(getBaseContext(),"Existem campos não preenchidos!", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(posicao == 1){
            tipoDiag = 1;
        }else {
            tipoDiag = 2;
        }

        return true;
    }


}
