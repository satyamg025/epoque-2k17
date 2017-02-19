package edu.kiet.www.epoque2017.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import edu.kiet.www.epoque2017.R;

/**
 * Created by sooraj on 18-02-2017.
 */

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.viewholder>{
    List<String> title,body;
    public NotificationAdapter(List<String> title,List<String> body){
        this.title=title;
        this.body=body;
    }
    public class viewholder extends RecyclerView.ViewHolder {
        TextView titlev,bodyv;
        public viewholder(View itemView) {
            super(itemView);
            titlev=(TextView)itemView.findViewById(R.id.title);
            bodyv=(TextView)itemView.findViewById(R.id.body);

        }
    }
    @Override
    public NotificationAdapter.viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_card,parent,false);
        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(NotificationAdapter.viewholder holder, int position) {
        holder.titlev.setText(title.get(position));
        holder.bodyv.setText(body.get(position));
    }

    @Override
    public int getItemCount() {
        return title.size();
    }
}
