package edu.kiet.www.epoque2017.Fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import edu.kiet.www.epoque2017.Activity.Home;
import edu.kiet.www.epoque2017.Adapters.EventAdapter;
import edu.kiet.www.epoque2017.CardObjects.EventCardData;
import edu.kiet.www.epoque2017.Models.EventDetailsPOJO;
import edu.kiet.www.epoque2017.Models.ProfileDataumPOJO;
import edu.kiet.www.epoque2017.R;
import edu.kiet.www.epoque2017.Requests.EventDetailsRequest;
import edu.kiet.www.epoque2017.networking.ServiceGenerator;
import edu.kiet.www.epoque2017.util.DbHandler;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Events extends Fragment {
    private RecyclerView recyclerView;
    private EventAdapter adapter;
    ProgressDialog progressDialog;
    String subC,scateqory,stype;
    Boolean reg_closed=false;
    List<String> name,tagline,eventId,type,category,subCategory,maxPart,minPart,description,time,resultType,image_url,apexStudentName,apexStudentPhone,apexFacName,apexFacPhone;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_events, container, false);
        progressDialog=new ProgressDialog(getContext());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        subC=scateqory=stype="";
        final TextView music,arts,dance,others,institute,department,solo,group;
        name=new ArrayList<>();
        tagline=new ArrayList<>();
        category=new ArrayList<>();
        subCategory=new ArrayList<>();
        maxPart=new ArrayList<>();
        minPart=new ArrayList<>();
        description=new ArrayList<>();
        type=new ArrayList<>();
        time=new ArrayList<>();
        eventId=new ArrayList<>();
        resultType=new ArrayList<>();
        image_url=new ArrayList<>();
        apexFacName=new ArrayList<>();
        apexFacPhone=new ArrayList<>();
        apexStudentName=new ArrayList<>();
        apexStudentPhone=new ArrayList<>();
        recyclerView=(RecyclerView)view.findViewById(R.id.EventRecyclerView);
        final EventDetailsRequest request= ServiceGenerator.createService(EventDetailsRequest.class, DbHandler.getString(getContext(),"bearer",""));
        Call<EventDetailsPOJO> call=request.request();
        call.enqueue(new Callback<EventDetailsPOJO>() {
            @Override
            public void onResponse(Call<EventDetailsPOJO> call, Response<EventDetailsPOJO> response) {
                if(response.code()==200) {
                    if (!response.body().getError()) {
                        progressDialog.dismiss();
                        Gson gson = new Gson();
                        ProfileDataumPOJO data2 = gson.fromJson(DbHandler.getString(getContext(), "profile", ""), ProfileDataumPOJO.class);
                        List<String> reg_event_ids = new ArrayList<String>();
                        reg_event_ids = data2.getEventId();
                        Log.e("evnts", String.valueOf(response.body().getEventName()));
                        name = response.body().getEventName();
                        type = response.body().getType();
                        tagline = response.body().getTagline();
                        category = response.body().getCategory();
                        subCategory = response.body().getSubcategory();
                        description = response.body().getDescription();
                        maxPart = response.body().getMaxParticipation();
                        minPart = response.body().getMinParticipation();
                        time = response.body().getTime();
                        eventId = response.body().getEventId();
                        resultType = response.body().getResultType();
                        image_url = response.body().getImg();

                        adapter = new EventAdapter(getContext(), getData(), reg_event_ids, response.body().getReg_closed());
                        recyclerView.setAdapter(adapter);
                        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                    } else {

                        DbHandler.unsetSession(getContext(), "isForcedLoggedOut");
                    }
                }else{
                    progressDialog.dismiss();
                    new AlertDialog.Builder(getActivity(),R.style.MyAlertDialogStyle)
                            .setTitle("Failed")
                            .setMessage("Failed to connect")
                            .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    getActivity().onBackPressed();
                                    //startActivity(new Intent(getContext(),Home.class));
                                }
                            })
                            .show();

                }

            }

            @Override
            public void onFailure(Call<EventDetailsPOJO> call, Throwable t) {
                t.printStackTrace();
                progressDialog.dismiss();
                Snackbar.make(getActivity().findViewById(android.R.id.content),"Check Your Internet Connection",Snackbar.LENGTH_SHORT).show();
            }
        });

        return view;

    }

    public List<EventCardData> getData(){
        List<EventCardData> data= new ArrayList<>();
        for(int i=0;i<name.size();i++) {
            EventCardData current=new EventCardData();
            current.name=name.get(i);
            current.image_url=image_url.get(i);
            current.tagline=tagline.get(i);
            current.category=category.get(i);
            current.subCategory=subCategory.get(i);
            current.description=description.get(i);
            current.maxPart=maxPart.get(i);
            current.minPart=minPart.get(i);
            current.time=time.get(i);
            current.resultType=resultType.get(i);
            current.type=type.get(i);
            current.eventId=eventId.get(i);

            // current.apexFacName=apexFacName.get(i);
            //current.apexFacPhone=apexFacPhone.get(i);
            //current.apexStudentName=apexStudentName.get(i);
            //current.apexStudentPhone=apexStudentPhone.get(i);
            data.add(current);
        }
        return data;
    }


}
