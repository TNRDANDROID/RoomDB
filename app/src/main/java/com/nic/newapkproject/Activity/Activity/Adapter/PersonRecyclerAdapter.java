package com.nic.newapkproject.Activity.Activity.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.nic.newapkproject.Activity.Activity.BootmNavigationBarActivity;
import com.nic.newapkproject.Activity.Activity.DataBase.DatabaseClient;
import com.nic.newapkproject.Activity.Activity.Fragment.Entity.PersonDetails;
import com.nic.newapkproject.Activity.Activity.Fragment.FirstFragment;
import com.nic.newapkproject.Activity.Activity.Fragment.SecondFragment;
import com.nic.newapkproject.Activity.Activity.Fragment.ThirdFragment;
import com.nic.newapkproject.Activity.Activity.Interface.ListitemClick;
import com.nic.newapkproject.R;

import java.util.List;

public class PersonRecyclerAdapter extends RecyclerView.Adapter<PersonRecyclerAdapter.MyViewHolder> {

    List<PersonDetails> personDetailsList;
    Context context;
    ListitemClick listitemClick;
    SecondFragment secondFragment;
    Fragment fragment;

    public PersonRecyclerAdapter(Context context, List<PersonDetails> personDetailsList, ListitemClick listitemClick,Fragment fragment) {
        this.personDetailsList = personDetailsList;
        this.context=context;
        this.listitemClick=listitemClick;
        this.fragment=fragment;
    }

    @NonNull
    @Override
    public PersonRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_list_adapter_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonRecyclerAdapter.MyViewHolder holder, int position) {

        holder.id.setText(""+personDetailsList.get(position).getPerssion_id());
        holder.name.setText(personDetailsList.get(position).getName());
        holder.email.setText(personDetailsList.get(position).getEmail());
        holder.gender.setText(personDetailsList.get(position).getGender());
        holder.status.setText(personDetailsList.get(position).getStatus());

        if(personDetailsList.get(position).getComments()!=null&&!personDetailsList.get(position).getComments().equals("")){
            holder.comments.setText("Comment : "+personDetailsList.get(position).getComments());
        }
        else {
            holder.comments.setText("");
        }

        if(personDetailsList.get(position).getStatus().equals("active")){
            holder.status.setTextColor(Color.GREEN);
        }
        else {
            holder.status.setTextColor(Color.RED);
        }
        if(personDetailsList.get(position).getGender().equals("male")){
            holder.profile.setImageResource(R.drawable.male_profile_icon);
        }
        else {
            holder.profile.setImageResource(R.drawable.female_profile_icon);
        }

        holder.send_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!holder.comment_edit.getText().toString().equals("")) {
                    listitemClick.itemClicked(personDetailsList.get(position).getPage(),personDetailsList.get(position).getPerssion_id(),holder.comment_edit.getText().toString());
                    //updateDetails(position, holder.comment_edit.getText().toString());

                }

            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listitemClick.itemViewClicked(personDetailsList.get(position).getPerssion_id(),personDetailsList.get(position).getName(),personDetailsList.get(position).getEmail()
                ,personDetailsList.get(position).getGender(),personDetailsList.get(position).getStatus(),personDetailsList.get(position).getComments());

            }
        });

    }

    @Override
    public int getItemCount() {
        return personDetailsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView profile,send_icon;
        TextView id,name,email,gender,status,comments;
        EditText comment_edit;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            profile = itemView.findViewById(R.id.profile_icon);
            id = itemView.findViewById(R.id.person_id);
            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);
            gender = itemView.findViewById(R.id.gender);
            status = itemView.findViewById(R.id.status);
            comments = itemView.findViewById(R.id.comment);
            send_icon = itemView.findViewById(R.id.send_icon);
            comment_edit = itemView.findViewById(R.id.comment_edit);
        }
    }

    public void updateDetails(int pos,String comments){
        int id=personDetailsList.get(pos).getPerssion_id()
                ,page_no=personDetailsList.get(pos).getPage();
        DatabaseClient.getInstance(context).getAppDatabase()
                .taskDao()
                .UpdateColumnById(comments,id,page_no);
        notifyDataSetChanged();
    }
}
