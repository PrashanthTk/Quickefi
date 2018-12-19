package com.quickefi.retailapp.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.quickefi.retailapp.R;
import com.quickefi.retailapp.model.Order;

import java.util.UUID;

public class Rental_DropoffFragment extends Fragment{

    private Context context;
    public Rental_DropoffFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context=getContext();
        View view =inflater.inflate(R.layout.fragment_rental_dropoff, container, false);
        Button confirmdropoffbutton = (Button) container.findViewById(R.id.confirmdropoffbutton);
        confirmdropoffbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()) {
                    case R.id.confirmdropoffbutton:
                        //Create Order object
                        //Assign Order fields
                        //Push Order to heroku
                        Order newOrder = new Order();
                        //newOrder.setId("Justins email ID");
                        TextView prodname = (TextView) v.findViewById(R.id.ProductName);
                        String ProductName = prodname.getText().toString();

                        newOrder.setOwnerid("Owner email ID");
                        newOrder.setProductid("hash of ( owneremailID + " + ProductName);
                        newOrder.setRenterid("Renter email ID");
                        newOrder.setStatus("3");
                        //newOrder.setDropoffaddress(R.id.);
                        //newOrder.setPickupaddress();
                        System.out.println(v.getId());
                        break;

                }

            }
        });

        return view;
    }

}
