package com.example.todolist1.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.todolist1.Converters;

@Entity(tableName = "date_table")
@TypeConverters(Converters.class)
public class Date {


    @PrimaryKey(autoGenerate = true)
    private int id ;
    private java.util.Date createdate;
    private java.util.Date DueDate;

    public java.util.Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(java.util.Date createdate) {
        this.createdate = createdate;
    }

    public java.util.Date getDueDate() {
        return DueDate;
    }

    public void setDueDate(java.util.Date dueDate) {
        DueDate = dueDate;
    }

    public java.util.Date getDeadlinedate() {
        return deadlinedate;
    }

    public void setDeadlinedate(java.util.Date deadlinedate) {
        this.deadlinedate = deadlinedate;
    }

    private java.util.Date deadlinedate;
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
//


}
