package edu.kiet.www.epoque2017;

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

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Home extends AppCompatActivity implements Requests.OnFragmentInteractionListener, Newsfeed.OnFragmentInteractionListener, Profile.OnFragmentInteractionListener, Events.OnFragmentInteractionListener, Participations.OnFragmentInteractionListener {

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
                            case R.id.action_search:
                                fragment = new Newsfeed();
                                break;
                            case R.id.action_favorites:
                                fragment = new Requests();
                                break;
                            case R.id.action_messages:
                                fragment = new Participations();
                                break;
                            case R.id.action_profile:
                                fragment = new Profile();
                                break;
                            case R.id.action_requests:
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