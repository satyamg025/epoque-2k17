package edu.kiet.www.epoque2017.Fragment;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.kiet.www.epoque2017.R;


public class Profile extends Fragment {


    //  private OnFragmentInteractionListener mListener;

    public Profile(){}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view5= inflater.inflate(R.layout.fragment_profile, container, false);

        return view5;
    }

}
