package com.aula.victoriozansavio.umlp5.activity;


import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.aula.victoriozansavio.umlp5.R;
import com.aula.victoriozansavio.umlp5.Sketch;

import java.util.HashMap;

import processing.android.CompatUtils;
import processing.android.PFragment;
import processing.core.PApplet;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    ImageView ivUseCase;
    ImageView ivPointer;
    ImageView ivActor;
    ImageView ivInclude;
    ImageView ivExtend;
    ImageView ivAnotation;
    String text = "";

    private Sketch sketch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //setContentView(com.aula.victoriozansavio.umlp5.R.layout.activity_main);
        initViews();
        FrameLayout frame = findViewById(R.id.container);
        //frame.setId(CompatUtils.getUniqueViewId());


        //setContentView(frame, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));
        sketch = new Sketch(this);
        PFragment fragment = new PFragment(sketch);
        fragment.setView(frame, this);




    }

    private void initViews(){
        ivUseCase = (ImageView) findViewById(R.id.ivUseCase );
        ivPointer = (ImageView) findViewById(R.id.ivPointer );
        ivActor = (ImageView) findViewById(R.id.ivActor );
        ivInclude = (ImageView) findViewById(R.id.ivInclude );
        ivExtend = (ImageView) findViewById(R.id.ivExtend );
        ivAnotation = (ImageView) findViewById(R.id.ivAnotation );
        ivUseCase.setOnClickListener(this);
        ivPointer.setOnClickListener(this);
        ivActor.setOnClickListener(this);
        ivInclude.setOnClickListener(this);
        ivExtend.setOnClickListener(this);
        ivAnotation.setOnClickListener(this);

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
        }else if(view.getId() == ivPointer.getId()){
            sketch.modifyActions("pointer");
        }else if(view.getId() == ivActor.getId()){
            sketch.modifyActions("actor");
        }else if(view.getId() == ivInclude.getId()){
            sketch.modifyActions("include");
            sketch.clearTemp();
        }else if(view.getId() == ivExtend.getId()){
            sketch.modifyActions("extend");
            sketch.clearTemp();
        }else if(view.getId() == ivAnotation.getId()){
            sketch.modifyActions("anotation");
            sketch.clearTemp();

        }

        sketch.wichIsTrue(getBaseContext());
    }

}
