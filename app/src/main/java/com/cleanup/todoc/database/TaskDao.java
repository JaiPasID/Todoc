package com.cleanup.todoc.database;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.cleanup.todoc.model.TaskEntity;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM TaskEntity")
    LiveData<List<TaskEntity>> getTaskDataList();

    @Query("DELETE FROM TaskEntity")
    void deleteAllTask();


    @Insert
    void insertTask (TaskEntity pTaskEntity);

    @Update
    void updateTask (TaskEntity pTaskEntity);

    @Delete
    void deleteTask (TaskEntity pTaskEntity);


}
