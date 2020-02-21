package com.example.todolist1.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.todolist1.ActionDatabase;
import com.example.todolist1.DAO.ActionDao;
import com.example.todolist1.DAO.ProjectDao;
import com.example.todolist1.Entities.Action;
import com.example.todolist1.Entities.Projects;

import java.util.List;

public class ProjecRepository {

    private static ProjectDao projectDao;
    private LiveData<List<Projects>> allproject;
    public ProjecRepository (Application application){
        ActionDatabase database = ActionDatabase.getInstance(application);
        projectDao = database.projectDao();
        allproject = projectDao.getAllProject();
    }
    public void update(Projects projects){

        new ProjecRepository.UpdateAsyncTask(projectDao).execute(projects);
    }


    public void insert(Projects projects){

        new ProjecRepository.InsertAsyncTask(projectDao).execute(projects);
    }

    public void delete (Projects projects){

        new ProjecRepository.DeleteAsyncTask(projectDao).execute(projects);
    }

    public LiveData<List<Projects>> getAllproject() {

        return allproject;
    }
    private static class InsertAsyncTask extends AsyncTask<Projects , Void , Void > {

        private ProjectDao ProjectDao;
        private InsertAsyncTask (ProjectDao projectDao){
            this.ProjectDao = projectDao;
        }
        @Override
        protected Void doInBackground(Projects... project) {
            ProjectDao.insert(project[0]);
            return null;
        }
    }
    private static class UpdateAsyncTask extends AsyncTask<Projects , Void , Void > {

        private ProjectDao ProjectDao;
        private UpdateAsyncTask (ProjectDao projectDao){
            this.ProjectDao = projectDao;
        }
        @Override
        protected Void doInBackground(Projects... project) {
            ProjectDao.update(project[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Projects , Void , Void > {

        private ProjectDao ProjectDao;
        private DeleteAsyncTask (ProjectDao projectDao){
            this.ProjectDao = projectDao;
        }
        @Override
        protected Void doInBackground(Projects... project) {
            ProjectDao.delete(project[0]);
            return null;
        }
    }
}
