package com.quickefi.retailapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quickefi.retailapp.R;

public class Rental_DropoffFragment extends Fragment{


    public Rental_DropoffFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_rental_dropoff, container, false);

        return view;
    }

}
