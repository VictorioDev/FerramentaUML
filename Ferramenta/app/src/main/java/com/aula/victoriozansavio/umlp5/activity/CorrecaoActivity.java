package com.aula.victoriozansavio.umlp5.activity;

import android.app.Dialog;
import android.app.Fragment;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.aula.victoriozansavio.umlp5.R;
import com.aula.victoriozansavio.umlp5.Sketch;
import com.aula.victoriozansavio.umlp5.adapter.CorreacoAdapter;
import com.aula.victoriozansavio.umlp5.adapter.FragmenCorrecaoAdapter;
import com.aula.victoriozansavio.umlp5.inteface.SubmissionActionInterface;
import com.aula.victoriozansavio.umlp5.library.Correction;
import com.aula.victoriozansavio.umlp5.library.Submission;
import com.aula.victoriozansavio.umlp5.model.SubmissionModel;
import com.aula.victoriozansavio.umlp5.util.Utils;

import java.util.ArrayList;

import processing.android.PFragment;

public class CorrecaoActivity extends AppCompatActivity implements SubmissionActionInterface {

    public ViewPager viewPager;
    public TabLayout tabLayout;
    private Button btnVoltarEx;
    private ImageView ivVoltar;
    private TextView tvVoltar;

    private Submission submission = new Submission();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correcao);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            submission = (Submission) bundle.getSerializable("submission");
            Log.i("App", "Submission Bundle: " + submission.getDate());

            String token = Utils.getToken(getBaseContext());
            String id = Utils.getId(getBaseContext());
            if(Utils.verifyUserTokenValidation(id, token, getBaseContext())){
                SubmissionModel.doCorrection(token, submission.getId(), this);
            }else {
                Utils.redirectToLoginPage(getBaseContext());
            }


        }else {
            Log.i("App", "Correção Bundle null");
        }

        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new FragmenCorrecaoAdapter(getSupportFragmentManager(), getBaseContext(), buildList()));
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FFFFFF"));
        tabLayout.setTabTextColors(Color.parseColor("#ffffff"), Color.parseColor("#ffffff"));
        tabLayout.setupWithViewPager(viewPager);

        ivVoltar = findViewById(R.id.activity_correcao_ivVoltar);
        tvVoltar = findViewById(R.id.activity_correcao_tvVoltar);


        ivVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.redirectToLoginPage(getBaseContext());
                finish();
            }
        });


        tvVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.redirectToLoginPage(getBaseContext());
                finish();
            }
        });

        btnVoltarEx = (Button) findViewById(R.id.activity_correcao_btnVoltarEx);

        //frame.setId(CompatUtils.getUniqueViewId());
        //setContentView(frame, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
    }

    private void showDialog(Correction correction){
        RecyclerView recyclerViewCorrecao;
        RecyclerView.Adapter mAdapter;
        RecyclerView.LayoutManager mLayoutManager;

        mAdapter = new CorreacoAdapter(correction.getErrors(), getBaseContext());
        mLayoutManager = new LinearLayoutManager(getBaseContext());
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_correcao);
        Button btnFechar = dialog.findViewById(R.id.dialog_correcao_btnFechar);

        btnFechar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        recyclerViewCorrecao = dialog.findViewById(R.id.dialog_correcao_rvCorrecao);
        recyclerViewCorrecao.setLayoutManager(mLayoutManager);
        recyclerViewCorrecao.setAdapter(mAdapter);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    private ArrayList<PFragment> buildList(){
        ArrayList<PFragment> fragments = new ArrayList<>();
        Log.i("App", "Sub Json: " + submission.getJson());
        Log.i("App", "Sub Ex Json: " + submission.getExercise().getJson());

        String exerciseJson = submission.getExercise().getJson();
        exerciseJson = exerciseJson.substring(1, exerciseJson.length() - 1);
        exerciseJson = exerciseJson.replaceAll("\\\\", "");
        Log.i("App", "Sub Ex Json REp: " + exerciseJson);


        fragments.add(new PFragment(new Sketch(this, submission.getJson())));
        fragments.add(new PFragment(new Sketch(this, exerciseJson)));
        return fragments;
    }


    @Override
    public void OnSubmissionSaved(Submission submission) {

    }

    @Override
    public void OnSubmissionCorrection(Correction correction) {
        showDialog(correction);
    }
}
