package com.nic.newapkproject.Activity.Activity.Fragment.Interface;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.nic.newapkproject.Activity.Activity.Fragment.Entity.PersonDetails;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface PersonDao {

    @Query("SELECT * FROM PersonDetails where page = (:page)")
    List<PersonDetails> getAll(int page);

    @Insert
    void insert(PersonDetails personDetails);

    @Delete
    void delete(PersonDetails personDetails);

    @Update
    void update(PersonDetails personDetails);

    @Query("UPDATE PersonDetails SET comments =:text WHERE perssion_id=:id and page=:page_no")
    void UpdateColumnById (String text, int id,int page_no);
}
