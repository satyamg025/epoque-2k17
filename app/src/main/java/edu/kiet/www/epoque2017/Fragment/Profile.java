package edu.kiet.www.epoque2017.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import edu.kiet.www.epoque2017.Adapters.EventAdapter;
import edu.kiet.www.epoque2017.Adapters.RegisteredEventsAdapter;
import edu.kiet.www.epoque2017.CardObjects.EventCardData;
import edu.kiet.www.epoque2017.CardObjects.RegisteredEventCard;
import edu.kiet.www.epoque2017.Models.ProfileDataumPOJO;
import edu.kiet.www.epoque2017.R;
import edu.kiet.www.epoque2017.util.DbHandler;


public class Profile extends Fragment {
    private RecyclerView recyclerView;
    private RegisteredEventsAdapter adapter;
    CardView  logout_card;
    public Profile(){}
    TextView name;
    String name_displayed;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view5= inflater.inflate(R.layout.fragment_profile, container, false);
        name=(TextView)view5.findViewById(R.id.name);
        recyclerView=(RecyclerView)view5.findViewById(R.id.registeredEventsRecyclerView);
        Gson gson=new Gson();
        ProfileDataumPOJO data=gson.fromJson(DbHandler.getString(getContext(),"profile",""),ProfileDataumPOJO.class);
        name_displayed=data.getName();
        name_displayed=name_displayed.trim();
        name_displayed=Character.toUpperCase(name_displayed.charAt(0))+name_displayed.substring(1).toLowerCase();
        name.setText(name_displayed);
        adapter=new RegisteredEventsAdapter(getContext(),data);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(view5.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        logout_card=(CardView)view5.findViewById(R.id.logout);
        logout_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getActivity(),R.style.MyAlertDialogStyle)
                        .setTitle("Logout")
                        .setMessage("Are you sure you want to Logout?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                DbHandler.unsetSession(getActivity(), "isLoggedOut");
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .show();

            }
        });

        return view5;
    }



}
