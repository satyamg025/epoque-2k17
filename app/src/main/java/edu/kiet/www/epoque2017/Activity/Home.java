package edu.kiet.www.epoque2017.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

import edu.kiet.www.epoque2017.Fragment.Events;
import edu.kiet.www.epoque2017.Fragment.Notifications;
import edu.kiet.www.epoque2017.Fragment.Profile;
import edu.kiet.www.epoque2017.Fragment.Requests;
import edu.kiet.www.epoque2017.Fragment.fragment_sched_result;
import edu.kiet.www.epoque2017.R;

public class Home extends AppCompatActivity {

    private Fragment fragment;
    private FragmentManager fragmentManager;
    private String name1, email1;
    private String name, email, uid, fragmentid, position;
    int count = 0,flag=0;
    Boolean doubleBackToExitPressedOnce=false;
    FrameLayout frameLayout;

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

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.action_notification:
                                flag=1;
                                fragment = new Notifications();
                                break;
                            case R.id.action_requests:
                                flag=1;
                                fragment = new Requests();
                                break;
                            case R.id.action_schedule_result:
                                flag=1;
                                fragment = new fragment_sched_result();
                                break;
                            case R.id.action_profile:
                                flag=1;
                                fragment = new Profile();
                                break;
                            case R.id.action_events:
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
            final FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.main_container, fragment).commit();
        }
        else if(flag==0){
            if (doubleBackToExitPressedOnce) {
                this.finishAffinity();
                return;
            }
            this.doubleBackToExitPressedOnce = true;

            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content),"Press again to exit", Snackbar.LENGTH_SHORT);
            snackbar.show();
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