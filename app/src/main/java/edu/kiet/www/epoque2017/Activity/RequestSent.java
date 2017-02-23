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

import edu.kiet.www.epoque2017.Adapters.RequestSentAdapter;
import edu.kiet.www.epoque2017.CardObjects.RequestSentCardData;
import edu.kiet.www.epoque2017.Models.RequestSentDataumPOJO;
import edu.kiet.www.epoque2017.Models.RequestSentPOJO;
import edu.kiet.www.epoque2017.R;
import edu.kiet.www.epoque2017.Requests.RequestSentRequest;
import edu.kiet.www.epoque2017.networking.ServiceGenerator;
import edu.kiet.www.epoque2017.ui.coloredSnackBar;
import edu.kiet.www.epoque2017.util.DbHandler;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestSent extends AppCompatActivity {
 RecyclerView.Adapter adapter;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_sent);
        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Requests Sent");
        setTitleColor(getResources().getColor(R.color.white));
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final ProgressDialog progressDialog=new ProgressDialog(this);

        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        if(!DbHandler.getString(getApplicationContext(),"bearer","").equals("")) {

            RequestSentRequest requestSentRequest = ServiceGenerator.createService(RequestSentRequest.class,DbHandler.getString(getApplicationContext(),"bearer",""));
            Call<RequestSentPOJO> call = requestSentRequest.responseRequest();
            call.enqueue(new Callback<RequestSentPOJO>() {

                @Override
                public void onResponse(Call<RequestSentPOJO> call, Response<RequestSentPOJO> response) {
                    progressDialog.dismiss();
                    RequestSentPOJO responseBody = response.body();
                    Log.e("request_data", String.valueOf(responseBody));
                    if (response.code() == 200) {
                        if (!responseBody.getError()) {


                            List<RequestSentDataumPOJO> data = new ArrayList<RequestSentDataumPOJO>();
                            data = responseBody.getData();
                            if (!data.isEmpty())
                            {
                                recyclerView = (RecyclerView) findViewById(R.id.requestSentRecyclerView);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            recyclerView.setLayoutManager(linearLayoutManager);
                            adapter = new RequestSentAdapter(getApplicationContext(), data, getSupportFragmentManager());
                            recyclerView.setAdapter(adapter);

                        }
                            else {
                                new AlertDialog.Builder(RequestSent.this,R.style.MyAlertDialogStyle)
                                        .setMessage("No requests sent")
                                        .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                startActivity(new Intent(RequestSent.this,Home.class));
                                            }
                                        })
                                        .show();
                            }

                        } else {
                            Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), "Session Expired", Snackbar.LENGTH_LONG);
                            coloredSnackBar.alert(snackbar).show();
                            DbHandler.unsetSession(RequestSent.this, "isForcedLoggedOut");
                        }
                    } else {

                        new AlertDialog.Builder(RequestSent.this)
                                .setTitle("Failed")
                                .setMessage("Failed to connect")
                                .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        startActivity(new Intent(RequestSent.this,Home.class));
                                    }
                                })
                                .show();
                    }
                }

                @Override
                public void onFailure(Call<RequestSentPOJO> call, Throwable t) {
                    progressDialog.dismiss();
                    new AlertDialog.Builder(RequestSent.this)
                            .setMessage("Connection Failed")
                            .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    startActivity(new Intent(RequestSent.this,Home.class));
                                }
                            })
                            .show();

                }
            });
        }


    }
    public List<RequestSentCardData> getData()
    {
        List<RequestSentCardData> data=new ArrayList<>();

        int k=1;
        for(int i=1;i<=10;i++)
        {
            RequestSentCardData current=new RequestSentCardData();
            current.event="Event Name : EventName(Institute)";
            current.image= R.drawable.logo2;
            List<String> id=new ArrayList<>();
            k=k%7+1;
           for(int j=1;j<=k;j++)
           {
               id.add("150291311"+Integer.toString(j));
           }
            current.partId=id;
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
