package com.quickefi.retailapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.quickefi.retailapp.R;
import com.quickefi.retailapp.listener.OnClickListener;
import com.quickefi.retailapp.model.ItemData;
import com.quickefi.retailapp.model.Order;

import java.util.List;

public class AdapterOrders extends RecyclerView.Adapter<AdapterOrders.ViewHolder> {
    private Context mContext;
    private List<Order> list;
    OnClickListener onClickListener;

    private RecyclerView mrecyclerView;
    public AdapterOrders(List<Order> list, OnClickListener onClickListener) {
        this.list = list;
        this.onClickListener = onClickListener;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_item_order, viewGroup, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtLabel;
        Button confirmDropoffButton;
        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtLabel = itemView.findViewById(R.id.OrderName);
            /*confirmDropoffButton= (Button) itemView.findViewById(R.id.acceptOrder);
            confirmDropoffButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Order newOrder = new Order();
                    //newOrder.setId("Justins email ID");
                    //TextView prodname = (TextView) findViewById(R.id.textView12);
                    String ProductName = txtLabel.getText().toString();

                    newOrder.setOwnerid("Owner email ID");
                    newOrder.setProductid("hash of ( owneremailID + " + ProductName);
                    newOrder.setRenterid("Renter email ID");
                    newOrder.setStatus("3");
                    //Elly accepting the request is status=2. Elly confirming Dropoff by meeting him at the place is status=3
                    //newOrder.setDropoffaddress(R.id.);
                    //newOrder.setPickupaddress();
                    System.out.println(v.getId());

                    //MAKE API CALL TO CHANGE THE ORDER STATUS . BASICALLY UPDATE THE ORDER OBJECT
                }
            });

            */
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.onClick();

                }
            });

        }
    }
}
