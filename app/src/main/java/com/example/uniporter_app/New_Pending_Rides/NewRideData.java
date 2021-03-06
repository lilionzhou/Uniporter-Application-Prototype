package com.example.uniporter_app.New_Pending_Rides;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.uniporter_app.API.RetrofitClientRides;
import com.example.uniporter_app.API_models.RideResponse;
import com.example.uniporter_app.R;
import com.example.uniporter_app.Storage.SharedPreferenceManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SuppressLint("Registered")
public class NewRideData extends AppCompatActivity{

    final List<Integer> id = new ArrayList<>();
    final List<String> type = new ArrayList<>();
    final List<String> airline = new ArrayList<>();
    final List<String> flight_no = new ArrayList<>();
    final List<String> date = new ArrayList<>();
    final List<String> flight_time = new ArrayList<>();

    public int past_rides() throws ParseException {
        int future = 0;
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
        Date today_obj = new Date();
        Log.w("today", today_obj.toString());
        for (int i = 0; i < date.size(); ++i) {
            Date date_val = sdf.parse(date.get(i));
            if (date_val.compareTo(today_obj) > 0) {
                future++;
            }
            Log.w("current date", date_val.toString());
        }
        return future;
    }

    public void callRideAPI() {
        Call<List<RideResponse>> call = RetrofitClientRides
                .getInstance()
                .getAPI()
                .getRides("token " + SharedPreferenceManager.getInstance(NewRideData.this).getUserToken());

        call.enqueue(new Callback<List<RideResponse>>() {
            @Override
            public void onResponse(@NonNull Call<List<RideResponse>> call, @NonNull Response<List<RideResponse>> response) {
                if (response.body() != null) {
                    for (RideResponse ride : response.body()) {
                        id.add(ride.getId());
                        type.add(ride.getType());
                        airline.add(ride.getAirline());
                        flight_no.add(ride.getFlight_no());
                        date.add(ride.getDate());
                        flight_time.add(ride.getFlight_time());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<RideResponse>> call, @NonNull Throwable t) {

            }
        });
    }

    public ArrayList<NewRideInformation> getRideData() {

        ArrayList<NewRideInformation> data = new ArrayList<>();

        Log.w("response", Integer.toString(type.size()));
        for (int i = 0; i < type.size(); i++) {

            NewRideInformation current = new NewRideInformation();

            current.id = id.get(i);
            current.type = type.get(i);
            current.airline = airline.get(i);
            current.flight_no  =flight_no.get(i);
            current.date = date.get(i);
            current.flight_time = flight_time.get(i);

            Collections.sort(data, Collections.reverseOrder());
            data.add(current);
        }


        return data;
    }
}
