package com.oktwohundred.corona.preventcorona.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.oktwohundred.corona.preventcorona.ExpenssionLayout.ExpansionLayout;
import com.oktwohundred.corona.preventcorona.Model.allcountry;
import com.oktwohundred.corona.preventcorona.R;

import java.util.List;


public class timezoneAdapter extends RecyclerView.Adapter<timezoneAdapter.timeViewholder> {
    private LayoutInflater inflater;
    List<allcountry> timezoneis;
    ExpansionLayout timelayout;
    String timeis="";
    EditText text;
    public timezoneAdapter(List<allcountry> timezoneis, ExpansionLayout timelayout, EditText text) {
        this.timezoneis = timezoneis;
        this.timelayout = timelayout;
        this.text = text;
    }

    @NonNull
    @Override
    public timezoneAdapter.timeViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(parent.getContext());
        View v =inflater.inflate(R.layout.item_timezone, parent, false);
        return new timeViewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final timezoneAdapter.timeViewholder holder, int position) {
        allcountry model = timezoneis.get(position);

        String area = model.getCountryname();
        holder.timezonetext.setText(area);
        holder.timezonetext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeis = holder.timezonetext.getText().toString();
                timelayout.collapse(true);
                text.setText(timeis);
            }
        });

    }

    public String returnTimezone() {
        if (!timeis.isEmpty() || timeis !=null){
            return timeis;
        }
        return "Pakistan";
    }

    @Override
    public int getItemCount() {
        return timezoneis.size();
    }

    public class timeViewholder extends RecyclerView.ViewHolder {
        TextView timezonetext;
        public timeViewholder(@NonNull View itemView) {
            super(itemView);
            timezonetext = itemView.findViewById(R.id.item_time_text);
        }
    }
}
