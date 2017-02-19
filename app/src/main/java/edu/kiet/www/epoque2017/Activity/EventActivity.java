package edu.kiet.www.epoque2017.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import edu.kiet.www.epoque2017.R;

/**
 * Created by Shrey on 10-02-2017.
 */

public class EventActivity extends AppCompatActivity {
    TextView  eventName,tagline,category,type,minParticipants,maxParticipants,description,time,studentApexName,studentApexPhone,facultyApexName,facultyApexPhone,facultyApexDept;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        eventName=(TextView)findViewById(R.id.event_name);
        tagline=(TextView)findViewById(R.id.tagline);
        category=(TextView)findViewById(R.id.category);
        type=(TextView)findViewById(R.id.type);
        minParticipants=(TextView)findViewById(R.id.min_participants);
        maxParticipants=(TextView)findViewById(R.id.max_participants);
        description=(TextView)findViewById(R.id.event_description);
        time=(TextView)findViewById(R.id.time);
        studentApexName=(TextView)findViewById(R.id.student_apex_name);
        studentApexPhone=(TextView)findViewById(R.id.student_apex_phone);
        facultyApexName=(TextView)findViewById(R.id.faculty_apex_name);
        facultyApexPhone=(TextView)findViewById(R.id.facuty_apex_phone);
        facultyApexDept=(TextView)findViewById(R.id.facuty_apex_dept);

    }
}
