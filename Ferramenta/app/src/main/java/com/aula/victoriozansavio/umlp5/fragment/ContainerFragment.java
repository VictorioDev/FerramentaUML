package com.aula.victoriozansavio.umlp5.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;


import com.aula.victoriozansavio.umlp5.R;
import com.aula.victoriozansavio.umlp5.Sketch;
import com.aula.victoriozansavio.umlp5.adapter.FragmenCorrecaoAdapter;

import processing.android.PFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class ContainerFragment extends Fragment {


    private Sketch sketch;

    public ContainerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_container,null, false);
        /*FrameLayout frame = v.findViewById(R.id.container);
        sketch = new Sketch(getContext());
        PFragment fragment = new PFragment(sketch);
        fragment.setView(frame, getActivity());*/
        return v;
    }

}
