package com.nic.newapkproject.Activity.Activity.Fragment;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.nic.newapkproject.R;

public class MyDialogFragment extends DialogFragment {
    TextView person_id_value;
    TextView name_value;
    TextView email_value;
    TextView gender_value;
    TextView _value;
    TextView comment_value;
    LinearLayout comment_layout;

    int person_id=0;
    String name="";
    String email="";
    String gender="";
    String status="";
    String comment="";
    public MyDialogFragment()
    {
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog, new LinearLayout(getActivity()), false);

              Dialog builder = new Dialog(getActivity());
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        builder.getWindow();
        builder.setContentView(view);

        Bundle bundle = getArguments();
        if(bundle!=null){
            person_id = getArguments().getInt("person_id",0);
            name = getArguments().getString("name");
            email = getArguments().getString("email");
            gender = getArguments().getString("gender");
            status = getArguments().getString("status");
            comment = getArguments().getString("comment");
        }
        person_id_value = view.findViewById(R.id.person_id);
        name_value = view.findViewById(R.id.name);
        email_value = view.findViewById(R.id.email);
        gender_value = view.findViewById(R.id.gender);
        _value = view.findViewById(R.id.status);
        comment_value = view.findViewById(R.id.comment);
        comment_layout = view.findViewById(R.id.comment_layout);
        comment_layout.setVisibility(View.GONE);
        person_id_value.setText(""+person_id);
        name_value.setText(name);
        email_value.setText(email);
        gender_value.setText(gender);
        _value.setText(status);
        if(!comment_value.equals("")){
            comment_layout.setVisibility(View.VISIBLE);
            comment_value.setText(comment);
        }


        return builder;
    }
}
