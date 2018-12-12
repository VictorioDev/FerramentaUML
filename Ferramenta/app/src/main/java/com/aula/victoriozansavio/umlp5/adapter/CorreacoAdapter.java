package com.aula.victoriozansavio.umlp5.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aula.victoriozansavio.umlp5.R;

import java.util.List;



public class CorreacoAdapter extends RecyclerView.Adapter<CorreacoAdapter.MyViewHolder> {
    private List<String> mDataset;
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
    public CorreacoAdapter(List<String> userList, Context context) {
        this.mDataset = userList;
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
