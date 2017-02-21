
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
import edu.kiet.www.epoque2017.CardObjects.SponserCard;
import edu.kiet.www.epoque2017.R;

/**
 * Created by Shrey on 21-02-2017.
 */
public class SponserAdapter extends RecyclerView.Adapter<SponserAdapter.MyViewHolder> {

    private final LayoutInflater inflater;
    Context context;
    List<SponserCard> data= Collections.emptyList();

    public SponserAdapter(Context context, List<SponserCard> data){
        inflater=LayoutInflater.from(context);
        this.data=data;
        this.context=context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.sponsor,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        SponserCard current=data.get(position);
        holder.EventPhoto.setImageResource(current.image);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView EventPhoto;


        public MyViewHolder(View itemView) {
            super(itemView);
            EventPhoto=(ImageView)itemView.findViewById(R.id.sponser_image);
        }
    }
}