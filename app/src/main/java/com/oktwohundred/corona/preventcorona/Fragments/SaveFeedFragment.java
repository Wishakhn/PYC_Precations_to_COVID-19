package com.oktwohundred.corona.preventcorona.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oktwohundred.corona.preventcorona.Adapter.saveditemAdapter;
import com.oktwohundred.corona.preventcorona.Model.savedfeeds;
import com.oktwohundred.corona.preventcorona.R;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SaveFeedFragment extends Fragment {

    RecyclerView SavedCycler;
    saveditemAdapter sAdapter;
    List<savedfeeds> saveItems;
    public SaveFeedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View sv = inflater.inflate(R.layout.fragment_save_feed, container, false);

        return sv ;
    }


    private void loadAllRemedies() {
        saveItems.clear();

        SavedCycler.setAdapter(sAdapter);
        sAdapter.notifyDataSetChanged();
    }

}
