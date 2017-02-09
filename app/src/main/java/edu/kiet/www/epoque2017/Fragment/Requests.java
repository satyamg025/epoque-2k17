package edu.kiet.www.epoque2017.Fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import edu.kiet.www.epoque2017.Adapters.RequestAdapter;
import edu.kiet.www.epoque2017.R;
import edu.kiet.www.epoque2017.CardObjects.RequestCardData;


public class Requests extends Fragment {
    private RecyclerView recyclerView;
    private RequestAdapter adapter;
    //CardView eventCard;

    public Requests() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_requests, container, false);
       // eventCard=(CardView)view.findViewById(R.id.event_card);
        recyclerView=(RecyclerView)view.findViewById(R.id.requestRecyclerView);
        adapter=new RequestAdapter(getContext(),getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //eventCard.setRadius(20);
        return view;
    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    public static List<RequestCardData> getData(){
        List<RequestCardData> data= new ArrayList<>();
        int eventImage=R.drawable.logo2;
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
}
