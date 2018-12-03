package com.quickefi.retailapp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.quickefi.retailapp.activities.DetailActivity;
import com.quickefi.retailapp.listener.OnClickListener;

import com.quickefi.retailapp.R;
import com.quickefi.retailapp.adapter.AdapterOrders;
import com.quickefi.retailapp.adapter.AdapterPopular;
import com.quickefi.retailapp.model.ItemData;
import com.quickefi.retailapp.model.Order;

import java.util.ArrayList;
import java.util.List;

public class InboxFragment extends Fragment implements OnClickListener{
    AdapterOrders adapterforOrders;
    private RecyclerView recyclerViewforOrders;
    private List<Order> list = new ArrayList<>();
    public InboxFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_inbox, container, false);

        recyclerViewforOrders = view.findViewById(R.id.recyclerViewforOrders);
        for (int i = 0; i < 5; i++) {
            Order temporder = new Order();
            temporder.setName("Order"+i);
            list.add(temporder);
        }
        Button b = (Button) view.findViewById(R.id.acceptOrder);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.acceptOrder:
                    //Create Order object
                        //Assign Order fields
                            //Push Order to heroku
                        Order newOrder=new Order();
                        //newOrder.setId("Justins email ID");
                        TextView prodname= (TextView) v.findViewById(R.id.ProductName);
                        String ProductName=prodname.getText().toString();

                        newOrder.setOwnerid("Owner email ID");
                        newOrder.setProductid("hash of ( owneremailID + "+ProductName);
                        newOrder.setRenterid("Renter email ID");
                        //newOrder.setDropoffaddress(R.id.);
                        //newOrder.setPickupaddress();
                        System.out.println(v.getId());
                        break;
                }

            }
        });
        adapterforOrders = new AdapterOrders(list,this);
        recyclerViewforOrders.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerViewforOrders.setItemAnimator(new DefaultItemAnimator());
        recyclerViewforOrders.setAdapter(adapterforOrders);

        return view;
    }
    @Override
    public void onClick() {
        startActivity(new Intent(getContext(),DetailActivity.class));
    }

}