package edu.kiet.www.epoque2017.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import edu.kiet.www.epoque2017.R;
import edu.kiet.www.epoque2017.util.DbHandler;


public class Profile extends Fragment {

    public Profile(){}
    CardView registeredEvents,ourSponsors,logout,contactUs;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view5= inflater.inflate(R.layout.fragment_profile, container, false);
        logout=(CardView)view5.findViewById(R.id.logout);
        registeredEvents=(CardView)view5.findViewById(R.id.regEvents);
        ourSponsors=(CardView)view5.findViewById(R.id.sponsors);
        contactUs=(CardView)view5.findViewById(R.id.contact);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getActivity())
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
