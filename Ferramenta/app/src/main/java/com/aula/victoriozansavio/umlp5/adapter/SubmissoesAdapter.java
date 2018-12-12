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
import com.aula.victoriozansavio.umlp5.activity.CorrecaoActivity;
import com.aula.victoriozansavio.umlp5.library.Submission;


import java.util.List;

public class SubmissoesAdapter extends RecyclerView.Adapter<SubmissoesAdapter.MyViewHolder> {
    private List<Submission> mDataset;
    private Context context;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        View view;
        TextView tvTitle;
        TextView tvDiagrama;
        TextView tvData;

        public MyViewHolder(View v) {
            super(v);
            this.view = v;
            tvTitle = view.findViewById(R.id.adapter_submissoes_tvNomeExercicio);
            tvDiagrama =  view.findViewById(R.id.adapter_submissoes_tvTipoDiagrama);
            tvData =  view.findViewById(R.id.adapter_submissoes_tvTentativa);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public SubmissoesAdapter(List<Submission> submissions, Context context) {
        this.mDataset = submissions;
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public SubmissoesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                                 int viewType) {
        // create a new view
        View v = LayoutInflater.from(context)
                .inflate(R.layout.adapter_sumissoes, null, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Log.i("App", "Bind: " + position);
        holder.tvTitle.setText("#" + (position + 1) + " - " + mDataset.get(position).getExercise().getTitle());
        if(mDataset.get(position).getExercise().getType() == 1){
            holder.tvDiagrama.setText("Diagrama de Caso de Uso");
        }else {
            holder.tvDiagrama.setText("Diagrama de Classe");
        }

        String dataSub = mDataset.get(position).getDate().substring(0,10);
        Log.i("App", dataSub);
        String anoMesDia[] = dataSub.split("-");
        holder.tvData.setText("Tentativa em: " + anoMesDia[2] + "/" + anoMesDia[1] + "/" + anoMesDia[0]);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, CorrecaoActivity.class);
                i.putExtra("submission", mDataset.get(position));
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