package edu.kiet.www.epoque2017.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import edu.kiet.www.epoque2017.Adapters.NotificationAdapter;
import edu.kiet.www.epoque2017.Adapters.RequestSentAdapter;
import edu.kiet.www.epoque2017.R;
import edu.kiet.www.epoque2017.util.DbHandler;

public class NotificationActivity extends AppCompatActivity {
    LinearLayout empty,non_empty;
    RecyclerView recyclerView;
    NotificationAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Notifications");
        Spannable text = new SpannableString(getSupportActionBar().getTitle());
        text.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.white)), 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        setTitleColor(getResources().getColor(R.color.white));
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        empty=(LinearLayout)findViewById(R.id.empty_view);
        non_empty=(LinearLayout)findViewById(R.id.non_empty_view);

        if(!DbHandler.contains(this,"title_list")){
            empty.setVisibility(View.VISIBLE);
            non_empty.setVisibility(View.GONE);
        }
        else{
            String title="";
            title=DbHandler.getString(this,"title_list","");
            if(title.equals("")){
                empty.setVisibility(View.VISIBLE);
                non_empty.setVisibility(View.GONE);
            }
            else{
                empty.setVisibility(View.GONE);
                non_empty.setVisibility(View.VISIBLE);
                String get_title=DbHandler.getString(this,"title_list","");
                String get_message=DbHandler.getString(this,"message_list","");
                String ti[]=get_title.split("@#$");
                String msg[]=get_message.split("@#$");

                recyclerView = (RecyclerView) findViewById(R.id.noti_recycler);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                adapter = new NotificationAdapter(getApplicationContext(),ti,msg);
                recyclerView.setAdapter(adapter);

            }
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
