package edu.kiet.www.epoque2017.Splash;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import edu.kiet.www.epoque2017.Activity.Home;
import edu.kiet.www.epoque2017.Fragment.LoginFragment;
import edu.kiet.www.epoque2017.Fragment.SplashFragment;
import edu.kiet.www.epoque2017.R;
import edu.kiet.www.epoque2017.util.DbHandler;

public class SplashActivity extends AppCompatActivity {

    FragmentTransaction ft;
    LinearLayout layout;
    FragmentManager fragmentManager;
    Button login;
    protected boolean _active = true;
    protected int _splashTime = 7000;
 int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        fragmentManager= getSupportFragmentManager();
        ft = fragmentManager.beginTransaction();
        ft.add(new SplashFragment(), "fm");

        ft.commit();
        layout=(LinearLayout)findViewById(R.id.layout_buttons);
        login=(Button)findViewById(R.id.login);
        layout.setVisibility(View.GONE);

        Thread splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while (_active && (waited < _splashTime)) {
                        sleep(100);
                        if (_active) {
                            waited += 100;
                        }
                    }
                } catch (Exception e) {

                } finally {
                    Boolean check_login= DbHandler.getBoolean(getApplicationContext(),"isLoggedIn", false);
                    if(!check_login) {
                        fragmentManager.beginTransaction().add(R.id.fragment, new LoginFragment(), "LoginFragment").commit();
                    }
                    else {
                        Intent intent=new Intent(getApplicationContext(), Home.class);
                        startActivity(intent);
                    }
                   // finish();
                }
            };
        };
        splashTread.start();

    }
    @Override
    public void onBackPressed(){
            super.onBackPressed();
     }

}