package com.swankapps.corona.preventcorona.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.swankapps.corona.preventcorona.Model.insights;
import com.swankapps.corona.preventcorona.R;

import java.util.List;

public class insightAdapter extends RecyclerView.Adapter<insightAdapter.insightViewholder> {
    List<insights> insightItems;
    Context context;

    public insightAdapter(List<insights> insightItems, Context context) {
        this.insightItems = insightItems;
        this.context = context;
    }

    @NonNull
    @Override
    public insightAdapter.insightViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.insight_item, parent, false);
        return new insightViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull insightAdapter.insightViewholder holder, int position) {
        insights model = insightItems.get(position);
        String title = model.getTitle();
        String descrip = model.getDescrip();
        String url = model.getUrl();
        String type = model.getType();
        if (type.equalsIgnoreCase("research")){
            holder.cardbackground.setBackground(context.getResources().getDrawable(R.drawable.card_bluebackground));
        }
        else {
            holder.cardbackground.setBackground(context.getResources().getDrawable(R.drawable.card_purple_bacground));
        }
        holder.insdescrip.setText(descrip);
        holder.institle.setText(title);

    }

    @Override
    public int getItemCount() {
        return insightItems.size();
    }

    public class insightViewholder extends RecyclerView.ViewHolder {
        LinearLayout cardbackground;
        TextView insdescrip;
        TextView institle;
        public insightViewholder(@NonNull View itemView) {
            super(itemView);
            insdescrip = itemView.findViewById(R.id.insdescrip);
            institle = itemView.findViewById(R.id.institle);
            cardbackground = itemView.findViewById(R.id.cardbackground);
        }
    }
}
