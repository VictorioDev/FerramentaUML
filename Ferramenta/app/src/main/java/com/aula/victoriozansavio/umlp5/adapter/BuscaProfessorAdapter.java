package com.aula.victoriozansavio.umlp5.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.aula.victoriozansavio.umlp5.R;
import com.aula.victoriozansavio.umlp5.activity.BuscaExercicioActivity;
import com.aula.victoriozansavio.umlp5.library.User;

import java.util.List;

public class BuscaProfessorAdapter extends RecyclerView.Adapter<BuscaProfessorAdapter.MyViewHolder> {
    private List<User> mDataset;
    private Context context;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        View view;
        TextView tvNome;
        TextView tvInstituicao;
        TextView tvNumExs;

        public MyViewHolder(View v) {
            super(v);
            this.view = v;
            tvNome = view.findViewById(R.id.adapter_professores_tvNomeProfessor);
            tvInstituicao =  view.findViewById(R.id.adapter_professores_tvInstituição);
            tvNumExs =  view.findViewById(R.id.adapter_professores_tvNumExs);;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public BuscaProfessorAdapter(List<User> userList, Context context) {
        this.mDataset = userList;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public BuscaProfessorAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = LayoutInflater.from(context)
                .inflate(R.layout.adapter_professores, null, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Log.i("App", "Bind: " + position);
        holder.tvNome.setText(mDataset.get(position).getNome());
        holder.tvInstituicao.setText(mDataset.get(position).getOrganization());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BuscaExercicioActivity.class);
                intent.putExtra("professor", mDataset.get(position));
                context.startActivity(intent);
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
