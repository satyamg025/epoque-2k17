package edu.kiet.www.epoque2017.Splash;



        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentManager;
        import android.support.v4.app.FragmentTransaction;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.Button;
        import android.widget.LinearLayout;

        import edu.kiet.www.epoque2017.R;
        import edu.kiet.www.epoque2017.Fragment.LoginFragment;
        import edu.kiet.www.epoque2017.Fragment.RegisterFragment;
        import edu.kiet.www.epoque2017.Fragment.SplashFragment;

public class SplashActivity extends AppCompatActivity {

    FragmentTransaction ft;
    LinearLayout layout;
    FragmentManager fragmentManager;
    Button login,register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash);
        fragmentManager= getSupportFragmentManager();
        ft = fragmentManager.beginTransaction();
        ft.add(new SplashFragment(), "fm");

        ft.addToBackStack(null);
        ft.commit();
        layout=(LinearLayout)findViewById(R.id.layout_buttons);
        login=(Button)findViewById(R.id.login);
        register=(Button)findViewById(R.id.register);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager.beginTransaction().add(R.id.fragment,new LoginFragment(), "LoginFragment").setTransition(
                        FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
                layout.setVisibility(View.GONE);
                //ft.addToBackStack(null);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager.beginTransaction().add(R.id.fragment,new RegisterFragment(), "RegisterFragment").setTransition(
                        FragmentTransaction.TRANSIT_FRAGMENT_FADE).commit();
                layout.setVisibility(View.GONE);
            }
        });
    }
    @Override
    public void onBackPressed(){
        Fragment f= getSupportFragmentManager().getFragments().get(getSupportFragmentManager().getFragments().size()-1);
        //Log.e("key",f.getClass().getName());
        //Log.e("key2",LoginFragment.class.getName());

        if(f.getClass().getName().equals(LoginFragment.class.getName())) {
            fragmentManager.beginTransaction().add(R.id.fragment, new SplashFragment(), "SplashActivity").commit();
            layout.setVisibility(View.VISIBLE);

        }
        else if(f.getClass().getName().equals(RegisterFragment.class.getName())) {
            fragmentManager.beginTransaction().add(R.id.fragment, new SplashFragment(), "SplashActivity").commit();
            layout.setVisibility(View.VISIBLE);
        }
        else
            super.onBackPressed();


    }

}