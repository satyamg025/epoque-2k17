package edu.kiet.www.epoque2017.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.kiet.www.epoque2017.Activity.ResultActivity;
import edu.kiet.www.epoque2017.Activity.TabbedSchedule;
import edu.kiet.www.epoque2017.R;


/**
 * Created by satyam on 2/9/17.
 */
public class fragment_sched_result extends Fragment {
    View view;
    AppCompatButton btn_sched,btn_result;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_schedule_result, container, false);

        btn_sched=(AppCompatButton)view.findViewById(R.id.btn_schedule);
        btn_result=(AppCompatButton)view.findViewById(R.id.btn_result);

        btn_sched.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), TabbedSchedule.class);
                startActivity(intent);
            }
        });

        btn_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), ResultActivity.class);
                startActivity(intent);

            }
        });
        return view;
    }
}
