package com.nic.newapkproject.Activity.Activity.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.nic.newapkproject.R;

import java.io.*;

public class SecondFragment extends Fragment {

    TextView person_id;
    TextView name;
    TextView email;
    TextView gender;
    TextView status;
    TextView comment;
    LinearLayout comment_layout;


    int person_id_value=0;
    String name_value="";
    String email_value="";
    String gender_value="";
    String status_value="";
    String comment_value="";
    public SecondFragment(){
        // require a empty public constructor
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Bundle bundle = new Bundle();
        bundle.putInt("person_id", person_id_value);
        bundle.putString("name",name_value);
        bundle.putString("email",email_value);
        bundle.putString("gender",gender_value);
        bundle.putString("status",status_value);
        bundle.putString("comment",comment_value);
        outState.putBundle("bundle",bundle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
            if(savedInstanceState!=null){
                Bundle bundle = savedInstanceState.getBundle("bundle");

                person_id_value = bundle.getInt("person_id",0);
                name_value = bundle.getString("name");
                email_value = bundle.getString("email");
                gender_value = bundle.getString("gender");
                status_value = bundle.getString("status");
                comment_value = bundle.getString("comment");
            }
            else {
                person_id_value = getArguments().getInt("person_id", 0);
                name_value = getArguments().getString("name");
                email_value = getArguments().getString("email");
                gender_value = getArguments().getString("gender");
                status_value = getArguments().getString("status");
                comment_value = getArguments().getString("comment");
            }


        person_id = view.findViewById(R.id.person_id);
        name = view.findViewById(R.id.name);
        email = view.findViewById(R.id.email);
        gender = view.findViewById(R.id.gender);
        status = view.findViewById(R.id.status);
        comment = view.findViewById(R.id.comment);
        comment_layout = view.findViewById(R.id.comment_layout);
        comment_layout.setVisibility(View.GONE);

        person_id.setText(""+person_id_value);
        name.setText(name_value);
        email.setText(email_value);
        gender.setText(gender_value);
        status.setText(status_value);
        if(comment_value!=null&&!comment_value.equals("")){
            comment_layout.setVisibility(View.VISIBLE);
            comment.setText(comment_value);
        }
        return view;
    }
}

