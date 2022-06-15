package com.cleanup.todoc.database;

import android.content.Context;


import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.cleanup.todoc.model.ProjectEntity;
import com.cleanup.todoc.model.TaskEntity;

import java.util.concurrent.Executors;


@Database(entities = {ProjectEntity.class, TaskEntity.class}, exportSchema = false,version = 1)
public abstract class DatabaseRoom extends RoomDatabase {

    private static final String DB_NAME = "CleanUp_db";
    private static DatabaseRoom instance;


    public static synchronized DatabaseRoom getInstance(Context pContext){
        if (instance == null){
            instance= Room.databaseBuilder(pContext.getApplicationContext(), DatabaseRoom.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        return instance;
    }
    public abstract ProjectDao mProjectDao();
    public abstract TaskDao mTaskDao();

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback(){
        /**
         * Called when the database is created for the first time. This is called after all the
         * tables are created.
         *
         * @param db The database.
         */
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            Executors.newSingleThreadExecutor().execute(() -> {
                instance.mProjectDao().insertProject(new ProjectEntity("Projet Lucidia", 0xFFB4CDBA));
                instance.mProjectDao().insertProject(new ProjectEntity("Projet Tartampion", 0xFFEADAD1));
                instance.mProjectDao().insertProject(new ProjectEntity("Projet Circus", 0xFFA3CED2));

            });

        }
    };



}
