package com.swankapps.corona.preventcorona.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.swankapps.corona.preventcorona.Model.child;
import com.swankapps.corona.preventcorona.R;

import java.util.List;

public class childAdapter extends RecyclerView.Adapter<childAdapter.childViewHolder> {
    List<child> childItems;

    public childAdapter(List<child> childItems) {
        this.childItems = childItems;
    }

    @NonNull
    @Override
    public childAdapter.childViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_child, parent, false);
        return new childViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull childAdapter.childViewHolder holder, int position) {
        child model = childItems.get(position);
        String title = model.getChildName();
        String des = model.getChildDescrip();

        holder.ingtitle.setText(title);
        holder.ingdescrip.setText(des);
    }

    @Override
    public int getItemCount() {
        return childItems.size();
    }

    public class childViewHolder extends RecyclerView.ViewHolder {
        TextView ingdescrip;
        TextView ingtitle;
        public childViewHolder(@NonNull View v) {
            super(v);
            ingdescrip = v.findViewById(R.id.ingdescrip);
            ingtitle = v.findViewById(R.id.ingtitle);

        }
    }
}
