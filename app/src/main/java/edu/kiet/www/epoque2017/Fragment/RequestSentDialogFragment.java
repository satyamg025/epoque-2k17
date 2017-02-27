package edu.kiet.www.epoque2017.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import edu.kiet.www.epoque2017.Activity.Home;
import edu.kiet.www.epoque2017.Activity.RequestSent;
import edu.kiet.www.epoque2017.Models.ChangeRequestPOJO;
import edu.kiet.www.epoque2017.R;
import edu.kiet.www.epoque2017.Requests.ChangeRequest;
import edu.kiet.www.epoque2017.networking.NetworkCheck;
import edu.kiet.www.epoque2017.networking.ServiceGenerator;
import edu.kiet.www.epoque2017.ui.coloredSnackBar;
import edu.kiet.www.epoque2017.util.DbHandler;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/**
 * Created by sooraj on 15-02-2017.
 */

public class RequestSentDialogFragment extends DialogFragment {
    Context context;
    TextView cancel,change;
    public static RequestSentDialogFragment newInstance(Context context, String change_from, String event_id)
    {
        RequestSentDialogFragment ob=new RequestSentDialogFragment();
        Bundle bundle=new Bundle();
        bundle.putString("change_from",change_from);
        bundle.putString("event_id",event_id);
        Log.e("text",change_from);
        ob.setArguments(bundle);
        return ob;
    }
    public Dialog onCreateDialog( Bundle savedInstanceState) {
        final View view=getActivity().getLayoutInflater().inflate(R.layout.request_sent_dialog_fragment,null);
        final EditText user=(EditText) view.findViewById(R.id.user);
        cancel=(TextView) view.findViewById(R.id.cancel);
        change=(TextView) view.findViewById(R.id.change);
        user.setText(getArguments().getString("change_from"));
        final AlertDialog.Builder builder= new AlertDialog.Builder(getContext());
        builder.setView(view);
        final Dialog dialog=builder.create();
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                if (!NetworkCheck.isNetworkAvailable(getActivity())) {

                    Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content), "No network connection", Snackbar.LENGTH_LONG);
                    coloredSnackBar.alert(snackbar).show();
                    return;
                }
                else if(user.getText().toString().trim().isEmpty())
                user.setError("Please enter a valid Library Id");
                else{

                    final ProgressDialog progressDialog=new ProgressDialog(getActivity());

                    progressDialog.setIndeterminate(true);
                    progressDialog.setCancelable(false);
                    progressDialog.setMessage("Loading...");
                    progressDialog.show();


                    ChangeRequest changeRequest = ServiceGenerator.createService(ChangeRequest.class,DbHandler.getString(getActivity(),"bearer",""));

                    Call<ChangeRequestPOJO> call = changeRequest.requestResponse(getArguments().getString("change_from",""),user.getText().toString(),getArguments().getString("event_id",""));
                    call.enqueue(new Callback<ChangeRequestPOJO>() {

                        @Override
                        public void onResponse(Call<ChangeRequestPOJO> call, Response<ChangeRequestPOJO> response) {
                            progressDialog.dismiss();
                            ChangeRequestPOJO responseBody=response.body();
                            Log.e("rose",String.valueOf(response.code()));
                            if(response.code()==200) {

                                if (responseBody.getError().equals("200")) {
                                    Toast.makeText(view.getContext(), "Teammate changed successfully!!!", Toast.LENGTH_SHORT).show();
                                    getContext().startActivity(new Intent(getContext(), RequestSent.class));
                                }
                                else if(responseBody.getError().equals("404")) {
                                    DbHandler.unsetSession(getActivity(), "isForcedLoggedOut");
                                }
                                else {
                                    Toast.makeText(view.getContext(), responseBody.getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            }
                            else {
                                new AlertDialog.Builder(getContext())
                                        .setTitle("Failed")
                                        .setMessage("Failed to connect")
                                        .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                Intent intent = new Intent(getContext(), Home.class);
                                                startActivity(intent);
                                            }
                                        })
                                        .show();
                            }

                        }

                        @Override
                        public void onFailure(Call<ChangeRequestPOJO> call, Throwable t) {
                            progressDialog.dismiss();
                            new AlertDialog.Builder(getActivity())
                                    .setMessage("Connection Failed")
                                    .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent = new Intent(getContext(), Home.class);
                                            startActivity(intent);
                                        }
                                    })
                                    .show();

                        }
                    });
                }

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

            }
        });

        return dialog;
    }

}
