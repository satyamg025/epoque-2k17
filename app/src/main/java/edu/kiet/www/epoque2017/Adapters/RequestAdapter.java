package edu.kiet.www.epoque2017.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import edu.kiet.www.epoque2017.CardObjects.RequestCardData;
import edu.kiet.www.epoque2017.R;

/**
 * Created by Shrey on 02-02-2017.
 */

public class RequestAdapter extends RecyclerView.Adapter<RequestAdapter.MyViewHolder> {

    private final LayoutInflater inflater;
    List<RequestCardData> data= Collections.emptyList();

    public RequestAdapter(Context context,  List<RequestCardData> data){
        inflater=LayoutInflater.from(context);
        this.data=data;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.event_request_card,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        RequestCardData current=data.get(position);
        holder.EventName.setText(current.eventName);
        holder.InvitedBy.setText("Invited By-"+current.invitedBy);
        holder.EventPhoto.setImageResource(current.eventPhoto);
        //holder.Accept.setText(current.accept);
        //holder.Reject.setText(current.reject);
        /*holder.Accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"Accepted",Toast.LENGTH_SHORT).show();
            }
        });*/

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView EventName,InvitedBy;
        ImageView EventPhoto;
        Button Accept,Reject;


        public MyViewHolder(View itemView) {
            super(itemView);
            EventName=(TextView) itemView.findViewById(R.id.event_name);
            InvitedBy=(TextView)itemView.findViewById(R.id.invited_by);
            EventPhoto=(ImageView)itemView.findViewById(R.id.event_photo);
            Accept=(Button)itemView.findViewById(R.id.accept);
            Reject=(Button)itemView.findViewById(R.id.reject);

        }
    }
}
