package edu.kiet.www.epoque2017.Activity;

import android.graphics.Color;
import android.net.Uri;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.widget.FrameLayout;

import edu.kiet.www.epoque2017.Fragment.Events;
import edu.kiet.www.epoque2017.Fragment.Notifications;
import edu.kiet.www.epoque2017.Fragment.Schedule;
import edu.kiet.www.epoque2017.Fragment.Profile;
import edu.kiet.www.epoque2017.Fragment.fragment_sched_result;
import edu.kiet.www.epoque2017.R;
import edu.kiet.www.epoque2017.Fragment.Requests;

public class Home extends AppCompatActivity implements Requests.OnFragmentInteractionListener, Notifications.OnFragmentInteractionListener, Profile.OnFragmentInteractionListener, Events.OnFragmentInteractionListener, Schedule.OnFragmentInteractionListener {

    private Fragment fragment;
    private FragmentManager fragmentManager;
    private String name1, email1;
    private String name, email, uid, fragmentid, position;
    int count = 0;
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
                                fragment = new Notifications();
                                break;
                            case R.id.action_requests:
                                fragment = new Requests();
                                break;
                            case R.id.action_schedule_result:
                                fragment = new fragment_sched_result();
                                break;
                            case R.id.action_profile:
                                fragment = new Profile();
                                break;
                            case R.id.action_events:
                                fragment = new Events();
                        }

                        final FragmentTransaction transaction = fragmentManager.beginTransaction();
                        transaction.replace(R.id.main_container, fragment).commit();
                        return true;
                    }


                });
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

     /*class MyTimer extends TimerTask {


         @Override
         public void run() {
             Random random=new Random();
             frameLayout.setBackgroundColor(Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256) ));
         }
     }*/

}