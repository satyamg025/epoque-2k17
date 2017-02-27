package edu.kiet.www.epoque2017.Adapters;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import edu.kiet.www.epoque2017.Activity.EventActivity;
import edu.kiet.www.epoque2017.CardObjects.EventCardData;
import edu.kiet.www.epoque2017.R;
import edu.kiet.www.epoque2017.networking.NotificationPublisher;

/**
 * Created by Shrey on 02-02-2017.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyViewHolder> {

    private final LayoutInflater inflater;
    Context context;
    List<EventCardData> data= Collections.emptyList();
    Boolean reg_closed=false;
    List<String> reg_events_ids=new ArrayList<String>();

    public EventAdapter(Context context, List<EventCardData> data, List<String> reg_event_ids, Boolean reg_closed){
        inflater=LayoutInflater.from(context);
        this.data=data;
        this.reg_events_ids=reg_event_ids;
        this.context=context;
        this.reg_closed=reg_closed;
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
        holder.EventName.setText(current.name);
        Picasso
                .with(context)
                .load(current.image_url)
                .fit()
                .error(R.drawable.logo2)
                .into(holder.EventPhoto);
        Log.e("Image url",current.image_url);
        if(reg_events_ids.contains(current.eventId)) {
            if (!current.time.equals("---")) {
                String tim = current.time;
                String ti[] = tim.split(" ");
                String time = ti[0] + " " + ti[1];

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                try {
                    Date date1 = sdf.parse(time);
                    // Toast.makeText(context, String.valueOf(time), Toast.LENGTH_SHORT).show();
                    Date date2 = new Date();
                    //Toast.makeText(context, String.valueOf(date1), Toast.LENGTH_SHORT).show();
                    //Toast.makeText(context, String.valueOf(date2), Toast.LENGTH_SHORT).show();
                    long mills = (date1.getTime() - date2.getTime()) - 7200000;
                    //Toast.makeText(context, String.valueOf(mills), Toast.LENGTH_SHORT).show();
                    if (mills > 0) {
                        scheduleNotification(getNotification(" 2 hours left for "+current.name+". Be ready"), mills);
                    }

                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    private void scheduleNotification(Notification notification, long delay) {

        Intent notificationIntent = new Intent(context, NotificationPublisher.class);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        long futureInMillis = SystemClock.elapsedRealtime() + delay;
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis, pendingIntent);


    }

    private Notification getNotification(String content) {
        Notification.Builder builder = new Notification.Builder(context);
        builder.setContentTitle("Epoque 2k17");
        builder.setContentText(content);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder.setColor(context.getResources().getColor(R.color.black));
        }
        builder.setSmallIcon(R.drawable.ic_noti);

        return builder.build();
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
                    EventCardData ob=data.get(getAdapterPosition());
                    Bundle bundle=new Bundle();
                    Log.e("type",ob.type);
                    bundle.putString("name",ob.name);
                    bundle.putString("tagline",ob.tagline);
                    bundle.putString("category",ob.category);
                    bundle.putString("type",ob.type);
                    bundle.putString("description",ob.description);
                    bundle.putString("minPart",ob.minPart);
                    bundle.putString("maxPart",ob.maxPart);
                    bundle.putString("time",ob.time);
                    bundle.putString("eventId",ob.eventId);
                    bundle.putBoolean("reg_closed",reg_closed);
                    //bundle.putString("apexFacName",ob.apexFacName);
                    //bundle.putString("apexFacPhone",ob.apexFacPhone);
                    //bundle.putString("apexStudentName",ob.apexStudentName);
                    //bundle.putString("apexStudentPhone",ob.apexStudentPhone);
                    intent.putExtra("bundle",bundle);
                    context.startActivity(intent);
                }
            });

        }
    }
}
