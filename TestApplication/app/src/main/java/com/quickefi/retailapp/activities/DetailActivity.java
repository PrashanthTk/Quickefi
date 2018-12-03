package com.quickefi.retailapp.activities;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.quickefi.retailapp.R;
import com.quickefi.retailapp.model.Order;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Button row_item_order_acceptOrder = (Button) findViewById(R.id.)
        Button RentNow = (Button) findViewById(R.id.acceptOrder);
        RentNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.acceptOrder:
                        //Create Order object
                        //Assign Order fields
                        //Push Order to heroku
                        Order newOrder = new Order();
                        //newOrder.setId("Justins email ID");
                        TextView prodname = (TextView) findViewById(R.id.textView12);
                        String ProductName = prodname.getText().toString();

                        newOrder.setOwnerid("Owner email ID");
                        newOrder.setProductid("hash of ( owneremailID + " + ProductName);
                        newOrder.setRenterid("Renter email ID");
                        //newOrder.setDropoffaddress(R.id.);
                        //newOrder.setPickupaddress();
                        System.out.println(v.getId());
                        break;

                }

            }
        });

        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appBarLayout);
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = true;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle("Nikon D840 Camera");
                    isShow = true;
                } else if(isShow) {
                    collapsingToolbarLayout.setTitle(" ");//carefull there should a space between double quote otherwise it wont work
                    isShow = false;
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
           finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
