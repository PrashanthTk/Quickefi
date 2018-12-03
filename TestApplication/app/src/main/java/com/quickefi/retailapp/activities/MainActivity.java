package com.quickefi.retailapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.quickefi.retailapp.R;
import com.quickefi.retailapp.fragment.ImageUploadFragment;
import com.quickefi.retailapp.fragment.InboxFragment;
import com.quickefi.retailapp.fragment.Rental_DropoffFragment;
import com.quickefi.retailapp.fragment.Rental_PickupFragment;
import com.quickefi.retailapp.fragment.SearchFragment;
import com.quickefi.retailapp.util.AppConfig;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {
    private static PayPalConfiguration config=new PayPalConfiguration()
            .environment(PayPalConfiguration.ENVIRONMENT_SANDBOX)
            .clientId("Afxj_dBy1SgDJYs7Nttu5hrLUXlw4pZKTem-VA-X5aWDM3Z7Pyzvn5RqZN33Dtsels2nkXx8SzdvS2-R");

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.navigation_search:

                    fragment = new SearchFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_rentals:

                    fragment = new Rental_DropoffFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_picture:

                    fragment = new ImageUploadFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_inbox:

                    fragment = new InboxFragment();
                    loadFragment(fragment);
                    return true;
                case R.id.navigation_profile:

                    fragment = new Rental_PickupFragment();
                    loadFragment(fragment);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AppConfig.REQUEST_GALLERY_IMAGE || requestCode == AppConfig.REQUEST_CAMERA_IMAGE){
            new ImageUploadFragment().onActivityResult(requestCode, resultCode, data);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        bottomNavigationView.setSelectedItemId(R.id.navigation_search);
        final Button button = (Button) findViewById(R.id.paypal_button);
    }
    public void beginPayment(View view){
        Intent serviceConfig = new Intent(this, PayPalService.class);
        serviceConfig.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        startService(serviceConfig);

        PayPalPayment payment = new PayPalPayment(new BigDecimal("5.65"),
                "CAD", "My Awesome Nikon", PayPalPayment.PAYMENT_INTENT_SALE);

        Intent paymentConfig = new Intent(this, PaymentActivity.class);
        paymentConfig.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        //paymentConfig.putExtra(PaymentActivity.EXTRA_PAYMENT, payment);
        startActivityForResult(paymentConfig, 0);
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack("ImgUploadFragmentTransaction");
        transaction.commit();
    }


}
