package edu.kiet.www.epoque2017.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import edu.kiet.www.epoque2017.Activity.RequestReceived;
import edu.kiet.www.epoque2017.Activity.RequestSent;
import edu.kiet.www.epoque2017.R;


public class Requests extends Fragment {
Button sent,received;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_requests, container, false);
        sent=(Button)view.findViewById(R.id.sent);
        received=(Button)view.findViewById(R.id.received);
        sent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), RequestSent.class));
            }
        });

        received.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), RequestReceived.class));
            }
        });
        return view;
    }
}
