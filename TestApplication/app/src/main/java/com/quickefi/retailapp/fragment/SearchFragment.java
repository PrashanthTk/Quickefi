package com.quickefi.retailapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quickefi.retailapp.R;
import com.quickefi.retailapp.activities.DetailActivity;
import com.quickefi.retailapp.adapter.AdapterPopular;
import com.quickefi.retailapp.adapter.AdapterRecommended;
import com.quickefi.retailapp.listener.OnClickListener;
import com.quickefi.retailapp.model.ItemData;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment implements OnClickListener {

    private RecyclerView recyclerViewRecommended,recyclerViewPopular;
    private AdapterRecommended adapterRecommended;
    private AdapterPopular adapterPopular;
    private List<ItemData> list = new ArrayList<>();

    public SearchFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_search, container, false);
        recyclerViewRecommended = view.findViewById(R.id.recyclerView);
        recyclerViewPopular = view.findViewById(R.id.recyclerViewPopular);

        for (int i = 0; i < 5; i++) {
            ItemData itemData = new ItemData();
            itemData.setLabel("Item"+i);
            list.add(itemData);
        }
        adapterRecommended = new AdapterRecommended(list,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerViewRecommended.setLayoutManager(mLayoutManager);
        recyclerViewRecommended.setItemAnimator(new DefaultItemAnimator());
        recyclerViewRecommended.setAdapter(adapterRecommended);

        adapterPopular = new AdapterPopular(list,this);
        recyclerViewPopular.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerViewPopular.setItemAnimator(new DefaultItemAnimator());
        recyclerViewPopular.setAdapter(adapterPopular);
        return view;
    }


    @Override
    public void onClick() {
        startActivity(new Intent(getContext(),DetailActivity.class));
    }
}
