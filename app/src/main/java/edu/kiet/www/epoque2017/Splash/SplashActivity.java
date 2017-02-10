package edu.kiet.www.epoque2017.Splash;



        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentManager;
        import android.support.v4.app.FragmentTransaction;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.Button;
        import android.widget.LinearLayout;

        import edu.kiet.www.epoque2017.Activity.Home;
        import edu.kiet.www.epoque2017.R;
        import edu.kiet.www.epoque2017.Fragment.LoginFragment;
        import edu.kiet.www.epoque2017.Fragment.RegisterFragment;
        import edu.kiet.www.epoque2017.Fragment.SplashFragment;

public class SplashActivity extends AppCompatActivity {

    FragmentTransaction ft;
    LinearLayout layout;
    FragmentManager fragmentManager;
    Button login;
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

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                if(count==1)
                fragmentManager.beginTransaction().add(R.id.fragment,new LoginFragment(), "LoginFragment").commit();
                else
                    startActivity(new Intent(SplashActivity.this,Home.class));
            }
        });

    }
    @Override
    public void onBackPressed(){
            super.onBackPressed();


    }

}