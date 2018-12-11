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
import android.widget.TextView;
import android.widget.Toast;

import com.aula.victoriozansavio.umlp5.API.UserServiceAPI;
import com.aula.victoriozansavio.umlp5.R;
import com.aula.victoriozansavio.umlp5.library.Exercise;
import com.aula.victoriozansavio.umlp5.util.RetrofitBuilder;
import com.aula.victoriozansavio.umlp5.util.Utils;
import com.aula.victoriozansavio.umlp5.inteface.UserActionInterface;
import com.aula.victoriozansavio.umlp5.library.LoginResult;
import com.aula.victoriozansavio.umlp5.library.User;
import com.aula.victoriozansavio.umlp5.model.UserModel;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity implements UserActionInterface {

    EditText edtEmail;
    EditText edtSenha;
    CheckBox chkProfessor;

    TextView tvRecuperaSenha;

    ImageView ivPassword;

    Button btnLogin;
    Button btnCadastrar;

    String senha;
    String email;
    String id = "";
    String token = "";
    boolean isProfessor = false;


    UserActionInterface userActionInterface;

    boolean passwordView = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userActionInterface = this;
         id = Utils.getId(getBaseContext());
         token = Utils.getToken(getBaseContext());
        if(Utils.verifyUserTokenValidation(id, token, getBaseContext())){
            UserModel.getUserById(id, token, getBaseContext(), userActionInterface);
        }else{
            setContentView(R.layout.activity_login);
            initViews();

            Bundle bundle = getIntent().getExtras();
            if( bundle != null){
                edtEmail.setText(bundle.getString("email"));
                edtSenha.requestFocus();
            }
        }



    }




    private void initViews() {
        edtEmail = (EditText) findViewById(R.id.activity_login_edtEmail);
        edtSenha = (EditText) findViewById(R.id.activity_login_edtPassword);
        btnLogin = (Button) findViewById(R.id.activity_login_btnLogin);
        btnCadastrar = (Button) findViewById(R.id.activity_login_btnCadastrar);
        tvRecuperaSenha = (TextView) findViewById(R.id.activity_login_tvSenha);
        chkProfessor = findViewById(R.id.activity_login_checkProf);

        tvRecuperaSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(), RecuperarSenhaActivity.class);
                startActivity(i);
            }
        });

        ivPassword = (ImageView) findViewById(R.id.activity_login_imgViewPassword);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validaCampos()){
                    login(buildObject());
                }
            }
        });

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getBaseContext(), CadastroActivity.class);
                startActivity(i);

            }
        });


        ivPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeTint();
            }
        });
    }


    private void openHomePage(User user){
        //user.setLevel(3);
        Intent i;
        if(user.getLevel() == 2){
            i = new Intent(getBaseContext(), HomePageProfessorActivity.class);
        }else {
            i = new Intent(getBaseContext(), HomePageAlunoActivity.class );
        }

        i.putExtra("user", user);
        startActivity(i);
    }



    private void login(User user) {

        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Realizando Login...");

        Retrofit retrofit = RetrofitBuilder.build(GsonConverterFactory.create());

        UserServiceAPI userServiceAPI = retrofit.create(UserServiceAPI.class);
        userServiceAPI.login(user).enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {


                if(response.isSuccessful()){
                    LoginResult loginResult = response.body();
                    Utils.saveToken(loginResult, getBaseContext());
                    token = loginResult.getToken();
                    id = loginResult.getId();
                    Log.i("App", "LoginToken: " + loginResult.getToken());
                    Toast.makeText(getBaseContext(), "Login realizado com sucesso!", Toast.LENGTH_SHORT).show();

                    UserModel.getUserById(id, token, getBaseContext(), userActionInterface);
                }else {
                    try {
                        Log.i("App", response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getBaseContext(), "Algo deu errado!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                Log.i("App", "Falha na requisição! \n" + t.getMessage());
                Toast.makeText(getBaseContext(), "Falha na quisição!", Toast.LENGTH_SHORT).show();
            }
        });
    }




    private User buildObject() {
        User user = new User();
        user.setEmail(email);
        user.setPassword(senha);
        if(isProfessor){
            user.setLevel(2);
        }else {
            user.setLevel(3);
        }
        return user;
    }

    private void changeTint(){
        /*ivSenha.setColorFilter(ContextCompat.getColor(getBaseContext(),
                R.color.tint_yellow));*/

        if(passwordView){
            ivPassword.setColorFilter(ContextCompat.getColor(getBaseContext(),
                    R.color.colorAccent));
            edtSenha.setInputType( InputType.TYPE_CLASS_TEXT |
                    InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }else {
            ivPassword.setColorFilter(ContextCompat.getColor(getBaseContext(),
                    R.color.colorPrimary));
            edtSenha.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        }

        passwordView = !passwordView;
        edtSenha.setSelection(edtSenha.getText().length());

    }

    private boolean validaCampos() {
        email = edtEmail.getText().toString();
        senha = edtSenha.getText().toString();
        isProfessor = chkProfessor.isChecked();
        if(email.isEmpty() || senha.isEmpty()){
            Toast.makeText(this, "Existem campos não preenchidos!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return  true;
    }

    @Override
    public void onUserRetrieved(User user) {
        Log.i("App", "UserLevel: " + user.getLevel());
        openHomePage(user);
        finish();
    }
}
