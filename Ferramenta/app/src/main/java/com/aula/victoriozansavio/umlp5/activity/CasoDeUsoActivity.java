package com.aula.victoriozansavio.umlp5.activity;


import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.ImageViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.aula.victoriozansavio.umlp5.R;
import com.aula.victoriozansavio.umlp5.Sketch;
import com.aula.victoriozansavio.umlp5.inteface.ExerciseActionInterface;
import com.aula.victoriozansavio.umlp5.inteface.SubmissionActionInterface;
import com.aula.victoriozansavio.umlp5.library.Correction;
import com.aula.victoriozansavio.umlp5.model.ExerciseModel;
import com.aula.victoriozansavio.umlp5.model.SubmissionModel;
import com.aula.victoriozansavio.umlp5.util.Utils;
import com.aula.victoriozansavio.umlp5.inteface.UserActionInterface;
import com.aula.victoriozansavio.umlp5.library.Exercise;
import com.aula.victoriozansavio.umlp5.library.Submission;
import com.aula.victoriozansavio.umlp5.library.User;
import com.aula.victoriozansavio.umlp5.model.UserModel;
import java.util.ArrayList;
import java.util.List;
import processing.android.PFragment;

public class CasoDeUsoActivity extends AppCompatActivity implements View.OnClickListener, UserActionInterface, ExerciseActionInterface, SubmissionActionInterface {

    ImageView ivUseCase;
    ImageView ivPointer;
    ImageView ivActor;
    ImageView ivInclude;
    ImageView ivExtend;
    ImageView ivAnotation;
    ImageView ivAssociation;
    ImageView ivVoltar;
    TextView tvVoltar;
    TextView tvLerTexto;

    ImageView ivSave;
    ArrayList<ImageView> toolsIcons = new ArrayList<>();
    TextView tvNomeExer;

    Exercise exercise = new Exercise();

    SubmissionActionInterface submissionActionInterface = this;

    String token = "";
    String id = "";
    String json = "";

    String text = "";



    boolean salvar;
    boolean sub;

    Submission savedSubmission = new Submission();

    private Sketch sketch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caso_de_uso);
        //setContentView(com.aula.victoriozansavio.umlp5.R.layout.activity_caso_de_uso);


        Bundle b = getIntent().getExtras();
        if(b != null){
            exercise = (Exercise) b.getSerializable("exercise");
            salvar = b.getBoolean("salvar");
            Log.i("App", "Salvar: " + salvar);
            sub = b.getBoolean("sub");
        }

        initViews();
        FrameLayout frame = findViewById(R.id.container);
        //frame.setId(CompatUtils.getUniqueViewId());
        //setContentView(frame, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));

        if(salvar){
            sketch = new Sketch(this);
        }else {

            String exerciseJson = exercise.getJson();
            exerciseJson = exerciseJson.substring(1, exerciseJson.length() - 1);
            exerciseJson = exerciseJson.replaceAll("\\\\", "");
            sketch = new Sketch(this, exerciseJson);
        }

        PFragment fragment = new PFragment(sketch);
        fragment.setView(frame, this);
    }


    private void showTexto(){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_exercicio);
        dialog.setCanceledOnTouchOutside(false);
        TextView tvDescricao = dialog.findViewById(R.id.dialog_exercicio_tvDescricao);
        TextView tvTitulo = dialog.findViewById(R.id.dialog_exercicio_tvTitulo);
        Button btnFechar = dialog.findViewById(R.id.dialog_exercicio_btnFechar);

        tvDescricao.setText(exercise.getDescription());
        tvTitulo.setText(exercise.getTitle());

        btnFechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    private void initViews(){
        ivUseCase = (ImageView) findViewById(R.id.ivUseCase );
        ivPointer = (ImageView) findViewById(R.id.ivPointer );
        ivActor = (ImageView) findViewById(R.id.ivActor );
        ivInclude = (ImageView) findViewById(R.id.ivInclude );
        ivExtend = (ImageView) findViewById(R.id.ivExtend );
        ivAnotation = (ImageView) findViewById(R.id.ivAnotation );
        ivSave = (ImageView) findViewById(R.id.activity_case_ivSave);
        tvNomeExer = (TextView) findViewById(R.id.activity_use_case_tvTitle);
        ivAssociation = findViewById(R.id.ivAssociation);
        ivVoltar = findViewById(R.id.activity_case_ivVoltar);
        tvVoltar = findViewById(R.id.activity_case_tvVoltar);
        tvLerTexto = (TextView) findViewById(R.id.activity_case_btnLerTexto);

        tvLerTexto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTexto();
            }
        });

        ivVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.redirectToLoginPage(getBaseContext());
                finish();
            }
        });

        tvNomeExer.setText(exercise.getTitle());

        ivUseCase.setOnClickListener(this);
        ivPointer.setOnClickListener(this);
        ivActor.setOnClickListener(this);
        ivInclude.setOnClickListener(this);
        ivExtend.setOnClickListener(this);
        ivAnotation.setOnClickListener(this);
        ivSave.setOnClickListener(this);
        ivAssociation.setOnClickListener(this);

        toolsIcons.add(ivUseCase);
        toolsIcons.add(ivPointer);
        toolsIcons.add(ivActor);
        toolsIcons.add(ivInclude);
        toolsIcons.add(ivExtend);
        toolsIcons.add(ivAnotation);
        toolsIcons.add(ivAssociation);
        /*tvUseCase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sketch.modifyActions("usecase");
                sketch.wichIsTrue(getBaseContext());
            }
        });*/
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == ivUseCase.getId()){
            sketch.modifyActions("usecase");
            changeTint(ivUseCase);
        }else if(view.getId() == ivPointer.getId()){
            sketch.modifyActions("pointer");
            changeTint(ivPointer);
        }else if(view.getId() == ivActor.getId()){
            sketch.modifyActions("actor");
            changeTint(ivActor);
        }else if(view.getId() == ivInclude.getId()){
            sketch.modifyActions("include");
            sketch.clearTemp();
            changeTint(ivInclude);
        }else if(view.getId() == ivExtend.getId()){
            sketch.modifyActions("extend");
            sketch.clearTemp();
            changeTint(ivExtend);
        }else if(view.getId() == ivAnotation.getId()){
            sketch.modifyActions("annotation");
            sketch.clearTemp();
            changeTint(ivAnotation);
        }else if(view.getId() == ivAssociation.getId()){
            sketch.modifyActions("association");
            sketch.clearTemp();
            changeTint(ivAssociation);
        } else if(view.getId() == ivSave.getId()){
            token = Utils.getToken(getBaseContext());
            id = Utils.getId(getBaseContext());
            if(!Utils.verifyUserTokenValidation(id, token, getBaseContext())){
                Utils.redirectToLoginPage(getBaseContext());
            }else {
                json = sketch.saveToJson();
                if(salvar){
                    if(!sub){
                        exercise.setJson(json);
                        UserModel.getUserById(id, token, getBaseContext(), this);

                    }else {
                        UserModel.getUserById(id, token, getBaseContext(), this);
                    }
                }else {
                    exercise.setJson(json);
                    ExerciseModel.editExercise(token, exercise.getId(), exercise, this);
                }

                Log.i("App", json);
            }
        }
        sketch.wichIsTrue(getBaseContext());
    }


    private Submission buildObject(User user, String json){
        Submission submission =  new Submission();
        submission.setExercise(exercise);
        Log.i("App", "BuildObject: " + user.getNome());
        submission.setAuthor(user);
        submission.setJson(json);
        return submission;
    }



    private  void changeTint(ImageView view) {
        view.setColorFilter(ContextCompat.getColor(getBaseContext(),
                R.color.tint_yellow));
        ImageViewCompat.setImageTintList(view, ColorStateList.valueOf(getResources().getColor(R.color.tint_yellow)));
        for (ImageView imgView : toolsIcons) {
            if(imgView.getId() != view.getId())
                imgView.setColorFilter(ContextCompat.getColor(getBaseContext(),
                        android.R.color.white));

        }
    }


    @Override
    public void onUserRetrieved(User user) {
        if(sub){
            SubmissionModel.saveSubmission(token,buildObject(user, json),getBaseContext(), submissionActionInterface);
            Toast.makeText(getBaseContext(), "Salvando submissao!", Toast.LENGTH_SHORT).show();
        }else {
            exercise.setAuthor(user);
            ExerciseModel.saveExercise(token, exercise, this);
        }

    }

    @Override
    public void onExercisesRetrieved(List<Exercise> exerciseList) {

    }

    @Override
    public void onExerciseDeleted(int position) {

    }

    @Override
    public void onExerciseSaved() {
        Intent i = new Intent(this, HomePageProfessorActivity.class);
        i.putExtra("user", exercise.getAuthor());
        Toast.makeText(getBaseContext(), "Salvando exercicio!", Toast.LENGTH_SHORT).show();
        startActivity(i);
        finish();
    }

    @Override
    public void onExerciseEdited() {
        Intent i = new Intent(this, HomePageProfessorActivity.class);
        i.putExtra("user", exercise.getAuthor());
        Toast.makeText(getBaseContext(), "Editando exercicio!", Toast.LENGTH_SHORT).show();
        startActivity(i);
        finish();
    }

    @Override
    public void OnSubmissionSaved(Submission submission) {
        Log.i("App", "Correction: " + submission.getJson());
        savedSubmission = submission;
        /*Intent i = new Intent(this, CorrecaoActivity.class);
        i.putExtra("submission", savedSubmission);
        startActivity(i);
        finish();*/
        Intent i = new Intent(this, SubmissoesActivity.class);
        //i.putExtra("submission", savedSubmission);
        startActivity(i);
        finish();
    }

    @Override
    public void OnSubmissionCorrection(Correction correction) {

    }
}
