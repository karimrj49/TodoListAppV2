package com.example.todolist1.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "date_table")
public class Date {


    @PrimaryKey(autoGenerate = true)
    private int id ;
    private Date createdate;

    public Date(Date createdate, Date duedate, Date deadline, int id_task, int id_project) {
        this.createdate = createdate;
        this.duedate = duedate;
        this.deadline = deadline;
        this.id_task = id_task;
        this.id_project = id_project;
    }

    private Date duedate;
    private Date deadline;

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getDuedate() {
        return duedate;
    }

    public void setDuedate(Date duedate) {
        this.duedate = duedate;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public int getId_task() {
        return id_task;
    }

    public void setId_task(int id_task) {
        this.id_task = id_task;
    }

    public int getId_project() {
        return id_project;
    }

    public void setId_project(int id_project) {
        this.id_project = id_project;
    }

    private int id_task;
    private int id_project;



}
