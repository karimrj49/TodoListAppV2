package com.example.todolist1.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.todolist1.Entities.Projects;

import java.util.List;
@Dao
public interface ProjectDao {
    @Insert
    void insert(Projects project);

    @Delete
    void delete(Projects project);
    @Update
    void update(Projects project);

    @Query("SELECT * FROM project_table")
    LiveData<List<Projects>> getAllProject();
}
