package com.example.uniporter_app;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.uniporter_app.Add_New_Ride_Sequence.AddNewRide;
import com.example.uniporter_app.Authentication.Login;
import com.example.uniporter_app.New_Pending_Rides.NewRide;
import com.example.uniporter_app.Scheduled_Rides.Scheduled_Ride;
import com.example.uniporter_app.Storage.SharedPreferenceManager;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;


public class DrawerUtil {

    public static void getDrawer(final Activity activity, Toolbar toolbar) {
        //if you want to update the items at a later time it is recommended to keep it in a variable
        PrimaryDrawerItem drawerEmptyItem= new PrimaryDrawerItem().withIdentifier(0).withName("Rides");
        drawerEmptyItem.withEnabled(false);
        PrimaryDrawerItem add_new_ride = new PrimaryDrawerItem().withIdentifier(1)
                .withName(R.string.new_ride).withIcon(R.drawable.ic_add_circle_outline_black_24dp);
        PrimaryDrawerItem pending_rides = new PrimaryDrawerItem()
                .withIdentifier(2).withName("Rides & History").withIcon(R.drawable.ic__car_24dp);
        PrimaryDrawerItem scheduled_rides = new PrimaryDrawerItem()
                .withIdentifier(3).withName("Scheduled Rides").withIcon(R.drawable.ic_schedule_24dp);
        PrimaryDrawerItem logout = new PrimaryDrawerItem()
                .withIdentifier(5).withName(R.string.logout).withIcon(R.drawable.ic_exit_to_app_black_24dp);

        String user_email = SharedPreferenceManager.getInstance(activity)
                .getUserEmal();

        String username = SharedPreferenceManager
                .getInstance(activity)
                .getName();

        // Create the AccountHeader
        AccountHeader headerResult = new AccountHeaderBuilder()
                .withActivity(activity)
                .withTranslucentStatusBar(true)
                .withHeaderBackground(R.drawable.gradient)
                .addProfiles(
                        new ProfileDrawerItem().withName(username).withEmail(user_email).withIcon(activity.getResources().getDrawable(R.drawable.avatar))
                )
                .withOnAccountHeaderListener(new AccountHeader.OnAccountHeaderListener() {
                    @Override
                    public boolean onProfileChanged(View view, IProfile profile, boolean currentProfile) {
                        return false;
                    }
                })
                .build();


        //create the drawer and remember the `Drawer` result object
        DrawerBuilder drawerBuilder = new DrawerBuilder();
        drawerBuilder.withActivity(activity);
        drawerBuilder.withToolbar(toolbar);
        drawerBuilder.withAccountHeader(headerResult);
        drawerBuilder.withActionBarDrawerToggle(true);
        drawerBuilder.withActionBarDrawerToggleAnimated(true);
        drawerBuilder.withCloseOnClick(true);
        drawerBuilder.withSelectedItem(-1);
        drawerBuilder.addDrawerItems(
                add_new_ride,
                pending_rides,
                scheduled_rides,
                new DividerDrawerItem(),
                logout,
                new DividerDrawerItem()
        );

        drawerBuilder.withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
            @Override
            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                if (drawerItem.getIdentifier() == 1 && !(activity instanceof AddNewRide)) {
                    // load screen
                    Intent intent = new Intent(activity, AddNewRide.class);
                    view.getContext().startActivity(intent);
                }
                else if (drawerItem.getIdentifier() == 2 && !(activity instanceof NewRide)) {
                    // load screen
                    Intent intent = new Intent(activity, NewRide.class);
                    view.getContext().startActivity(intent);
                }
                else if (drawerItem.getIdentifier() == 3 && !(activity instanceof Scheduled_Ride)) {
                    // load screen
                    Intent intent = new Intent(activity, Scheduled_Ride.class);
                    view.getContext().startActivity(intent);
                }
                else if (drawerItem.getIdentifier() == 5) {
                    // load screen
                    SharedPreferenceManager.getInstance(activity).logoutUser();
                    Intent intent = new Intent(activity, Login.class);
                    view.getContext().startActivity(intent);
                }
                return true;
            }
        });
        Drawer result = drawerBuilder.build();
    }

}
