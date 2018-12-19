package com.quickefi.retailapp.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.quickefi.retailapp.activities.MainActivity;
import com.quickefi.retailapp.interfaces.EndPoints;
import com.quickefi.retailapp.activities.DetailActivity;
import com.quickefi.retailapp.interfaces.Callback;
//import com.quickefi.retailapp.listener.OnClickListener;

import com.quickefi.retailapp.R;
import com.quickefi.retailapp.adapter.AdapterOrders;
import com.quickefi.retailapp.listener.OnClickListener;
import com.quickefi.retailapp.model.Order;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.loopj.android.http.*;
import com.quickefi.retailapp.model.Session;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;

public class InboxFragment extends Fragment implements OnClickListener {
    AdapterOrders adapterforOrders;
    OnClickListener onClickListener;
    private RecyclerView recyclerViewforOrders;
    private List<Order> list = new ArrayList<>();
    private List<Order> firelist = new ArrayList<>();
    private Context mContext;
    FirebaseDatabase database;
    DatabaseReference myRef,childref,ordersref ;
    AsyncHttpClient client;
    Session session;
    public InboxFragment() {

    }
    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);

        //=========================================================================

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view =inflater.inflate(R.layout.fragment_inbox_final, container, false);

        recyclerViewforOrders = view.findViewById(R.id.recyclerViewforOrders);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("orders");
        /*childref=database.getReference("stripeid1+timestamp");
        childref.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot snapshot) {
            System.out.println(snapshot.getValue());  //prints "Do you have data? You'll love Firebase."
        }
        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
        });*/
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.


                for(DataSnapshot dataSnapshot1 :dataSnapshot.getChildren()){

                    Order row_order = dataSnapshot1.getValue(Order.class);

                    list.add(row_order);

                }
                adapterforOrders = new AdapterOrders(list,onClickListener);
                recyclerViewforOrders.setLayoutManager(new GridLayoutManager(getContext(), 2));
                recyclerViewforOrders.setItemAnimator(new DefaultItemAnimator());
                recyclerViewforOrders.setAdapter(adapterforOrders);

            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Hello", "Failed to read value.", error.toException());
            }
        });




        return view;
    }
    @Override
    public void onClick(View whicheverorderwasclicked) {
        // I need to know who clicked it. Probably send view as parameter to oin
        RecyclerView mRecyclerView;
        mContext=getActivity().getApplicationContext();

        String tempstr = "Time pass string for t esting purposes";
        Toast.makeText(mContext, tempstr, Toast.LENGTH_LONG).show();

        Fragment fragment = new Rental_DropoffFragment();

        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        fm.popBackStack();
        //startActivity(new Intent(getContext(),Rental_DropoffFragment.class));
    }
    public void loopjsend(AsyncHttpClient client,JSONObject parameters,String email,String apitoken,final Callback<JSONObject> callback)
    {
        try {
            StringEntity entity = new StringEntity(parameters.toString());
            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            client.setBasicAuth(email,apitoken);
            String url=EndPoints.ORDERS;

            client.get(getContext(), url, entity, "application/json",
                    new AsyncHttpResponseHandler() {


                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                            try {
                                JSONObject json = new JSONObject(new String(responseBody));
                                Toast.makeText(mContext, "SUCCESS FOR THE WIN YOO", Toast.LENGTH_LONG).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            Toast.makeText(mContext, "Error in Asyncrequest bro", Toast.LENGTH_LONG).show();
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}