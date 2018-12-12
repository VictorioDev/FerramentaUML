package com.aula.victoriozansavio.umlp5.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aula.victoriozansavio.umlp5.R;

import java.util.ArrayList;
import java.util.List;



public class CorreacoAdapter extends RecyclerView.Adapter<CorreacoAdapter.MyViewHolder> {
    private List<String> mDataset = new ArrayList<>();
    private Context context;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case


        TextView tvErro;

        public MyViewHolder(View v) {
            super(v);
            tvErro = v.findViewById(R.id.adapter_correcao_tvErro);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public CorreacoAdapter(List<String> erros, Context context) {
        this.mDataset = erros;
        /*this.mDataset.add("O seu diagrama contém 1 casos de uso, mas o diagrama original contém 2.");
        this.mDataset.add("No caso de uso Login, esperava-se que fosse escrito com pelo menos duas palavras. A primeira, um verbo que indique ação e o segundo, o caso em questão.");
        this.mDataset.add("Esperava-se que a primeira palavra do caso Login fosse um verbo.");
        this.mDataset.add("Você não cadastrou nenhum include. O diagrama original contém 1 includes.");*/
        this.context = context;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public CorreacoAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                                 int viewType) {
        // create a new view
        View v = LayoutInflater.from(context)
                .inflate(R.layout.adapter_correcao, null, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Log.i("App", "Bind: " + position);
        holder.tvErro.setText(mDataset.get(position));


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
