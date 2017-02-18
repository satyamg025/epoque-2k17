package edu.kiet.www.epoque2017.Activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import edu.kiet.www.epoque2017.Adapters.ScheduleAdapter;
import edu.kiet.www.epoque2017.Models.ScheduleDatumPOJO;
import edu.kiet.www.epoque2017.Models.SchedulePOJO;
import edu.kiet.www.epoque2017.R;
import edu.kiet.www.epoque2017.Requests.ScheduleBearer;
import edu.kiet.www.epoque2017.Requests.ScheduleRequest;
import edu.kiet.www.epoque2017.networking.ServiceGenerator;
import edu.kiet.www.epoque2017.util.DbHandler;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TabbedSchedule extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    List<ScheduleDatumPOJO> schedulelist=new ArrayList<ScheduleDatumPOJO>();
    List<String> titleList=new ArrayList<String>();
//    Context context=this.getApplicationContext();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabbed_schedule);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Schedule");
        Spannable text = new SpannableString(getSupportActionBar().getTitle());
        text.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.white)), 0, text.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        setTitleColor(getResources().getColor(R.color.white));
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setTabTextColors(ColorStateList.valueOf(getResources().getColor(R.color.white)));

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        if(DbHandler.getString(getApplicationContext(),"bearer","").equals("")) {
            ScheduleRequest scheduleRequest = ServiceGenerator.createService(ScheduleRequest.class);
            Call<SchedulePOJO> call = scheduleRequest.requestResponse();
            call.enqueue(new Callback<SchedulePOJO>() {
                @Override
                public void onResponse(Call<SchedulePOJO> call, Response<SchedulePOJO> response) {
                    SchedulePOJO responseBody = response.body();
                    Log.e("schedule_data", String.valueOf(responseBody));
                    if (response.code() == 200) {
                        if (!responseBody.getError()) {
                            progressDialog.dismiss();
                            schedulelist = responseBody.getData();
                            for (int i = 0; i < schedulelist.size(); i++) {
                                titleList.add("Day " + String.valueOf(i + 1));
                            }
                            tabLayout.setupWithViewPager(mViewPager);
                            setupViewPager(mViewPager);
                        } else {
                            DbHandler.unsetSession(TabbedSchedule.this, "isForcedLoggedOut");
                        }
                    } else {
                        progressDialog.dismiss();
                        new AlertDialog.Builder(TabbedSchedule.this)
                                .setTitle("Failed")
                                .setMessage("Failed to connect")
                                .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(getApplicationContext(), Home.class);
                                        startActivity(intent);
                                    }
                                })
                                .show();
                    }
                }

                @Override
                public void onFailure(Call<SchedulePOJO> call, Throwable t) {
                    progressDialog.dismiss();
                    new AlertDialog.Builder(TabbedSchedule.this)
                            .setMessage("Connection Failed")
                            .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(getApplicationContext(), Home.class);
                                    startActivity(intent);
                                }
                            })
                            .show();

                }
            });
        }

        else{

            ScheduleBearer scheduleBearer = ServiceGenerator.createService(ScheduleBearer.class,DbHandler.getString(getApplicationContext(),"bearer",""));
            Call<SchedulePOJO> call = scheduleBearer.requestResponse();
            call.enqueue(new Callback<SchedulePOJO>() {
                @Override
                public void onResponse(Call<SchedulePOJO> call, Response<SchedulePOJO> response) {
                    SchedulePOJO responseBody = response.body();
                    Log.e("schedule_data", String.valueOf(responseBody));
                    if (response.code() == 200) {
                        if (!responseBody.getError()) {
                            progressDialog.dismiss();
                            schedulelist = responseBody.getData();
                            for (int i = 0; i < schedulelist.size(); i++) {
                                titleList.add("Day " + String.valueOf(i + 1));
                            }
                            tabLayout.setupWithViewPager(mViewPager);
                            setupViewPager(mViewPager);
                        } else {
                            DbHandler.unsetSession(TabbedSchedule.this, "isForcedLoggedOut");
                        }
                    } else {
                        progressDialog.dismiss();
                        new AlertDialog.Builder(TabbedSchedule.this)
                                .setTitle("Failed")
                                .setMessage("Failed to connect")
                                .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent = new Intent(getApplicationContext(), Home.class);
                                        startActivity(intent);
                                    }
                                })
                                .show();
                    }
                }

                @Override
                public void onFailure(Call<SchedulePOJO> call, Throwable t) {
                    progressDialog.dismiss();
                    new AlertDialog.Builder(TabbedSchedule.this)
                            .setMessage("Connection Failed")
                            .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(getApplicationContext(), Home.class);
                                    startActivity(intent);
                                }
                            })
                            .show();

                }
            });
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

        private void setupViewPager(ViewPager viewPager) {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        for (int i=0;i<schedulelist.size();i++) {
            adapter.addFragment(PlaceholderFragment.newInstance(schedulelist.get(i)), titleList.get(i));
        }
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id= item.getItemId();
        if(id==android.R.id.home)
            onBackPressed();
        return super.onOptionsItemSelected(item);
    }

    public static class PlaceholderFragment extends Fragment {

        public List<String> event_name=new ArrayList<String>();
        public List<String> start_time=new ArrayList<String>();
        public List<String> end_time=new ArrayList<String>();
        public List<String> place=new ArrayList<String>();
        public List<String> type=new ArrayList<String>();
        public List<String> date=new ArrayList<String>();

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(ScheduleDatumPOJO data) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Gson gson=new Gson();
            Bundle args = new Bundle();
            args.putString("data",gson.toJson(data));
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            RecyclerView mrecyclerView;
            LinearLayoutManager mlinearLayoutManager;
            ScheduleAdapter madapter;
            Gson gson=new Gson();
            ScheduleDatumPOJO data=gson.fromJson(getArguments().getString("data"),ScheduleDatumPOJO.class);
            View view = inflater.inflate(R.layout.fragment_tabbed_schedule, container, false);

            event_name=data.getEventName();
            start_time=data.getStartTime();
            end_time=data.getEndTime();
            place=data.getPlace();
            type=data.getType();
            date=data.getDate();

            mrecyclerView = (RecyclerView) view.findViewById(R.id.recycler_sched);
            assert mrecyclerView != null;
            mrecyclerView.setHasFixedSize(true);
            mlinearLayoutManager = new LinearLayoutManager(getActivity());
            mrecyclerView.setLayoutManager(mlinearLayoutManager);
            madapter = new ScheduleAdapter(event_name,start_time,end_time,place,type,date,getActivity());
            mrecyclerView.setAdapter(madapter);

            return view;
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public final List<Fragment> mFragmentList=new ArrayList<Fragment>();
        public final List<String>  mFragmentTitleList=new ArrayList<String>();

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentTitleList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
           return mFragmentTitleList.get(position);
        }
    }
}
