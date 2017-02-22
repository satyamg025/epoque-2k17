package edu.kiet.www.epoque2017.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import edu.kiet.www.epoque2017.Activity.Home;
import edu.kiet.www.epoque2017.Activity.RequestReceived;
import edu.kiet.www.epoque2017.Models.AcceptRejectPOJO;
import edu.kiet.www.epoque2017.Models.RequestReceivedDataumPOJO;
import edu.kiet.www.epoque2017.R;
import edu.kiet.www.epoque2017.Requests.AcceptRejectRequest;
import edu.kiet.www.epoque2017.networking.NetworkCheck;
import edu.kiet.www.epoque2017.networking.ServiceGenerator;
import edu.kiet.www.epoque2017.util.DbHandler;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Shrey on 02-02-2017.
 */

public class RequestReceivedAdapter extends RecyclerView.Adapter<RequestReceivedAdapter.MyViewHolder> {

    //private final LayoutInflater inflater;
    Context context;
    RequestReceivedDataumPOJO data;

    public RequestReceivedAdapter(Context context, RequestReceivedDataumPOJO data){
        this.context=context;
        this.data=data;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_request_card, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        if(!data.getEventImg().get(position).equals("")) {
           Picasso
                    .with(context)
                    .load(data.getEventImg().get(position))
                    .into(holder.EventPhoto);
        }
        holder.EventName.setText(data.getEventName().get(position));
        holder.InvitedBy.setText("Invited By-"+data.getInvitedBy().get(position));
        if(!data.getStatus().get(position).equals("PENDING")){
            holder.ll.setVisibility(View.GONE);
            holder.accept_reject.setVisibility(View.VISIBLE);

            if(data.getStatus().get(position).equals("ACCEPT")){
                holder.accept_reject.setText("ACCEPTED");
                holder.accept_reject.setTextColor(context.getResources().getColor(R.color.global_color_green_primary_dark));
            }
            else if(data.getStatus().get(position).equals("REJECT")){
                holder.accept_reject.setTextColor(context.getResources().getColor(R.color.rejected));
                holder.accept_reject.setText("REJECTED");
            }
        }
        else{

            holder.ll.setVisibility(View.VISIBLE);
            holder.accept_reject.setVisibility(View.GONE);
            holder.Accept.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    holder.Accept.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (!NetworkCheck.isNetworkAvailable(context)) {
                                Toast.makeText(context,"No Network Connection",Toast.LENGTH_SHORT).show();
                                return;
                            }
                            else {
                                /*final ProgressDialog progressDialog=new ProgressDialog(context);

                                progressDialog.setIndeterminate(true);
                                progressDialog.setCancelable(false);
                                progressDialog.setMessage("Loading...");
                                progressDialog.show();*/
                                if(!DbHandler.getString(context,"bearer","").equals("")) {
                                    Toast.makeText(context,"Registering ...",Toast.LENGTH_LONG);

                                    AcceptRejectRequest acceptRejectRequest = ServiceGenerator.createService(AcceptRejectRequest.class,DbHandler.getString(context,"bearer",""));
                                    Call<AcceptRejectPOJO> call = acceptRejectRequest.responseRequest(data.getInviteId().get(position),"1");
                                    call.enqueue(new Callback<AcceptRejectPOJO>() {

                                        @Override
                                        public void onResponse(Call<AcceptRejectPOJO> call, Response<AcceptRejectPOJO> response) {
                                            AcceptRejectPOJO responseBody = response.body();
                                            Log.e("request_data", String.valueOf(responseBody));
                                            if (response.code() == 200) {
                                                if (!responseBody.getError()) {
                                                    // progressDialog.dismiss();
                                                    Toast.makeText(context,responseBody.getMsg(),Toast.LENGTH_SHORT).show();
                                                    Intent intent=new Intent(context, RequestReceived.class);
                                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                    context.startActivity(intent);

                                                } else {
                                                    DbHandler.unsetSession(context, "isForcedLoggedOut");
                                                }
                                            }
                                            else {
                                                //progressDialog.dismiss();
                                                Toast.makeText(context,"Failed to connect",Toast.LENGTH_SHORT).show();
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<AcceptRejectPOJO> call, Throwable t) {
                                            //progressDialog.dismiss();
                                            Toast.makeText(context,"Connection failed",Toast.LENGTH_SHORT).show();

                                        }
                                    });
                                }

                            }
                        }
                    });


                }
            });

            holder.Reject.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (!NetworkCheck.isNetworkAvailable(context)) {
                        Toast.makeText(context,"No Network Connection",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    else {
                                /*final ProgressDialog progressDialog=new ProgressDialog(context);

                                progressDialog.setIndeterminate(true);
                                progressDialog.setCancelable(false);
                                progressDialog.setMessage("Loading...");
                                progressDialog.show();*/
                        if(!DbHandler.getString(context,"bearer","").equals("")) {
                            Toast.makeText(context,"Rejecting ...",Toast.LENGTH_LONG);

                            AcceptRejectRequest acceptRejectRequest = ServiceGenerator.createService(AcceptRejectRequest.class,DbHandler.getString(context,"bearer",""));
                            Call<AcceptRejectPOJO> call = acceptRejectRequest.responseRequest(data.getInviteId().get(position),"0");
                            call.enqueue(new Callback<AcceptRejectPOJO>() {

                                @Override
                                public void onResponse(Call<AcceptRejectPOJO> call, Response<AcceptRejectPOJO> response) {
                                    AcceptRejectPOJO responseBody = response.body();
                                    Log.e("request_data", String.valueOf(responseBody.getMsg()));
                                    if (response.code() == 200) {
                                        if (!responseBody.getError()) {
                                            //progressDialog.dismiss();
                                            Intent intent=new Intent(context, RequestReceived.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            Toast.makeText(context,responseBody.getMsg(),Toast.LENGTH_SHORT).show();
                                            context.startActivity(intent);

                                        } else {
                                            DbHandler.unsetSession(context, "isForcedLoggedOut");
                                        }
                                    } else {
                                        // progressDialog.dismiss();
                                        new AlertDialog.Builder(context)
                                                .setTitle("Failed")
                                                .setMessage("Failed to connect")
                                                .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        context.startActivity(new Intent(context,Home.class));
                                                    }
                                                })
                                                .show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<AcceptRejectPOJO> call, Throwable t) {
                                    // progressDialog.dismiss();
                                    new AlertDialog.Builder(context)
                                            .setMessage("Connection Failed")
                                            .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int which) {
                                                    context.startActivity(new Intent(context,Home.class));
                                                }
                                            })
                                            .show();

                                }
                            });
                        }

                    }

                }
            });
        }
        //holder.EventPhoto.setImageResource(current.eventPhoto);
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
        return data.getEventName().size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView EventName,InvitedBy;
        ImageView EventPhoto;
        Button Accept,Reject,accept_reject;
        LinearLayout ll;


        public MyViewHolder(View itemView) {
            super(itemView);
            EventName=(TextView) itemView.findViewById(R.id.event_name);
            InvitedBy=(TextView)itemView.findViewById(R.id.invited_by);
            EventPhoto=(ImageView)itemView.findViewById(R.id.event_photo);
            Accept=(Button)itemView.findViewById(R.id.accept);
            Reject=(Button)itemView.findViewById(R.id.reject);
            ll=(LinearLayout)itemView.findViewById(R.id.ll1);
            accept_reject=(Button)itemView.findViewById(R.id.accept_reject);


        }
    }
}
