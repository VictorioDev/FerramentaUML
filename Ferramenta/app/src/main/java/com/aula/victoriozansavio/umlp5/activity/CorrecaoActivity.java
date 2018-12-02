package com.aula.victoriozansavio.umlp5.activity;

import android.app.Fragment;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.aula.victoriozansavio.umlp5.R;
import com.aula.victoriozansavio.umlp5.Sketch;
import com.aula.victoriozansavio.umlp5.adapter.FragmenCorrecaoAdapter;
import com.aula.victoriozansavio.umlp5.fragment.ContainerFragment;

import java.util.ArrayList;

import processing.android.PFragment;

public class CorrecaoActivity extends AppCompatActivity {

    public ViewPager viewPager;
    public TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correcao);
        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.setAdapter(new FragmenCorrecaoAdapter(getSupportFragmentManager(), getBaseContext()));
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FFFFFF"));
        tabLayout.setTabTextColors(Color.parseColor("#ffffff"), Color.parseColor("#ffffff"));
        tabLayout.setupWithViewPager(viewPager);

        //frame.setId(CompatUtils.getUniqueViewId());
        //setContentView(frame, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));

    }


}
