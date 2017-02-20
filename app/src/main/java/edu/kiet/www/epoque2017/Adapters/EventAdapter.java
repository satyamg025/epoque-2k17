package edu.kiet.www.epoque2017.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import edu.kiet.www.epoque2017.Activity.EventActivity;
import edu.kiet.www.epoque2017.CardObjects.EventCardData;
import edu.kiet.www.epoque2017.R;

/**
 * Created by Shrey on 02-02-2017.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyViewHolder> {

    private final LayoutInflater inflater;
    Context context;
    List<EventCardData> data= Collections.emptyList();

    public EventAdapter(Context context, List<EventCardData> data){
        inflater=LayoutInflater.from(context);
        this.data=data;
        this.context=context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.event_card,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        EventCardData current=data.get(position);
        holder.EventName.setText(current.eventName);
        holder.EventPhoto.setImageResource(current.eventPhoto);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView EventName;
        ImageView EventPhoto;


        public MyViewHolder(View itemView) {
            super(itemView);
            EventName=(TextView) itemView.findViewById(R.id.event_name);
            EventPhoto=(ImageView)itemView.findViewById(R.id.event_photo);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context,EventActivity.class);
                    context.startActivity(intent);
                }
            });

        }
    }
}
