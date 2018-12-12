package com.aula.victoriozansavio.umlp5.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.aula.victoriozansavio.umlp5.Sketch;


import java.util.ArrayList;

import processing.android.PFragment;

/**
 * Created by Victorio Zansavio on 02/12/2018.
 */

public class FragmenCorrecaoAdapter extends FragmentStatePagerAdapter {

    private Context context;
    private Sketch sketch;
    private ArrayList<PFragment> fragmentArrayList;

    public FragmenCorrecaoAdapter(FragmentManager fm, Context context, ArrayList<PFragment> fragmentArrayList) {
        super(fm);
        this.context = context;
        this.fragmentArrayList = fragmentArrayList;
    }

    @Override
    public Fragment getItem(int position) {
        /*sketch = new Sketch(context);
        PFragment fragment = new PFragment(sketch);
        return fragment;*/
        return fragmentArrayList.get(position);

    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }



    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "SUBMISSÃO";
            case 1:
                return "CORREÇÃO";
            default:
                return "NO TITLE";
        }

    }
}
