package edu.kiet.www.epoque2017.Fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import edu.kiet.www.epoque2017.Activity.RequestReceived;
import edu.kiet.www.epoque2017.Activity.RequestSent;
import edu.kiet.www.epoque2017.R;
import edu.kiet.www.epoque2017.networking.NetworkCheck;
import edu.kiet.www.epoque2017.ui.coloredSnackBar;


public class Requests extends Fragment {
Button sent,received;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_requests, container, false);
        sent=(Button)view.findViewById(R.id.sent);
        received=(Button)view.findViewById(R.id.received);
        final ProgressDialog progressDialog = new ProgressDialog(getContext());

        sent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!NetworkCheck.isNetworkAvailable(getActivity())) {

                    Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content), "No network connection", Snackbar.LENGTH_INDEFINITE);
                    coloredSnackBar.alert(snackbar).show();
                    return;
                }
                else {
                    Intent intent = new Intent(getContext(), RequestSent.class);
                    startActivity(intent);
                }

            }
        });

        received.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!NetworkCheck.isNetworkAvailable(getActivity())) {

                    Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content), "No network connection", Snackbar.LENGTH_INDEFINITE);
                    coloredSnackBar.alert(snackbar).show();
                    return;
                }
                else {
                    startActivity(new Intent(getContext(), RequestReceived.class));
                }
            }

        });
        return view;
    }
}
