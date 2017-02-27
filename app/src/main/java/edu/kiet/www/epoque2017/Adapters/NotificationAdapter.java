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

import edu.kiet.www.epoque2017.R;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {

    String title[]=null;
    String messge[]=null;
    Context context;

    public NotificationAdapter(Context applicationContext, String[] ti, String[] msg) {
        this.context=context;
        this.title=ti;
        this.messge=msg;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_card, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {


        holder.titl.setText(title[position]);
        holder.msg.setText(messge[position]);
    }


    @Override
    public int getItemCount() {
        return title.length;
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView titl,msg;
        public ViewHolder(View itemView) {
            super(itemView);
            titl=(TextView)itemView.findViewById(R.id.title);
            msg=(TextView)itemView.findViewById(R.id.body);

        }
    }
}

