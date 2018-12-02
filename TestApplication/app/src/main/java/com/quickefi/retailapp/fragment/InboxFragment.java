package com.quickefi.retailapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.quickefi.retailapp.R;
import com.quickefi.retailapp.model.Order;

public class InboxFragment extends Fragment{


    public InboxFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_inbox, container, false);

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
        return view;
    }


}