package com.example.uniporter_app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.ImageButton;

public class Luggage extends Fragment implements View.OnClickListener {
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.activity_luggage, container, false);
        ImageButton front4 = myView.findViewById(R.id.front4);
        ImageButton back4 = myView.findViewById(R.id.back4);
        front4.setOnClickListener(this);
        back4.setOnClickListener(this);
        return myView;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.front4:
                Fragment fragment1 = new Early();
                FragmentTransaction ft1 = getFragmentManager().beginTransaction();
                ft1.replace(R.id.screen_area, fragment1, "Luggage");
                ft1.commit();
                break;
            case R.id.back4:
                Fragment fragment2 = new Blocks();
                FragmentTransaction ft2 = getFragmentManager().beginTransaction();
                ft2.replace(R.id.screen_area, fragment2, "Address");
                ft2.commit();
                break;
            default:
                break;
        }
    }
}