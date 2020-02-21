package com.example.todolist1.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.todolist1.Entities.Tags;

import java.util.List;

@Dao
public interface TagsDao  {



    @Insert
    void inserts(Tags tag);

    @Delete
    void delete(Tags tag);
    @Update
    void update(Tags tag);

    @Query("SELECT * FROM Tags_table")
    LiveData<List<Tags>> getAllTags();



}
