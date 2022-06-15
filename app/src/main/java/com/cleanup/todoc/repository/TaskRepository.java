package com.cleanup.todoc.repository;



import androidx.lifecycle.LiveData;

import com.cleanup.todoc.database.TaskDao;
import com.cleanup.todoc.model.TaskEntity;

import java.util.List;

public class TaskRepository {

    private TaskDao mTaskDao;


    public TaskRepository(TaskDao pTaskDao ){

        mTaskDao = pTaskDao;


    }
    public void insert (TaskEntity pTaskEntity){
       mTaskDao.insertTask(pTaskEntity);
    }
    public void update (TaskEntity pTaskEntity){
        mTaskDao.updateTask(pTaskEntity);
    }
    public void delete (TaskEntity pTaskEntity){
       mTaskDao.deleteTask(pTaskEntity);
    }


    public LiveData<List<TaskEntity>> getAllTask() {
        return mTaskDao.getTaskDataList();
    }


}
