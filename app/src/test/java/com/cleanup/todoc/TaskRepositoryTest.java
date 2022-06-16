package com.cleanup.todoc;

import com.cleanup.todoc.database.ProjectDao;
import com.cleanup.todoc.database.TaskDao;
import com.cleanup.todoc.repository.ProjectRepository;
import com.cleanup.todoc.repository.TaskRepository;

import org.junit.Before;
import org.mockito.Mockito;

public class TaskRepositoryTest {

    private TaskDao mTaskDao;
    private TaskRepository mTaskRepository;

    @Before
    public void setUp() {
        mTaskDao = Mockito.mock(TaskDao.class);
        mTaskRepository = new TaskRepository(mTaskDao);
    }
}
