package edu.kiet.www.epoque2017.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import edu.kiet.www.epoque2017.Adapters.RequestSentAdapter;
import edu.kiet.www.epoque2017.CardObjects.RequestSentCardData;
import edu.kiet.www.epoque2017.R;

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
        recyclerView=(RecyclerView)findViewById(R.id.requestSentRecyclerView);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter=new RequestSentAdapter(this,getData(),getSupportFragmentManager());
        recyclerView.setAdapter(adapter);

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
