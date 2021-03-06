package edu.kiet.www.epoque2017.Activity;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.kiet.www.epoque2017.Fragment.Events;
import edu.kiet.www.epoque2017.Fragment.Fragment_update;
import edu.kiet.www.epoque2017.Fragment.Profile;
import edu.kiet.www.epoque2017.Fragment.Requests;
import edu.kiet.www.epoque2017.Fragment.fragment_sched_result;
import edu.kiet.www.epoque2017.Models.ProfileDataumPOJO;
import edu.kiet.www.epoque2017.Models.ProfilePOJO;
import edu.kiet.www.epoque2017.R;
import edu.kiet.www.epoque2017.Requests.ProfileRequest;
import edu.kiet.www.epoque2017.networking.NetworkCheck;
import edu.kiet.www.epoque2017.networking.NotificationPublisher;
import edu.kiet.www.epoque2017.networking.ServiceGenerator;
import edu.kiet.www.epoque2017.ui.coloredSnackBar;
import edu.kiet.www.epoque2017.util.DbHandler;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends AppCompatActivity {

    private Fragment fragment;
    private FragmentManager fragmentManager;
    private String name1, email1;
    private String name, email, uid, fragmentid, position;
    int ids=0;
    int count = 0,flag=0;
    Boolean doubleBackToExitPressedOnce=false;
    FrameLayout frameLayout;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        fragmentManager = getSupportFragmentManager();

        fragment = new Events();
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(R.id.main_container, fragment).commit();
        frameLayout = (FrameLayout) findViewById(R.id.main_container);
        frameLayout.setBackgroundColor(Color.WHITE);
        /*Timer timer = new Timer();
        MyTimer mytimer = new MyTimer();
        timer.schedule(mytimer, 1000, 1000);*/
        final ProgressDialog progressDialog=new ProgressDialog(this);

        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        if (!NetworkCheck.isNetworkAvailable(Home.this)) {
            progressDialog.dismiss();
            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "No network connection", Snackbar.LENGTH_SHORT);
            coloredSnackBar.alert(snackbar).show();
            return;
        }
        else {
            if (!DbHandler.getString(getApplicationContext(), "bearer", "").equals("")) {

                ProfileRequest profileRequest = ServiceGenerator.createService(ProfileRequest.class, DbHandler.getString(getApplicationContext(), "bearer", ""));

                Call<ProfilePOJO> call = profileRequest.response();
                call.enqueue(new Callback<ProfilePOJO>() {

                    @Override
                    public void onResponse(Call<ProfilePOJO> call, Response<ProfilePOJO> response) {
                        progressDialog.dismiss();
                        ProfilePOJO responseBody = response.body();
                        Log.e("request_data", String.valueOf(responseBody));
                        if (response.code() == 200) {
                            if (!responseBody.getError()) {
                                Gson gson = new Gson();

                                ProfileDataumPOJO data;
                                data = responseBody.getData();

                                if (data.getUpdate()) {

                                    Fragment_update fragment_update = new Fragment_update();
                                    fragment_update.show(getFragmentManager(), "Update App");
                                }
                                if (data.getService()) {
                                    new AlertDialog.Builder(Home.this)
                                            .setMessage("App under service")
                                            .setCancelable(false)
                                            .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {
                                                    doubleBackToExitPressedOnce = true;
                                                    onBackPressed();
                                                }
                                            })
                                            .show();
                                }
                                if(data.getEpk_2k18()){
                                    new AlertDialog.Builder(Home.this)
                                            .setMessage("See you on EPOQUE 2K18")
                                            .setCancelable(false)
                                            .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {
                                                    doubleBackToExitPressedOnce = true;
                                                    onBackPressed();
                                                }
                                            })
                                            .show();

                                }

                                DbHandler.putString(Home.this, "profile", gson.toJson(data));


                            } else {
                                Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Session Expired", Snackbar.LENGTH_SHORT);
                                coloredSnackBar.alert(snackbar).show();
                                DbHandler.unsetSession(Home.this, "isForcedLoggedOut");
                            }
                        } else {

                            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Error connecting to server", Snackbar.LENGTH_SHORT);
                            coloredSnackBar.alert(snackbar).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<ProfilePOJO> call, Throwable t) {
                        progressDialog.dismiss();
                        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Error connecting to server", Snackbar.LENGTH_SHORT);
                        coloredSnackBar.alert(snackbar).show();

                    }
                });
            }
        }


       bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.action_requests:
                                flag=1;ids=item.getItemId();
                                //bottomNavigationView.setItemBackgroundResource(R.color.deep_orange);
                                fragment = new Requests();
                                break;
                            case R.id.action_schedule_result:
                                //bottomNavigationView.setItemBackgroundResource(R.color.yellow);
                                flag=1;ids=item.getItemId();
                                fragment = new fragment_sched_result();
                                break;
                            case R.id.action_profile:
                                flag=1;ids=item.getItemId();
                                //bottomNavigationView.setItemBackgroundResource(R.color.blue);
                                fragment = new Profile();
                                break;
                            case R.id.action_events:
                               // bottomNavigationView.setItemBackgroundResource(R.color.global_color_green_primary);
                                flag=0;
                                fragment = new Events();
                        }

                        final FragmentTransaction transaction = fragmentManager.beginTransaction();
                        transaction.replace(R.id.main_container, fragment).commit();
                        return true;
                    }


                });
    }
    @Override
    public void onBackPressed(){
        if(flag==1){
            flag=0;
            fragment=new Events();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.main_container, fragment).commit();
            Log.e("key","crashed");
            //bottomNavigationView.getMenu().getItem(ids).setChecked(false);
            //getMenuInflater(R.menu.bottom_navigation_main);


        }
        else if(flag==0){
            if (doubleBackToExitPressedOnce) {
                this.finishAffinity();
                return;
            }

            this.doubleBackToExitPressedOnce = true;

            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),"Press again to exit", Snackbar.LENGTH_SHORT);
            coloredSnackBar.warning(snackbar).show();
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            }, 2000);
        }
       // super.onBackPressed();
    }





}