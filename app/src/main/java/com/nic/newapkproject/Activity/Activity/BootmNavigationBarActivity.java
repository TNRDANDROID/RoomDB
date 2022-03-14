package com.nic.newapkproject.Activity.Activity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import com.nic.newapkproject.Activity.Activity.Fragment.FirstFragment;
import com.nic.newapkproject.Activity.Activity.Fragment.SecondFragment;
import com.nic.newapkproject.Activity.Activity.Fragment.ThirdFragment;

import com.nic.newapkproject.R;


public class BootmNavigationBarActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{
    BottomNavigationView bottomNavigationView;


    String tag="";
    int total_page;


    Fragment fragment1;
    Fragment fragment2;
    Fragment fragment3;
    FragmentManager fm = getSupportFragmentManager();
    Fragment active;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bottom_bar_fragment);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        fragment1 = new FirstFragment();
        fragment2 = new SecondFragment();
        fragment3 = new ThirdFragment();
        active = fragment1;
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.personDetails);
        tag="1";

        total_page = getIntent().getIntExtra("total_page",0);


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment;
        FragmentManager fragmentManager = getSupportFragmentManager();
        switch (item.getItemId()) {
            case R.id.personDetails:

            Bundle bundle = new Bundle();
            bundle.putInt("total_page", total_page);

                fragment1.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, fragment1).addToBackStack("1").commit();
                tag="1";
                return true;


            case R.id.home:
                if(tag.equals("4")){

                }
                Bundle bundle1 = new Bundle();
                bundle1.putInt("person_id", 0);
                bundle1.putString("name","");
                bundle1.putString("email","");
                bundle1.putString("gender","");
                bundle1.putString("status","");
                bundle1.putString("comment","");
                fragment2.setArguments(bundle1);
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, fragment2).addToBackStack("2").commit();
                tag="2";
                return true;

            case R.id.settings:

                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, fragment3).addToBackStack("3").commit();
                tag="3";
                return true;

        }

        return false;
    }

    @Override
    public void onBackPressed() {


        if(getSupportFragmentManager().getBackStackEntryCount()==1){
            finish();
        }
      /*  else if(getSupportFragmentManager().getBackStackEntryCount()==1){
            bottomNavigationView.setSelectedItemId(R.id.home);
            getSupportFragmentManager().popBackStack();
        }
        else if (getSupportFragmentManager().getBackStackEntryCount()==2){
            bottomNavigationView.setSelectedItemId(R.id.settings);
            getSupportFragmentManager().popBackStack();
        }*/
        else {
            getSupportFragmentManager().popBackStack();
        //bottomNavigationView.setSelectedItemId(bottomNavigationView.getSelectedItemId());
        }

    }



    public void gotoSecondFragment(int person_id, String name, String email, String gender, String status, String comment){
        Bundle bundle = new Bundle();
        bundle.putInt("person_id", person_id);
        bundle.putString("name",name);
        bundle.putString("email",email);
        bundle.putString("gender",gender);
        bundle.putString("status",status);
        bundle.putString("comment",comment);

        bottomNavigationView.setSelectedItemId(R.id.home);
        fragment2.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, fragment2).addToBackStack("2").commit();
        tag ="4";


    }





}
