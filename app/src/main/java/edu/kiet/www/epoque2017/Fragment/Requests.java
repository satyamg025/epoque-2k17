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
        return view;
    }
}
