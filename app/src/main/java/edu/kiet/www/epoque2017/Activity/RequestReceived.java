package edu.kiet.www.epoque2017.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import edu.kiet.www.epoque2017.Adapters.RequestReceivedAdapter;
import edu.kiet.www.epoque2017.CardObjects.RequestCardData;
import edu.kiet.www.epoque2017.R;

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
        recyclerView=(RecyclerView)findViewById(R.id.requestRecyclerView);
        adapter=new RequestReceivedAdapter(this,getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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
