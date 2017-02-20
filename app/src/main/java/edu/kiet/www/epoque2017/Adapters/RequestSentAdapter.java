package edu.kiet.www.epoque2017.Adapters;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.kiet.www.epoque2017.CardObjects.RequestSentCardData;
import edu.kiet.www.epoque2017.Fragment.RequestSentDialogFragment;
import edu.kiet.www.epoque2017.Models.RequestSentDataumPOJO;
import edu.kiet.www.epoque2017.Models.RequestSentPOJO;
import edu.kiet.www.epoque2017.R;

/**
 * Created by sooraj on 13-02-2017.
 */

public class RequestSentAdapter extends RecyclerView.Adapter<RequestSentAdapter.view_holder>{
    public List<RequestSentDataumPOJO> data=new ArrayList<RequestSentDataumPOJO>();
    public Context context;
    public CardView cardView;
    public LinearLayout layout;
    public LinearLayout linearLayout;
    public TextView textView;
    public TextView textView2;
    public ImageView status;
    FragmentManager fragmentManager;
    public RequestSentAdapter(Context context, List<RequestSentDataumPOJO> data, FragmentManager fragmentManager){
        this.data=data;
        this.context=context;
        this.fragmentManager=fragmentManager;
    }

    public class view_holder extends RecyclerView.ViewHolder {
        TextView eventName;ImageView eventImage;
        public view_holder(final View itemView) {
            super(itemView);
            eventImage=(ImageView)itemView.findViewById(R.id.sent_image);
            eventName=(TextView)itemView.findViewById(R.id.sent_event_name);
            cardView=(CardView)itemView.findViewById(R.id.sent_card);
            layout=(LinearLayout)itemView.findViewById(R.id.sent_layout);


        }
    }

    @Override
    public view_holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.request_sent_card,parent,false);
        return new view_holder(view);
    }

    @Override
    public void onBindViewHolder(view_holder holder, final int position) {
       // final RequestSentCardData current = data.get(position);
        holder.setIsRecyclable(false);
        holder.eventName.setText(data.get(position).getEventName());
        //holder.eventImage.setImageResource(current.image);
        for (int i = 0; i < data.get(position).getInvitationTo().size(); i++)
        {
            linearLayout=new LinearLayout(context);
            status=new ImageView(context);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            textView=new TextView(context);
            textView2=new TextView(context);
            int id=position*100+i;
            linearLayout.setId(id);
           // Log.e("id",Integer.toString(linearLayout.getId()));
            status.setLayoutParams(new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));
            textView.setLayoutParams(new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            textView2.setLayoutParams(new LinearLayoutCompat.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            linearLayout.setHorizontalGravity(View.TEXT_ALIGNMENT_CENTER);
            if(data.get(position).getStatus().get(i).equals("ACCEPTED")) {
                textView.setText(data.get(position).getInvitationTo().get(i));
                textView2.setText("Accepted");
                status.setImageResource(R.drawable.accepted);
                textView.setTextColor(context.getResources().getColor(R.color.accepted));
                textView2.setTextColor(context.getResources().getColor(R.color.accepted));
                status.setColorFilter(context.getResources().getColor(R.color.accepted));
            }
            else if(data.get(position).getStatus().get(i).equals("REJECTED")) {
                textView.setText(data.get(position).getInvitationTo().get(i));
                textView2.setText("Rejected");
                status.setImageResource(R.drawable.rejected);
                textView.setTextColor(context.getResources().getColor(R.color.rejected));
                textView2.setTextColor(context.getResources().getColor(R.color.rejected));
                status.setColorFilter(context.getResources().getColor(R.color.rejected));
            }

            else if(data.get(position).getStatus().get(i).equals("PENDING")){
                textView.setText(data.get(position).getInvitationTo().get(i));
                textView2.setText("Pending");
                status.setImageResource(R.drawable.pending);
                textView.setTextColor(context.getResources().getColor(R.color.pending));
                textView2.setTextColor(context.getResources().getColor(R.color.pending));
                status.setColorFilter(context.getResources().getColor(R.color.pending));
            }
            textView.setText("\t"+textView.getText()+"\t\t\t\t\t\t\t\t");

            textView.setTextSize(20);
            textView2.setTextSize(20);
            linearLayout.addView(textView);
            linearLayout.addView(status);
            linearLayout.addView(textView2);
            linearLayout.setClickable(true);
            layout.addView(linearLayout);
            if((data.get(position).getStatus().get(i).equals("PENDING") || data.get(position).getStatus().get(i).equals("REJECTED"))&& data.get(position).getTeamLeaderBool()) {
                linearLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int id = view.getId() % 100;
                        RequestSentDialogFragment dialog = RequestSentDialogFragment.newInstance(context,data.get(position).getInvitationTo().get(id).trim(),data.get(position).getEventId());
                        dialog.show(fragmentManager, "fm");
                        Log.e(data.get(position).getInvitationTo().get(id).trim(), Integer.toString(id));
                    }
                });
            }
        }


    }
    @Override
    public int getItemCount() {
        return data.size();
    }
}
