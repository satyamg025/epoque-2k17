package edu.kiet.www.epoque2017.Activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import edu.kiet.www.epoque2017.Adapters.RequestReceivedAdapter;
import edu.kiet.www.epoque2017.Adapters.RequestSentAdapter;
import edu.kiet.www.epoque2017.CardObjects.RequestCardData;
import edu.kiet.www.epoque2017.Models.RequestReceivedDataumPOJO;
import edu.kiet.www.epoque2017.Models.RequestReceivedPOJO;
import edu.kiet.www.epoque2017.Models.RequestSentPOJO;
import edu.kiet.www.epoque2017.R;
import edu.kiet.www.epoque2017.Requests.RequestReceivedRequest;
import edu.kiet.www.epoque2017.networking.ServiceGenerator;
import edu.kiet.www.epoque2017.ui.coloredSnackBar;
import edu.kiet.www.epoque2017.util.DbHandler;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestReceived extends AppCompatActivity {

    RecyclerView recyclerView;
    RequestReceivedAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_received);
       Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Requests Received");
        setTitleColor(getResources().getColor(R.color.white));
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final ProgressDialog progressDialog=new ProgressDialog(this,R.style.MyAlertDialogStyle);

        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        if(!DbHandler.getString(getApplicationContext(),"bearer","").equals("")) {

            RequestReceivedRequest requestReceivedRequest = ServiceGenerator.createService(RequestReceivedRequest.class,DbHandler.getString(getApplicationContext(),"bearer",""));

            Call<RequestReceivedPOJO> call = requestReceivedRequest.response();
            call.enqueue(new Callback<RequestReceivedPOJO>() {

                @Override
                public void onResponse(Call<RequestReceivedPOJO> call, Response<RequestReceivedPOJO> response) {
                    RequestReceivedPOJO responseBody = response.body();
                    Log.e("request_data", String.valueOf(responseBody));
                    if (response.code() == 200) {
                        if (!responseBody.getError()) {
                            progressDialog.dismiss();

                            RequestReceivedDataumPOJO data;
                            data=responseBody.getData();
                            recyclerView=(RecyclerView)findViewById(R.id.requestRecyclerView);
                            adapter=new RequestReceivedAdapter(RequestReceived.this,data);
                            recyclerView.setAdapter(adapter);
                            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


                        } else {
                            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Session Expired", Snackbar.LENGTH_LONG);
                            coloredSnackBar.alert(snackbar).show();
                            DbHandler.unsetSession(RequestReceived.this, "isForcedLoggedOut");
                        }
                    } else {
                        progressDialog.dismiss();
                        new AlertDialog.Builder(RequestReceived.this,R.style.MyAlertDialogStyle)
                                .setTitle("Failed")
                                .setMessage("Failed to connect")
                                .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        startActivity(new Intent(RequestReceived.this,Home.class));
                                    }
                                })
                                .show();
                    }
                }

                @Override
                public void onFailure(Call<RequestReceivedPOJO> call, Throwable t) {
                    progressDialog.dismiss();
                    new AlertDialog.Builder(RequestReceived.this,R.style.MyAlertDialogStyle)
                            .setMessage("No requests received")
                            .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    startActivity(new Intent(RequestReceived.this,Home.class));
                                }
                            })
                            .show();

                }
            });
        }


    }
    public static List<RequestCardData> getData(){
        List<RequestCardData> data= new ArrayList<>();
        int eventImage= R.drawable.logo2;
        String eventName="Event Name";
        String invitedBy="Invited By-";
        for(int i=0;i<5;i++) {
            RequestCardData current=new RequestCardData();
            current.eventName=eventName;
            current.invitedBy=invitedBy;
            current.eventPhoto=eventImage;
            data.add(current);
        }
        return data;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id= item.getItemId();
        if(id==android.R.id.home)
            onBackPressed();
        return super.onOptionsItemSelected(item);
    }
}
