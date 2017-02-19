package edu.kiet.www.epoque2017.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import edu.kiet.www.epoque2017.R;

/**
 * Created by sooraj on 15-02-2017.
 */

public class RequestSentDialogFragment extends DialogFragment {
 public static RequestSentDialogFragment newInstance(String text)
 {
     RequestSentDialogFragment ob=new RequestSentDialogFragment();
     Bundle bundle=new Bundle();
     bundle.putString("text",text);
     Log.e("text",text);
     ob.setArguments(bundle);
     return ob;
 }
    public Dialog onCreateDialog( Bundle savedInstanceState) {
        View view=getActivity().getLayoutInflater().inflate(R.layout.request_sent_dialog_fragment,null);
        EditText user=(EditText) view.findViewById(R.id.user);
        user.setText(getArguments().getString("text"));
        AlertDialog.Builder builder= new AlertDialog.Builder(getContext());
                builder.setView(view)
                        .setPositiveButton("change", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        Dialog dialog=builder.create();
        return dialog;
    }
}
