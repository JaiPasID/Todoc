package com.cleanup.todoc.database;



import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;

import androidx.room.Query;
import androidx.room.Update;

import com.cleanup.todoc.model.ProjectEntity;

import java.util.List;

@Dao
public interface ProjectDao {

    @Query ("SELECT * FROM project_data")
    LiveData<List<ProjectEntity>> getProjectList();

    @Query("DELETE FROM project_data")
    void deleteAllProject();

    @Insert
    void insertProject (ProjectEntity pProjectEntity);

    @Update
    void updateProject (ProjectEntity pProjectEntity);

    @Delete
    void deleteProject (ProjectEntity pProjectEntity);


    @Query ("SELECT * FROM project_data WHERE id = :id")
    ProjectEntity getProjectById(long id);
}
