package com.example.todolist1.Entities;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "project_table")
public class Projects {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String Titlet;
    public Projects() {

    }

    public Projects(String title) {
        this.Titlet = title;
    }

    public String getTitlet() {
        return Titlet;
    }

    public void setTitlet(String titlet) {
        Titlet = titlet;
    }

    private String Description_project;
    private String id_task;

    @Ignore
    Action s;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getDescription_project() {
        return Description_project;
    }

    public void setDescription_project(String description_project) {
        Description_project = description_project;
    }

    public String getId_task() {
        return id_task;
    }

    public void setId_task(String id_task) {
        this.id_task = id_task;
    }


}
