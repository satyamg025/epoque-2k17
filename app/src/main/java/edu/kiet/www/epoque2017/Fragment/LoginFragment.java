package edu.kiet.www.epoque2017.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import org.w3c.dom.Text;

import edu.kiet.www.epoque2017.Activity.Home;
import edu.kiet.www.epoque2017.R;


public class LoginFragment extends Fragment {
    private View parentView;
    TextInputLayout user_layout,pass_layout;
    EditText username,password;
    Button login;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        parentView=inflater.inflate(R.layout.fragment_login, container, false);
        login=(Button)parentView.findViewById(R.id.login);
        username=(EditText)parentView.findViewById(R.id.input_roll);
        password=(EditText)parentView.findViewById(R.id.input_password);
        user_layout=(TextInputLayout) parentView.findViewById(R.id.input_roll_layout);
        pass_layout=(TextInputLayout) parentView.findViewById(R.id.input_password_layout);
        return parentView;
    }



}
