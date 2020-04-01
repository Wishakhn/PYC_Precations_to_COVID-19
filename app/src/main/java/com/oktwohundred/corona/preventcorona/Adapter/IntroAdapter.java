package com.oktwohundred.corona.preventcorona.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.oktwohundred.corona.preventcorona.Fragments.WebViewFragment;
import com.oktwohundred.corona.preventcorona.Model.blogs;
import com.oktwohundred.corona.preventcorona.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class IntroAdapter extends RecyclerView.Adapter<IntroAdapter.introViewholder> {
    Context context;
    FrameLayout frame;
    List<blogs> bloglist;

    public IntroAdapter(Context context, FrameLayout frame, List<blogs> bloglist) {
        this.context = context;
        this.frame = frame;
        this.bloglist = bloglist;
    }

    @NonNull
    @Override
    public IntroAdapter.introViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_covitblog, parent, false);
        return new introViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IntroAdapter.introViewholder holder, int position) {
        blogs model = bloglist.get(position);
        String by = model.getArticleby();
        String bloger = model.getBlogger();
        int image = model.getImage();
        String title = model.getTitle();
        String desc = model.getDescrip();
        final String url = model.getUrl();


        holder.articleby.setText(by);
        holder.bloggername.setText(bloger);
        holder.blogtitle.setText(title);
        holder.blogdescrip.setText(desc);
        Glide.with(context).load(image).into(holder.blogerthumb);

        holder.readblogbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callWebFragment(url);
            }
        });


    }

    void callWebFragment(String url){
        FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        WebViewFragment frag1 = new WebViewFragment(url,context);
        fragmentTransaction.replace(frame.getId(), frag1, "Web  Fragment");
        fragmentTransaction.commit();
    }
    @Override
    public int getItemCount() {
        return bloglist.size();
    }

    public class introViewholder extends RecyclerView.ViewHolder {
        CircleImageView blogerthumb;
        TextView articleby;
        TextView bloggername;
        TextView blogtitle;
        TextView blogdescrip;
        RelativeLayout readblogbtn;
        public introViewholder(@NonNull View iv) {
            super(iv);
            blogerthumb =iv.findViewById(R.id.blogerthumb);
            articleby =iv.findViewById(R.id.articleby);
            bloggername =iv.findViewById(R.id.bloggername);
            blogtitle =iv.findViewById(R.id.blogtitle);
            blogdescrip =iv.findViewById(R.id.blogdescrip);
            readblogbtn =iv.findViewById(R.id.readblogbtn);
        }
    }
}
