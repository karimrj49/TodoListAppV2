package com.example.todolist1.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.todolist1.Entities.Action;
import com.example.todolist1.Entities.Date;
import com.example.todolist1.Repository.ActionRepository;
import com.example.todolist1.Repository.DateRepository;

import java.util.List;

public class DateViewModel extends AndroidViewModel {

    private com.example.todolist1.Repository.DateRepository DateRepository;
    private LiveData<List<Date>> allDate;

    public DateViewModel(@NonNull Application application, com.example.todolist1.Repository.DateRepository dateRepository, LiveData<List<Date>> allDate) {
        super(application);
        dateRepository = new DateRepository(application);
        allDate = dateRepository.getAllDate();
    }

    private LiveData<List<Date>> allDateS;


    public void update(Date date) {
        DateRepository.update(date);
    }

    public void insert(Date date) {
        DateRepository.insert(date);
    }

    public void delete(Date date) {
        DateRepository.delete(date);
    }

    public LiveData<List<Date>> getAllDate() {
        return allDate;
    }

}
//    public LiveData<List<Action>> getAllDateS(int p){return  DateRepository.(p);}
//}
