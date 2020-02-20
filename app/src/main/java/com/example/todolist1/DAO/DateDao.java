package com.example.todolist1.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.todolist1.Entities.Action;
import com.example.todolist1.Entities.Date;

import java.util.List;

@Dao
public interface DateDao {

    @Insert
    void insert(Date date);

    @Delete
    void delete(Date date);
    @Update
    void update(Date date);

    @Query("SELECT * FROM date_table")
    LiveData<List<Date>> getAllDate();




}
