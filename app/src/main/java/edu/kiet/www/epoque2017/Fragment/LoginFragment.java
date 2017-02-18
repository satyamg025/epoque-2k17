package edu.kiet.www.epoque2017.Fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import edu.kiet.www.epoque2017.Activity.Home;
import edu.kiet.www.epoque2017.Models.LoginPOJO;
import edu.kiet.www.epoque2017.R;
import edu.kiet.www.epoque2017.Requests.LoginRequest;
import edu.kiet.www.epoque2017.Splash.SplashActivity;
import edu.kiet.www.epoque2017.networking.NetworkCheck;
import edu.kiet.www.epoque2017.networking.ServiceGenerator;
import edu.kiet.www.epoque2017.ui.coloredSnackBar;
import edu.kiet.www.epoque2017.util.DbHandler;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginFragment extends Fragment {
    private View parentView;
    TextInputLayout user_layout,pass_layout;
    EditText username,password;
    LinearLayout ll;
    Button login;
    private View.OnClickListener mSnackBarClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            login();
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        parentView=inflater.inflate(R.layout.fragment_login, container, false);
        login=(Button)getActivity().findViewById(R.id.login);
        ll=(LinearLayout)getActivity().findViewById(R.id.layout_buttons);
        ll.setVisibility(View.VISIBLE);

        username=(EditText)parentView.findViewById(R.id.input_roll);
        password=(EditText)parentView.findViewById(R.id.input_password);
        user_layout=(TextInputLayout) parentView.findViewById(R.id.input_roll_layout);
        pass_layout=(TextInputLayout) parentView.findViewById(R.id.input_password_layout);

        username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user_layout.isErrorEnabled() && pass_layout.isErrorEnabled()){
                    user_layout.setErrorEnabled(false);
                    pass_layout.setErrorEnabled(false);
                }
                else if(user_layout.isErrorEnabled()){
                    user_layout.setErrorEnabled(false);
                }
            }
        });

        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user_layout.isErrorEnabled() && pass_layout.isErrorEnabled()){
                    user_layout.setErrorEnabled(false);
                    pass_layout.setErrorEnabled(false);
                }
                else if(pass_layout.isErrorEnabled()){
                    pass_layout.setErrorEnabled(false);
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }

        });
        return parentView;
    }


    public void login(){

        if(username.getText().toString().equals("") && password.getText().toString().equals("")){
            user_layout.setError("Please enter username");
            pass_layout.setError("Please enter password");
        }
        else if(username.getText().toString().equals("")){
            user_layout.setError("Please enter username");
        }
        else if(password.getText().toString().equals("")){
            pass_layout.setError("Please enter password");
        }

        else {

            if (!NetworkCheck.isNetworkAvailable(getActivity())) {

                Snackbar snackbar = Snackbar.make(getActivity().findViewById(android.R.id.content), "No network connection", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Retry", mSnackBarClickListener);
                coloredSnackBar.alert(snackbar).show();
                return;
            } else {
                final ProgressDialog progressDialog = new ProgressDialog(getActivity());
                progressDialog.setIndeterminate(true);
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Authenticating...");
                progressDialog.show();

                LoginRequest loginRequest = ServiceGenerator.createService(LoginRequest.class, username.getText().toString(), password.getText().toString());
                Call<LoginPOJO> call = loginRequest.responseRequest();
                call.enqueue(new Callback<LoginPOJO>() {
                    @Override
                    public void onResponse(Call<LoginPOJO> call, Response<LoginPOJO> response) {
                        LoginPOJO responseBody = response.body();
                        Log.e("Login_data", String.valueOf(responseBody) + " " + String.valueOf(response.code()));
                        if (response.code() == 200) {
                            if (!responseBody.getError()) {
                                progressDialog.dismiss();
                                DbHandler.setSession(getActivity(), responseBody.getKey(), responseBody.getPerson());
                                startActivity(new Intent(getActivity(), Home.class));
                            } else {
                                new AlertDialog.Builder(getActivity())
                                        .setTitle("Failed")
                                        .setMessage("Login Failed")
                                        .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int which) {
                                                getActivity().onBackPressed();

                                            }
                                        })
                                        .show();
                            }
                        } else {
                            new AlertDialog.Builder(getActivity())
                                    .setTitle("Failed")
                                    .setMessage("Failed to connect")
                                    .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent = new Intent(getActivity(), SplashActivity.class);
                                            startActivity(intent);
                                        }
                                    })
                                    .show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginPOJO> call, Throwable t) {
                        progressDialog.dismiss();
                        new AlertDialog.Builder(getActivity())
                                .setMessage("Connection Failed")
                                .setNegativeButton("Retry", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        login();
                                    }
                                })
                                .show();

                    }
                });
            }
        }

    }


}
