package com.aula.victoriozansavio.umlp5.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aula.victoriozansavio.umlp5.R;
import com.aula.victoriozansavio.umlp5.activity.ExercicioActivity;
import com.aula.victoriozansavio.umlp5.library.Exercise;
import com.aula.victoriozansavio.umlp5.library.User;

import java.util.List;

public class BuscaExercicioAdapter extends RecyclerView.Adapter<BuscaExercicioAdapter.MyViewHolder> {
    private List<Exercise> mDataset;
    private Context context;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        View view;
        TextView tvNome;
        TextView tvDiagrama;
        TextView tvDescricao;

        public MyViewHolder(View v) {
            super(v);
            this.view = v;
            tvNome = view.findViewById(R.id.adapter_exerciciosR_tvExercicio);
            tvDiagrama =  view.findViewById(R.id.adapter_exerciciosR_tvTipoDiagrama);
            tvDescricao =  view.findViewById(R.id.adapter_exerciciosR_tvDesc);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public BuscaExercicioAdapter(List<Exercise> exercises, Context context) {
        this.mDataset = exercises;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public BuscaExercicioAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                                 int viewType) {
        // create a new view
        View v = LayoutInflater.from(context)
                .inflate(R.layout.adapter_exercicios_busca, null, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Log.i("App", "Bind: " + position);
        holder.tvNome.setText("#" + (position + 1) + " - " + mDataset.get(position).getTitle());
        if(mDataset.get(position).getType() == 1){
            holder.tvDiagrama.setText("Diagrama de Caso de Uso");
        }else {
            holder.tvDiagrama.setText("Diagrama de Classe");
        }

        holder.tvDescricao.setText(mDataset.get(position).getDescription());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, ExercicioActivity.class);
                i.putExtra("exercise", mDataset.get(position));
                context.startActivity(i);
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}