package com.cleanup.todoc;


import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;

import com.cleanup.todoc.database.TaskDao;

import com.cleanup.todoc.model.TaskEntity;
import com.cleanup.todoc.repository.TaskRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class TaskRepositoryTest {

    private TaskDao mTaskDao;
    private TaskRepository mTaskRepository;

    @Before
    public void setUp() {
        mTaskDao = Mockito.mock(TaskDao.class);
        mTaskRepository = new TaskRepository(mTaskDao);
    }

    @Test
    public void insertTask(){
        TaskEntity lvTaskEntity = Mockito.mock(TaskEntity.class);

        mTaskRepository.insert(lvTaskEntity);

        Mockito.verify(mTaskDao, Mockito.atLeastOnce()).insertTask(lvTaskEntity);


    }
    @Test
    public void updateTask(){
        TaskEntity lvTaskEntity = Mockito.mock(TaskEntity.class);

        mTaskRepository.update(lvTaskEntity);

        Mockito.verify(mTaskDao, Mockito.atLeastOnce()).updateTask(lvTaskEntity);
    }

    @Test
    public void deleteTask(){
        TaskEntity lvTaskEntity = Mockito.mock(TaskEntity.class);

        mTaskRepository.delete(lvTaskEntity);

        Mockito.verify(mTaskDao, Mockito.atLeastOnce()).deleteTask(lvTaskEntity);
    }

    @Test
    public void getAllTask(){


        mTaskRepository.getAllTask();

        Mockito.verify(mTaskDao, Mockito.atLeastOnce()).getTaskDataList();
    }
}
