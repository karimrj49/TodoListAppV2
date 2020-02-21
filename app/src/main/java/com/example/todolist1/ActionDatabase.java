package com.example.todolist1;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.todolist1.DAO.ActionDao;
import com.example.todolist1.DAO.DateDao;
import com.example.todolist1.DAO.ProjectDao;
import com.example.todolist1.DAO.StatusDao;
import com.example.todolist1.DAO.TagsDao;
import com.example.todolist1.Entities.Action;
import com.example.todolist1.Entities.Date;
import com.example.todolist1.Entities.Projects;
import com.example.todolist1.Entities.Status;
import com.example.todolist1.Entities.Tags;

import java.util.concurrent.Executors;

@Database(entities = {Action.class,Status.class,Date.class, Tags.class, Projects.class}, version = 6)
@TypeConverters({Converters.class})
public abstract class ActionDatabase extends RoomDatabase {

    private static ActionDatabase instance;
    public abstract ActionDao actionDao();
    public abstract StatusDao statusDao();
    public abstract DateDao DatDao();
    public abstract TagsDao TagsDao();
    public abstract ProjectDao projectDao();
    public static synchronized ActionDatabase getInstance(Context context){

        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),ActionDatabase.class,"action_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(sRoomDatabaseCalback)
                    .build();
        }

        return instance;
    }


        private static RoomDatabase.Callback sRoomDatabaseCalback = new RoomDatabase.Callback() {

            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
                Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                    @Override
                    public void run() {

                            StatusDao dao = instance.statusDao();
                            //dao.deleteAll();
                            Status status1 = new Status("To-Do");
                            dao.inserts(status1);
                            Status status2 = new Status("Done");
                            dao.inserts(status2);

                    }
                });
            }

        };
    //.addCallback(sRoomDatabaseCalback)
    }


