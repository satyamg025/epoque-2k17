package edu.kiet.www.epoque2017.Activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import edu.kiet.www.epoque2017.Fragment.Events;
import edu.kiet.www.epoque2017.Fragment.RegisterGroupDialogFragment;
import edu.kiet.www.epoque2017.Models.RegisterCancelPOJO;
import edu.kiet.www.epoque2017.R;
import edu.kiet.www.epoque2017.Requests.RegisterGroupDeptRequest;
import edu.kiet.www.epoque2017.Requests.RegisterSoloDeptRequest;
import edu.kiet.www.epoque2017.Requests.RegisterSoloInstRequest;
import edu.kiet.www.epoque2017.Splash.SplashActivity;
import edu.kiet.www.epoque2017.networking.ServiceGenerator;
import edu.kiet.www.epoque2017.ui.coloredSnackBar;
import edu.kiet.www.epoque2017.util.DbHandler;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Shrey on 10-02-2017.
 */

public class EventActivity extends AppCompatActivity {
    AppCompatButton register;
    ProgressDialog progressDialog;
    Bundle bundle;
    TextView  eventName,tagline,category,type,minParticipants,maxParticipants,description,time;
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        bundle=new Bundle();
        bundle=getIntent().getBundleExtra("bundle");
        setTitle(bundle.getString("name"));
        setTitleColor(getResources().getColor(R.color.white));
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tagline=(TextView)findViewById(R.id.tagline);
        category=(TextView)findViewById(R.id.category);
        type=(TextView)findViewById(R.id.type);
        register=(AppCompatButton)findViewById(R.id.register);
        minParticipants=(TextView)findViewById(R.id.min_participants);
        maxParticipants=(TextView)findViewById(R.id.max_participants);
        description=(TextView)findViewById(R.id.event_description);
        time=(TextView)findViewById(R.id.time);
        /*studentApexName=(TextView)findViewById(R.id.student_apex_name);
        studentApexPhone=(TextView)findViewById(R.id.student_apex_phone);
        facultyApexName=(TextView)findViewById(R.id.faculty_apex_name);
        facultyApexPhone=(TextView)findViewById(R.id.facuty_apex_phone);
        facultyApexDept=(TextView)findViewById(R.id.facuty_apex_dept);*/
       /* studentApexName.setVisibility(View.GONE);
        studentApexPhone.setVisibility(View.GONE);
        facultyApexDept.setVisibility(View.GONE);
        facultyApexName.setVisibility(View.GONE);
        facultyApexPhone.setVisibility(View.GONE);*/
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        if(!(bundle.getString("tagline")==null))
        tagline.setText(bundle.getString("tagline"));
        else
        tagline.setVisibility(View.GONE);
        if(bundle.getString("category").equalsIgnoreCase("S"))
            category.setText("Solo");
        if(bundle.getString("category").equalsIgnoreCase("G"))
            category.setText("Group");
        if(bundle.getString("type").equalsIgnoreCase("I"))
            type.setText("Institute");
        if(bundle.getString("type").equalsIgnoreCase("D"))
            type.setText("Department");
        description.setText(bundle.getString("description"));
        minParticipants.setText(bundle.getString("minPart"));
        maxParticipants.setText(bundle.getString("maxPart"));
        time.setText(bundle.getString("time"));
        if(bundle.getString("category").equalsIgnoreCase("S"))
        {
            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    progressDialog.show();
                    if (!bundle.getBoolean("reg_closed")) {
                        if (bundle.getString("type").equalsIgnoreCase("D")) {
                            String bearer = DbHandler.getString(getApplicationContext(), "bearer", "");
                            RegisterSoloDeptRequest request = ServiceGenerator.createService(RegisterSoloDeptRequest.class, bearer);
                            Call<RegisterCancelPOJO> call = request.request(bundle.getString("eventId"));
                            call.enqueue(new Callback<RegisterCancelPOJO>() {
                                @Override
                                public void onResponse(Call<RegisterCancelPOJO> call, Response<RegisterCancelPOJO> response) {
                                    progressDialog.dismiss();
                                    if (response.body().getError()) {
                                        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Session Expired", Snackbar.LENGTH_LONG);
                                        coloredSnackBar.alert(snackbar).show();
                                        DbHandler.unsetSession(EventActivity.this, "isForcedLoggedOut");
                                        startActivity(new Intent(EventActivity.this, SplashActivity.class));
                                        finishAffinity();
                                    } else {
                                        Toast.makeText(EventActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                        if (response.body().getStatus().trim().equalsIgnoreCase("success")) {
                                            startActivity(new Intent(EventActivity.this, Home.class));
                                            finishAffinity();
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<RegisterCancelPOJO> call, Throwable t) {

                                }
                            });

                        }

                        if (bundle.getString("type").equalsIgnoreCase("I")) {
                            String bearer = DbHandler.getString(getApplicationContext(), "bearer", "");
                            RegisterSoloInstRequest request = ServiceGenerator.createService(RegisterSoloInstRequest.class, bearer);
                            Call<RegisterCancelPOJO> call = request.request(bundle.getString("eventId"));
                            call.enqueue(new Callback<RegisterCancelPOJO>() {
                                @Override
                                public void onResponse(Call<RegisterCancelPOJO> call, Response<RegisterCancelPOJO> response) {
                                    progressDialog.dismiss();
                                    if (response.body().getError()) {

                                        DbHandler.unsetSession(EventActivity.this, "isForcedLoggedOut");
                                        startActivity(new Intent(EventActivity.this, SplashActivity.class));
                                        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Session Expired", Snackbar.LENGTH_LONG);
                                        coloredSnackBar.alert(snackbar).show();
                                        finishAffinity();

                                    } else {
                                        Toast.makeText(EventActivity.this, response.body().getMessage(), Toast.LENGTH_LONG).show();
                                        if (response.body().getStatus().trim().equalsIgnoreCase("success")) {
                                            startActivity(new Intent(EventActivity.this, Home.class));
                                            finishAffinity();
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<RegisterCancelPOJO> call, Throwable t) {
                                    Toast.makeText(EventActivity.this, "Please check your internet connection", Toast.LENGTH_SHORT).show();
                                }
                            });

                        }

                    }
                    else{
                        new AlertDialog.Builder(EventActivity.this)
                                .setMessage("Registrations are closed now")
                                .setCancelable(false)
                                .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        onBackPressed();
                                    }
                                })
                                .show();
                    }
                }


            });


            }

        else
        {
            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!bundle.getBoolean("reg_closed")) {
                        RegisterGroupDialogFragment dialogFragment = RegisterGroupDialogFragment.newinstance(EventActivity.this,
                                bundle.getString("type"),
                                bundle.getString("minPart"), bundle.getString("maxPart"), bundle.getString("eventId"));
                        dialogFragment.show(getSupportFragmentManager(), "fm");
                    }
                    else{
                        new AlertDialog.Builder(EventActivity.this)
                                .setMessage("Registrations are closed now")
                                .setCancelable(false)
                                .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        onBackPressed();
                                    }
                                })
                                .show();
                    }
                }
            });

        }

        }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id= item.getItemId();
        if(id==android.R.id.home)
            onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}

