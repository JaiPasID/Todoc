package com.cleanup.todoc.repository;



import androidx.lifecycle.LiveData;


import com.cleanup.todoc.database.ProjectDao;

import com.cleanup.todoc.model.ProjectEntity;

import java.util.List;

public class ProjectRepository {
    private ProjectDao mProjectDao;



    public ProjectRepository(ProjectDao pProjectDao){


        mProjectDao = pProjectDao;


    }

    public void insert (ProjectEntity pProjectEntity){
        mProjectDao.insertProject(pProjectEntity);
    }
    public void update (ProjectEntity pProjectEntity){
        mProjectDao.updateProject(pProjectEntity);
    }
    public void delete (ProjectEntity pProjectEntity){
        mProjectDao.deleteProject(pProjectEntity);
    }


    public LiveData<List<ProjectEntity>> getAllProject(){
        return mProjectDao.getProjectList();
    }




}
