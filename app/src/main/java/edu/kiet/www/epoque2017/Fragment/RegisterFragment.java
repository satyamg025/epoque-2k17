package edu.kiet.www.epoque2017.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import edu.kiet.www.epoque2017.Activity.Home;
import edu.kiet.www.epoque2017.R;


public class RegisterFragment extends Fragment {
    Button register;

private View parentView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        parentView=inflater.inflate(R.layout.fragment_register, container, false);
        register=(Button)parentView.findViewById(R.id.btn_register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),Home.class));
            }
        });
        return parentView;
    }

}
