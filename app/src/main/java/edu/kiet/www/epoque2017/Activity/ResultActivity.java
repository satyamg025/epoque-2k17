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

import edu.kiet.www.epoque2017.Adapters.ResultAdapter;
import edu.kiet.www.epoque2017.R;

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

        mrecyclerView = (RecyclerView)findViewById(R.id.recycler_result);
        assert mrecyclerView != null;
        mrecyclerView.setHasFixedSize(true);
        mlinearLayoutManager = new LinearLayoutManager(this);
        mrecyclerView.setLayoutManager(mlinearLayoutManager);
        madapter = new ResultAdapter(this);
        mrecyclerView.setAdapter(madapter);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id= item.getItemId();
        if(id==android.R.id.home)
            onBackPressed();
        return super.onOptionsItemSelected(item);
    }

}
