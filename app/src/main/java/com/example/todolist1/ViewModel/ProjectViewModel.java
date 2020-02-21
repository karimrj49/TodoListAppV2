package com.example.todolist1.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.todolist1.Entities.Projects;
import com.example.todolist1.Repository.ProjecRepository;

import java.util.List;

public class ProjectViewModel extends AndroidViewModel {
    private ProjecRepository projecRepository;
    private LiveData<List<Projects>> allProject;

    public ProjectViewModel(@NonNull Application application) {
        super(application);
        projecRepository= new ProjecRepository(application);
        allProject = projecRepository.getAllproject();
        //allActionS = actionRepository.getAllActionS(p);
    }

    public void update(Projects projects){ projecRepository.update(projects);}

    public void insert(Projects projects){ projecRepository.insert(projects); }

    public void delete(Projects projects){ projecRepository.delete(projects); }

    public LiveData<List<Projects>> getAllProject() {
        return allProject;
    }

}
