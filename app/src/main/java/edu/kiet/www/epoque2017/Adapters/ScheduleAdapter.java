package edu.kiet.www.epoque2017.Adapters;

/**
 * Created by satyam on 1/29/17.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import edu.kiet.www.epoque2017.R;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder> {

    public List<String> event_name=new ArrayList<String>();
    public List<String> start_time=new ArrayList<String>();
    public List<String> end_time=new ArrayList<String>();
    public List<String> place=new ArrayList<String>();
    public List<String> date=new ArrayList<String>();
    public List<String> type=new ArrayList<String>();
    Context context;

    public ScheduleAdapter(List<String> event_name, List<String> start_time, List<String> end_time, List<String> place, List<String> type, List<String> date, Context context) {
        this.event_name=event_name;
        this.start_time=start_time;
        this.end_time=end_time;
        this.place=place;
        this.type=type;
        this.date=date;
        this.context=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule_card, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.event_name.setText(event_name.get(position));
        holder.time.setText(start_time.get(position)+" - "+end_time.get(position));
        holder.venue.setText(place.get(position));
        if(type.get(position).equals("I")){
            holder.type.setText("Institute");
        }
        else if(type.get(position).equals("D")){
            holder.type.setText("Department");
        }
    }


    @Override
    public int getItemCount() {
        return event_name.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView event_name,time,type,venue;
        public ViewHolder(View itemView) {
            super(itemView);
            event_name=(TextView)itemView.findViewById(R.id.event_name);
            time=(TextView)itemView.findViewById(R.id.event_time);
            type=(TextView)itemView.findViewById(R.id.type);
            venue=(TextView)itemView.findViewById(R.id.event_venue);
        }
    }
}