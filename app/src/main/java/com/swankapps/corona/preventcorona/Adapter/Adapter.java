package com.swankapps.corona.preventcorona.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.swankapps.corona.preventcorona.Model.Item;
import com.swankapps.corona.preventcorona.R;

import java.util.List;

public class Adapter extends PagerAdapter {

    private List<Item> models;
    private LayoutInflater layoutInflater;
    private Context context;

    public Adapter(List<Item> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.scrollviewthumb, container, false);

        ImageView imageView;

        imageView = view.findViewById(R.id.pic);


        imageView.setImageResource(models.get(position).getImage());


        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
