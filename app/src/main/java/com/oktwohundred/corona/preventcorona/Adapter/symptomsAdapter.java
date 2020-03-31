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

public class symptomsAdapter extends RecyclerView.Adapter<symptomsAdapter.childViewHolder> {
    List<child> childItems;

    public symptomsAdapter(List<child> childItems) {
        this.childItems = childItems;
    }

    @NonNull
    @Override
    public symptomsAdapter.childViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_symptom, parent, false);
        return new childViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull symptomsAdapter.childViewHolder holder, int position) {
        child model = childItems.get(position);
        String title = model.getChildName();
        String des = model.getChildDescrip();
        if(des.equalsIgnoreCase("none"))
        {
            holder.sympdescp.setVisibility(View.GONE);
        }
        else {
            holder.sympdescp.setText(des);

        }

        if (title.equalsIgnoreCase("none")){
            holder.symptitle.setVisibility(View.GONE);
        }
        else {
            holder.symptitle.setText(title);

        }

    }

    @Override
    public int getItemCount() {
        return childItems.size();
    }

    public class childViewHolder extends RecyclerView.ViewHolder {
        TextView symptitle;
        TextView sympdescp;
        public childViewHolder(@NonNull View v) {
            super(v);

            sympdescp = v.findViewById(R.id.sympdescp);
            symptitle =v. findViewById(R.id.symptitle);

        }
    }
}
