package com.oktwohundred.corona.preventcorona.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.oktwohundred.corona.preventcorona.Model.child;
import com.oktwohundred.corona.preventcorona.R;

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
        if(des.equalsIgnoreCase("none"))
        {
            holder.ingdescrip.setVisibility(View.INVISIBLE);
        }
        else {
            holder.ingdescrip.setText(des);

        }

        holder.ingtitle.setText(title);
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
