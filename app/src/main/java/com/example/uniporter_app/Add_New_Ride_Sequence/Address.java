package com.example.uniporter_app.Add_New_Ride_Sequence;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.uniporter_app.Authentication.Login;
import com.example.uniporter_app.Authentication.MainActivity;
import com.example.uniporter_app.R;
import com.example.uniporter_app.Storage.SharedPreferenceManager;

public class Address extends Fragment implements View.OnClickListener {

    String residence_value;
    View myView;
    ImageButton front2;
    ImageButton back2;
    ImageButton x2;
    Spinner spinner;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.activity_address, container, false);

        front2 = myView.findViewById(R.id.front2);
        back2 = myView.findViewById(R.id.back2);
        x2 = myView.findViewById(R.id.x2);

        front2.setOnClickListener(this);
        back2.setOnClickListener(this);
        x2.setOnClickListener(this);

        spinner = (Spinner) myView.findViewById(R.id.address_spinner);
        residence_value = spinner.getSelectedItem().toString();

        return myView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.front2:
                SharedPreferenceManager.getInstance(getContext())
                        .saveResidence(residence_value);
                Fragment fragment1 = new Blocks();
                FragmentTransaction ft1 = getFragmentManager().beginTransaction();
                ft1.replace(R.id.screen_area, fragment1, "Blocks");
                ft1.commit();
                break;
            case R.id.back2:
                SharedPreferenceManager.getInstance(getContext())
                        .saveResidence(residence_value);
                Fragment fragment2 = new FlightInfo();
                FragmentTransaction ft2 = getFragmentManager().beginTransaction();
                ft2.replace(R.id.screen_area, fragment2, "FlightInfo");
                ft2.commit();
                break;
            case R.id.x2:
               Intent intent = new Intent(getActivity(), MainActivity.class);
               v.getContext().startActivity(intent);
               break;
            default:
                break;
        }
    }
}
