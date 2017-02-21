package edu.kiet.www.epoque2017.Adapters;

/**
 * Created by satyam on 1/29/17.
 */

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import edu.kiet.www.epoque2017.Models.ResultDataumPOJO;
import edu.kiet.www.epoque2017.R;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder> {

    Context context;
    ResultDataumPOJO data;
    public ResultAdapter(Context context,ResultDataumPOJO data) {
        this.context=context;
        this.data=data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.result_card, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        holder.event_name.setText(data.getEventName().get(position));
        holder.winner1.setText(data.getEventWinnerName().get(position).get(0));
        holder.winner2.setText(data.getEventWinnerName().get(position).get(1));
        holder.winner3.setText(data.getEventWinnerName().get(position).get(2));
       // holder.img.setImageURI(Uri.parse("www.hotel-r.net/im/hotel/be/welcome-2.png"));
    }


    @Override
    public int getItemCount() {
        return data.getEventName().size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView event_name,winner1,winner2,winner3;
        public ViewHolder(View itemView) {
            super(itemView);
            event_name=(TextView)itemView.findViewById(R.id.event_name);
            winner1=(TextView)itemView.findViewById(R.id.winner1);
            winner2=(TextView)itemView.findViewById(R.id.winner2);
            winner3=(TextView)itemView.findViewById(R.id.winner3);
        }
    }
}