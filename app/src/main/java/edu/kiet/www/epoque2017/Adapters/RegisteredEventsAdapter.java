package edu.kiet.www.epoque2017.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import edu.kiet.www.epoque2017.Activity.Home;
import edu.kiet.www.epoque2017.Models.CancelEventPOJO;
import edu.kiet.www.epoque2017.Models.ProfileDataumPOJO;
import edu.kiet.www.epoque2017.R;
import edu.kiet.www.epoque2017.Requests.CancelRequest;
import edu.kiet.www.epoque2017.Requests.CancelRequest2;
import edu.kiet.www.epoque2017.networking.ServiceGenerator;
import edu.kiet.www.epoque2017.util.DbHandler;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Shrey on 02-02-2017.
 */

public class RegisteredEventsAdapter extends RecyclerView.Adapter<RegisteredEventsAdapter.MyViewHolder> {

    private final LayoutInflater inflater;
    Context context;
    ProfileDataumPOJO data;

    public RegisteredEventsAdapter(Context context, ProfileDataumPOJO data){
        inflater=LayoutInflater.from(context);
        this.data=data;
        this.context=context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.registered_events,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.EventName.setText(data.getEventName().get(position));
        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id=holder.getAdapterPosition();
                if(data.getTeamLeaderBool().get(id)){
                    if(data.getEventCategory().get(id).equals("S")){

                        if(!DbHandler.getString(context,"bearer","").equals("")) {
                            Toast.makeText(context,"Cancelling...",Toast.LENGTH_LONG).show();

                            CancelRequest cancelRequest = ServiceGenerator.createService(CancelRequest.class,DbHandler.getString(context,"bearer",""));
                            Call<CancelEventPOJO> call = cancelRequest.response(data.getEventId().get(position));
                            call.enqueue(new Callback<CancelEventPOJO>() {

                                @Override
                                public void onResponse(Call<CancelEventPOJO> call, Response<CancelEventPOJO> response) {
                                    CancelEventPOJO responseBody = response.body();
                                    Log.e("request_data", String.valueOf(responseBody));
                                    if (response.code() == 200) {
                                        if (!responseBody.getError()) {
                                            Toast.makeText(context,"Event cancelled successfully",Toast.LENGTH_LONG).show();
                                            Intent intent=new Intent(context,Home.class);
                                            context.startActivity(intent);


                                        } else {
                                            Toast.makeText(context,"Some error occured try again later",Toast.LENGTH_LONG).show();
                                        }
                                    } else {
                                        Toast.makeText(context,"Failed to connect",Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<CancelEventPOJO> call, Throwable t) {
                                    Toast.makeText(context,"Connection failed",Toast.LENGTH_LONG).show();
                                }
                            });
                        }


                    }
                    else{

                        if(!DbHandler.getString(context,"bearer","").equals("")) {

                            CancelRequest2 cancelRequest2 = ServiceGenerator.createService(CancelRequest2.class,DbHandler.getString(context,"bearer",""));
                            Call<CancelEventPOJO> call = cancelRequest2.response(data.getEventId().get(position));;
                            call.enqueue(new Callback<CancelEventPOJO>() {

                                @Override
                                public void onResponse(Call<CancelEventPOJO> call, Response<CancelEventPOJO> response) {
                                    CancelEventPOJO responseBody = response.body();
                                    Log.e("request_data", String.valueOf(responseBody));
                                    if (response.code() == 200) {
                                        if (!responseBody.getError()) {
                                            Toast.makeText(context,"Event cancelled successfully",Toast.LENGTH_LONG).show();
                                            Intent intent=new Intent(context,Home.class);
                                            context.startActivity(intent);


                                        } else {
                                            Toast.makeText(context,"Some error occured try again later",Toast.LENGTH_LONG).show();
                                        }
                                    } else {
                                        Toast.makeText(context,"Failed to connect",Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<CancelEventPOJO> call, Throwable t) {
                                    Toast.makeText(context,"Connection failed",Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    }


                }
                else{
                    Toast.makeText(context,"Only team leader can cancel this registered event.",Toast.LENGTH_LONG).show();
                }

            }



        });

    }

    @Override
    public int getItemCount() {
        return data.getEventName().size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView EventName;
        Button cancel;
        public MyViewHolder(View itemView) {
            super(itemView);
            EventName=(TextView) itemView.findViewById(R.id.event_name);
            cancel=(Button)itemView.findViewById(R.id.cancel);
        }
    }
}
