package com.quickefi.retailapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quickefi.retailapp.R;
import com.quickefi.retailapp.adapter.AdapterRental;
import com.quickefi.retailapp.listener.OnClickListener;
import com.quickefi.retailapp.model.ItemData;

import java.util.ArrayList;
import java.util.List;

public class Rental_PickupFragment extends Fragment implements OnClickListener {

    private RecyclerView recyclerView;
    private AdapterRental adapterRental;
    private List<ItemData> list = new ArrayList<>();
    public Rental_PickupFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_rental_pickup, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);


        for (int i = 0; i < 5; i++) {
            ItemData itemData = new ItemData();
            itemData.setLabel("Item"+i);
            list.add(itemData);
        }
        adapterRental = new AdapterRental(list,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapterRental);
        return view;
    }

    @Override
    public void onClick() {

    }
}
