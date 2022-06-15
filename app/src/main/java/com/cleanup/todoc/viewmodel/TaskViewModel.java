package com.cleanup.todoc.viewmodel;


import androidx.lifecycle.LiveData;

import androidx.lifecycle.ViewModel;

import com.cleanup.todoc.model.ProjectEntity;
import com.cleanup.todoc.model.TaskEntity;
import com.cleanup.todoc.repository.ProjectRepository;
import com.cleanup.todoc.repository.TaskRepository;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TaskViewModel extends ViewModel {


    private ProjectRepository mProjectRepository;
    private TaskRepository mTaskRepository;
    private Executor mExecutor;



    public TaskViewModel(ProjectRepository pProjectRepository, TaskRepository pTaskRepository) {

        mProjectRepository =  pProjectRepository;
        mTaskRepository =  pTaskRepository;
        mExecutor = Executors.newFixedThreadPool(2);

    }
   /**
    public void insertProject (ProjectEntity pProjectEntity){
        mProjectRepository.insert(pProjectEntity);
    }

    public void updateProject (ProjectEntity pProjectEntity){
        mProjectRepository.update(pProjectEntity);
    }

    public void deleteProject (ProjectEntity pProjectEntity){
        mProjectRepository.delete(pProjectEntity);
    }
    */

    public void insertTask (TaskEntity pTaskEntity){
       // mTaskRepository.insert(pTaskEntity);
        mExecutor.execute(()->mTaskRepository.insert(pTaskEntity));
    }

    public void updateTask (TaskEntity pTaskEntity){
       // mTaskRepository.update(pTaskEntity);
        mExecutor.execute(()->mTaskRepository.update(pTaskEntity));
    }

    public void deleteTask (TaskEntity pTaskEntity){
        //mTaskRepository.delete(pTaskEntity);
        mExecutor.execute(()->mTaskRepository.delete(pTaskEntity));
    }

    public LiveData<List<ProjectEntity>> getListLiveDataProject(){
        return mProjectRepository.getAllProject();
    }

    public LiveData<List<TaskEntity>> getListLiveDataTask(){
        return mTaskRepository.getAllTask();
    }
}
