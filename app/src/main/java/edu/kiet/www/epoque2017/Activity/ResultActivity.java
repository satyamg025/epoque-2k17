package edu.kiet.www.epoque2017.Activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.MenuItem;

import edu.kiet.www.epoque2017.Adapters.ResultAdapter;
import edu.kiet.www.epoque2017.Models.ResultDataumPOJO;
import edu.kiet.www.epoque2017.Models.ResultPOJO;
import edu.kiet.www.epoque2017.R;
import edu.kiet.www.epoque2017.Requests.ResultRequest;
import edu.kiet.www.epoque2017.networking.ServiceGenerator;
import edu.kiet.www.epoque2017.util.DbHandler;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResultActivity extends AppCompatActivity {

    RecyclerView mrecyclerView;
    LinearLayoutManager mlinearLayoutManager;
    ResultAdapter madapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        setTitle("Result");
        Spannable text = new SpannableString(getSupportActionBar().getTitle());
        text.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.white)), 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        setTitleColor(getResources().getColor(R.color.white));
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(!DbHandler.getString(getApplicationContext(),"bearer","").equals("")) {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Loading...");
            progressDialog.show();

            ResultRequest resultRequest = ServiceGenerator.createService(ResultRequest.class,DbHandler.getString(getApplicationContext(),"bearer",""));
            Call<ResultPOJO> call = resultRequest.response();
            call.enqueue(new Callback<ResultPOJO>() {

                @Override
                public void onResponse(Call<ResultPOJO> call, Response<ResultPOJO> response) {
                    ResultPOJO responseBody = response.body();
                    progressDialog.dismiss();
                    Log.e("request_data", String.valueOf(responseBody));
                    if (response.code() == 200) {
                        if (!responseBody.getError()) {


                            ResultDataumPOJO data=responseBody.getData();
                            mrecyclerView = (RecyclerView)findViewById(R.id.recycler_result);
                            assert mrecyclerView != null;
                            mrecyclerView.setHasFixedSize(true);
                            mlinearLayoutManager = new LinearLayoutManager(ResultActivity.this);
                            mrecyclerView.setLayoutManager(mlinearLayoutManager);
                            madapter = new ResultAdapter(ResultActivity.this,data);
                            mrecyclerView.setAdapter(madapter);


                        } else {
                            new AlertDialog.Builder(ResultActivity.this)
                                    .setTitle("No Result")
                                    .setMessage("Results not out yet.")
                                    .setCancelable(false)
                                    .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            startActivity(new Intent(ResultActivity.this,Home.class));
                                        }
                                    })
                                    .show();
                        }
                    } else {

                        new AlertDialog.Builder(ResultActivity.this)
                                .setTitle("Failed")
                                .setMessage("Failed to connect")
                                .setCancelable(false)
                                .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        startActivity(new Intent(ResultActivity.this,Home.class));
                                    }
                                })
                                .show();
                    }
                }

                @Override
                public void onFailure(Call<ResultPOJO> call, Throwable t) {
                    progressDialog.dismiss();
                    new AlertDialog.Builder(ResultActivity.this)
                            .setMessage("Connection Failed")
                            .setCancelable(false)
                            .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    startActivity(new Intent(ResultActivity.this,Home.class));
                                }
                            })
                            .show();

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
