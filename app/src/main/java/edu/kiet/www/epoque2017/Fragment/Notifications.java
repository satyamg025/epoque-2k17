package edu.kiet.www.epoque2017.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import edu.kiet.www.epoque2017.Adapters.NotificationAdapter;
import edu.kiet.www.epoque2017.R;


public class Notifications extends Fragment {
RecyclerView.Adapter adapter;
    List<String> title,body;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_notification, container, false);
        RecyclerView recyclerView=(RecyclerView)view.findViewById(R.id.recycler);
        title=new ArrayList<>();
        body=new ArrayList<>();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        for(int i=1;i<=10;i++)
        {
            title.add("Title");
            body.add("djdjknkjdfnkdfnkdjfnvkjdfnvkjdfnv\nosdijsdjifisdoj");
        }
        adapter=new NotificationAdapter(title,body);
        recyclerView.setAdapter(adapter);
        return view;
    }


}
