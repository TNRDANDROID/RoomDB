package com.nic.newapkproject.Activity.Activity.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nic.newapkproject.Activity.Activity.Adapter.PersonRecyclerAdapter;
import com.nic.newapkproject.Activity.Activity.BootmNavigationBarActivity;
import com.nic.newapkproject.Activity.Activity.DataBase.DatabaseClient;
import com.nic.newapkproject.Activity.Activity.Fragment.Entity.PersonDetails;
import com.nic.newapkproject.Activity.Activity.Interface.ListitemClick;
import com.nic.newapkproject.R;

public class FirstFragment extends Fragment {

    RelativeLayout previous_page,current_page,next_page;
    RecyclerView person_recycler;
    PersonRecyclerAdapter personRecyclerAdapter;
    int page_count=1;
    int total_page;

    Fragment fragment_;

    public FirstFragment(){
        // require a empty public constructor

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        total_page = getArguments().getInt("total_page");
        previous_page = view.findViewById(R.id.previous_page);
        next_page = view.findViewById(R.id.next_page);
        person_recycler = view.findViewById(R.id.person_recycler);
        person_recycler.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL);
        person_recycler.addItemDecoration(itemDecoration);
        if(page_count==1){
            previous_page.setEnabled(false);
        }
        else {
            previous_page.setEnabled(true);
        }

        previous_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page_count--;
                previous_page.setBackgroundDrawable(getResources().getDrawable(R.drawable.page_bg_blue));
                next_page.setBackgroundDrawable(getResources().getDrawable(R.drawable.page_bg_white));
                if(page_count==1){
                    previous_page.setEnabled(false);
                }
                else {
                    previous_page.setEnabled(true);
                }
                getPersonDetails(page_count);

            }
        });
        next_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page_count++;
                previous_page.setBackgroundDrawable(getResources().getDrawable(R.drawable.page_bg_white));
                next_page.setBackgroundDrawable(getResources().getDrawable(R.drawable.page_bg_blue));
                if(total_page==page_count){
                    next_page.setEnabled(false);

                }
                else {
                    next_page.setEnabled(true);
                }
                if(page_count==1){
                    previous_page.setEnabled(false);
                }
                else {
                    previous_page.setEnabled(true);
                }
                getPersonDetails(page_count);

            }
        });
        getPersonDetails(page_count);

        // Inflate the layout for this fragment
        return view;
    }

    private void getPersonDetails(int page_count1) {
        class GetTasks extends AsyncTask<Void, Void, List<PersonDetails>> implements ListitemClick {

            @Override
            protected List<PersonDetails> doInBackground(Void... voids) {
                List<PersonDetails> personDetails = DatabaseClient
                        .getInstance(getContext())
                        .getAppDatabase()
                        .taskDao()
                        .getAll(page_count1);
                return personDetails;
            }

            @Override
            protected void onPostExecute(List<PersonDetails> personDetails) {
                super.onPostExecute(personDetails);
                personRecyclerAdapter = new PersonRecyclerAdapter(getActivity(),personDetails, this,fragment_);
                person_recycler.setAdapter(personRecyclerAdapter);

            }

            @Override
            public void itemClicked(int page_no, int id, String comment) {
                page_count=page_no;
                DatabaseClient.getInstance(getActivity()).getAppDatabase()
                        .taskDao()
                        .UpdateColumnById(comment,id,page_no);
                getPersonDetails(page_no);
            }

            @Override
            public void itemViewClicked(int person_id, String name, String email, String gender, String status, String comment) {
                //showDialog1(person_id,name,email,gender,status,comment);
               /* SecondFragment nextFrag= new SecondFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("person_id", person_id);
                bundle.putString("name",name);
                bundle.putString("email",email);
                bundle.putString("gender",gender);
                bundle.putString("status",status);
                bundle.putString("comment",comment);
                nextFrag.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .add(R.id.flFragment, nextFrag, "2")
                        .addToBackStack(null)
                        .commit();*/
                ((BootmNavigationBarActivity)getActivity()).gotoSecondFragment(person_id,name,email,gender,status,comment);
            }
        }

        GetTasks gt = new GetTasks();
        gt.execute();
    }





    protected void showDialog1(int person_id, String name, String email, String gender, String status, String comment){
        Bundle bundle1 = new Bundle();
        bundle1.putInt("person_id", person_id);
        bundle1.putString("name",name);
        bundle1.putString("email",email);
        bundle1.putString("gender",gender);
        bundle1.putString("status",status);
        bundle1.putString("comment",comment);
        DialogFragment dialog = new MyDialogFragment();
        dialog.setArguments(bundle1);
        dialog.show(getFragmentManager(), "dialog");
    }

}

