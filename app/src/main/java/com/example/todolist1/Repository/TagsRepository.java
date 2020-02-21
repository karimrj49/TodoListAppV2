package com.example.todolist1.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.todolist1.ActionDatabase;

import com.example.todolist1.DAO.TagsDao;
import com.example.todolist1.Entities.Tags;

import java.util.List;

public class TagsRepository {
    private static TagsDao TagsDao;
    private LiveData<List<Tags>> allTags;
    private LiveData<List<String>> allTagss;

    public TagsRepository(Application application) {
        ActionDatabase database = ActionDatabase.getInstance(application);
        TagsDao = database.TagsDao();
        allTags = TagsDao.getAllTags();
    }
    public void update(Tags tags){

        new TagsRepository.UpdateAsyncTask(TagsDao).execute(tags);
    }


    public void insert(Tags tags){

        new TagsRepository.InsertAsyncTask(TagsDao).execute(tags);
    }

    public void delete (Tags tags){

        new TagsRepository.DeleteAsyncTask(TagsDao).execute(tags);
    }

    public LiveData<List<Tags>> getalltags() {

        return allTags;
    }
    //    public LiveData<List<Date>>getAllDateS(int p){
//        return DateDao.getActionsS(p);
//    }
    private static class InsertAsyncTask extends AsyncTask<Tags , Void , Void > {

        private TagsDao Dao;
        private InsertAsyncTask (TagsDao Dao){
            this.Dao = Dao;
        }
        @Override
        protected Void doInBackground(Tags... tags) {
            Dao.inserts(tags[0]);
            return null;
        }
    }
    private static class UpdateAsyncTask extends AsyncTask<Tags , Void , Void >{

        private TagsDao Dao;
        private UpdateAsyncTask (TagsDao Dao){
            this.Dao = Dao;
        }
        @Override
        protected Void doInBackground(Tags... tags) {
            Dao.update(tags[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<Tags , Void , Void >{

        private TagsDao Dao;
        private DeleteAsyncTask (TagsDao Dao){
            this.Dao = Dao;
        }
        @Override
        protected Void doInBackground(Tags... dao) {
            Dao.delete(dao[0]);
            return null;
        }
    }
}
