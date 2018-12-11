package com.aula.victoriozansavio.umlp5.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aula.victoriozansavio.umlp5.R;
import com.aula.victoriozansavio.umlp5.activity.CadastroExercicioActivity;
import com.aula.victoriozansavio.umlp5.inteface.ExerciseActionInterface;
import com.aula.victoriozansavio.umlp5.library.Exercise;
import com.aula.victoriozansavio.umlp5.model.ExerciseModel;
import com.aula.victoriozansavio.umlp5.util.Utils;

import java.util.List;

public class SeusExerciciosAdapter extends RecyclerView.Adapter<SeusExerciciosAdapter.MyViewHolder> implements ExerciseActionInterface {


    private List<Exercise> mDataset;
    private Context context;
    private ExerciseActionInterface exerciseActionInterface;


    @Override
    public void onExercisesRetrieved(List<Exercise> exerciseList) {

    }

    @Override
    public void onExerciseDeleted(int position) {
        mDataset.remove(position);
        notifyItemRemoved(position);
        Toast.makeText(context, "Exercício removido com sucesso!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onExerciseSaved() {

    }

    @Override
    public void onExerciseEdited() {

    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        ImageView ivEdit;
        ImageView ivRemove;
        TextView tvNome;
        TextView tvDiagrama;
        TextView tvDescricao;

        public MyViewHolder(View v) {
            super(v);
            tvNome = v.findViewById(R.id.adapter_exercicios_tvExercicio);
            tvDiagrama =  v.findViewById(R.id.adapter_exercicios_tvTipoDiagrama);
            tvDescricao =  v.findViewById(R.id.adapter_exercicios_tvDesc);
            ivEdit =  v.findViewById(R.id.adapter_exercicios_btnEdit);
            ivRemove = v.findViewById(R.id.adapter_exercicios_btnRemove);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public SeusExerciciosAdapter(List<Exercise> exercises, Context context) {
        this.mDataset = exercises;
        this.context = context;
        this.exerciseActionInterface = this;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public SeusExerciciosAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                                 int viewType) {
        // create a new view
        View v = LayoutInflater.from(context)
                .inflate(R.layout.adapter_exercicios, null, false);
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

        holder.ivRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String token = Utils.getToken(context);
                ExerciseModel.deleteExercise(token, mDataset.get(position).getId(), position,  exerciseActionInterface);
            }
        });

        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, CadastroExercicioActivity.class);
                i.putExtra("exercise", mDataset.get(position));
                i.putExtra("salvar", false);
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