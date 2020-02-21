package com.example.todolist1.Repository;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;

import com.example.todolist1.ActionDatabase;
import com.example.todolist1.DAO.DateDao;
import com.example.todolist1.Entities.Action;
import com.example.todolist1.Entities.Date;

import java.util.List;

public class DateRepository {

    private DateDao DateDao;
    private LiveData<List<Date>>allDate;
    private LiveData<List<Action>>allDateS;
    //int p;
    public DateRepository(Application application){

        ActionDatabase database = ActionDatabase.getInstance(application);
        DateDao = database.DatDao();
        allDate = DateDao.getAllDate();
        //allActionS = actionDao.getActionsS(p);
    }
    public void update(Date date){

        new UpdateAsyncTask(DateDao).execute(date);
    }


    public void insert(Date date){

        new InsertAsyncTask(DateDao).execute(date);
    }

    public void delete (Date date){

        new DeleteAsyncTask(DateDao).execute(date);
    }

    public LiveData<List<Date>> getAllDate() {

        return allDate;
    }
//    public LiveData<List<Date>>getAllDateS(int p){
//        return DateDao.getActionsS(p);
//    }
    private static class InsertAsyncTask extends AsyncTask<Date , Void , Void >{

        private DateDao Dao;
        private InsertAsyncTask (DateDao Dao){
            this.Dao = Dao;
        }
        @Override
        protected Void doInBackground(Date... dao) {
            Dao.insert(dao[0]);
            return null;
        }
    }
    private static class UpdateAsyncTask extends AsyncTask<Date , Void , Void >{

        private DateDao Dao;
        private UpdateAsyncTask (DateDao Dao){
            this.Dao = Dao;
        }
        @Override
        protected Void doInBackground(Date... dao) {
            Dao.update(dao[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Date , Void , Void >{

        private DateDao Dao;
        private DeleteAsyncTask (DateDao Dao){
            this.Dao = Dao;
        }
        @Override
        protected Void doInBackground(Date... dao) {
            Dao.delete(dao[0]);
            return null;
        }
    }
}
