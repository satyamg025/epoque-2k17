package edu.kiet.www.epoque2017.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;

import edu.kiet.www.epoque2017.R;

/**
 * Created by Shrey on 20-02-2017.
 */

public class contactUsDialogFragment extends DialogFragment {

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.contact_us_dialog, null);
        builder.setView(view);
        builder.setTitle("Contact Us");
        builder.setIcon(R.drawable.phone);
        setCancelable(false);
        builder.setPositiveButton("Close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dismiss();
            }
        });
        Dialog dialog=builder.create();
        return dialog;
    }
}
