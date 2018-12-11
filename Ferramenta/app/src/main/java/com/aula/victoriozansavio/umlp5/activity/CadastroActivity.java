package com.aula.victoriozansavio.umlp5.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.aula.victoriozansavio.umlp5.API.UserServiceAPI;
import com.aula.victoriozansavio.umlp5.R;
import com.aula.victoriozansavio.umlp5.util.RetrofitBuilder;
import com.aula.victoriozansavio.umlp5.library.User;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CadastroActivity extends AppCompatActivity {

    EditText edtNome;
    EditText edtEmail;
    EditText edtSenha;
    Button btnCadastrar;
    ImageView ivSenha;
    String nome;
    String email;
    String senha;


    boolean passwordView = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        initViews();
    }


    private void register(User user){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Cadastrando, por favor aguarde...");
        progressDialog.show();
       /* Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.100:3000/api/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        UserServiceAPI service = retrofit.create(UserServiceAPI.class);
        service.register("5c071fed3d0a8d1a1868ec62").enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("App", "OnResponse! Code: " + response.code());
                if (response.isSuccessful()) {
                    Log.i("App", "Sucesso!");
                        Log.i("App", response.body());
                } else {
                    try {
                        Log.i("App", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("App", t.getMessage());
            }
        });*/

        Retrofit retrofit = RetrofitBuilder.build(GsonConverterFactory.create());

       /* UserServiceAPI service = retrofit.create(UserServiceAPI.class);
        service.register("5c071fed3d0a8d1a1868ec62", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjVjMDczMzI2ZTA1N2RmMWVjMDlhMDZkYyIsImlhdCI6MTU0Mzk3NTc3NiwiZXhwIjoxNTQ0MDYwMzc2fQ.SU88H2t2A9KrUll9wHL5U19i-tlwhLifPo6k-aZfWDI").enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("App", "OnResponse! Code: " + response.code());
                if (response.isSuccessful()) {
                    Log.i("App", "Sucesso!");
                    Log.i("App", response.body());
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = new JSONObject(response.body());
                        User user = new Gson().fromJson(jsonObject.get("user").toString(), User.class);
                        Log.i("App", user.getEmail());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                } else {
                    try {
                        Log.i("App", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("App", t.getMessage());
            }
        });*/

       UserServiceAPI userServiceAPI = retrofit.create(UserServiceAPI.class);
       userServiceAPI.register(user).enqueue(new Callback<User>() {
           @Override
           public void onResponse(Call<User> call, Response<User> response) {
               if(response.isSuccessful()){
                   Log.i("App", response.body().toString());
                   Toast.makeText(getBaseContext(), "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                   progressDialog.dismiss();

                   Intent i =  new Intent(getBaseContext(), LoginActivity.class);
                   i.putExtra("email", email);
                   startActivity(i);
                   finish();
               }else {
                   try {
                       Log.i("App", response.errorBody().string());
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
                   Toast.makeText(getBaseContext(), "Algo deu errado!", Toast.LENGTH_SHORT).show();
                  progressDialog.dismiss();
               }
           }

           @Override
           public void onFailure(Call<User> call, Throwable t) {
               Log.i("App", t.getMessage());
               Toast.makeText(getBaseContext(), "Algo deu errado!", Toast.LENGTH_SHORT).show();
               progressDialog.dismiss();
           }
       });

    }


    private void changeTint(){
        /*ivSenha.setColorFilter(ContextCompat.getColor(getBaseContext(),
                R.color.tint_yellow));*/

        if(passwordView){
            ivSenha.setColorFilter(ContextCompat.getColor(getBaseContext(),
                    R.color.colorAccent));
            edtSenha.setInputType( InputType.TYPE_CLASS_TEXT |
                    InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }else {
            ivSenha.setColorFilter(ContextCompat.getColor(getBaseContext(),
                    R.color.colorPrimary));
            edtSenha.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        }

        passwordView = !passwordView;
        edtSenha.setSelection(edtSenha.getText().length());

    }


    private void initViews(){
        edtNome = (EditText) findViewById(R.id.activity_cad_usuar_edtNome);
        edtEmail = (EditText) findViewById(R.id.activity_cad_usuar_edtEmail);
        edtSenha = (EditText) findViewById(R.id.activity_cad_usuar_edtPassword);
        btnCadastrar = (Button) findViewById(R.id.activity_cad_usuar_btnCadastrar);
        ivSenha = (ImageView)  findViewById(R.id.activity_cad_usuar_ivPassword);

        btnCadastrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if(validaInfo()){
                    register(buildObject());
                }
            }
        });


        ivSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeTint();
            }
        });


    }

    private boolean validaInfo() {
         nome = edtNome.getText().toString();
         email = edtEmail.getText().toString();
         senha = edtSenha.getText().toString();
        if(nome.isEmpty() || email.isEmpty() || senha.isEmpty()){
            Toast.makeText(this, "Existem caapos não preenchidos!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return  true;
    }

    private User buildObject(){
        User user = new User();
        user.setNome(nome);
        user.setEmail(email);
        user.setPassword(senha);
        user.setLevel(3);


        return user;
    }


}
