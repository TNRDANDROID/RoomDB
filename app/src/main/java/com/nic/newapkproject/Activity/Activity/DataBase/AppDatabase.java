package com.nic.newapkproject.Activity.Activity.DataBase;

import android.app.Person;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.nic.newapkproject.Activity.Activity.Fragment.Entity.PersonDetails;
import com.nic.newapkproject.Activity.Activity.Fragment.Interface.PersonDao;

@Database(entities = {PersonDetails.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PersonDao taskDao();
}
