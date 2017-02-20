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

import edu.kiet.www.epoque2017.R;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {
    public List<String> title=new ArrayList<String>();
    public List<String> body=new ArrayList<String>();

    public NotificationAdapter(List<String> title,List<String> body){
        this.title=title;
        this.body=body;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.notification_card, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        // holder.img.setImageURI(Uri.parse("www.hotel-r.net/im/hotel/be/welcome-2.png"));
    }


    @Override
    public int getItemCount() {
        return title.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView img;
        public ViewHolder(View itemView) {
            super(itemView);

        }
    }
}

