package com.cleanup.todoc;


import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.cleanup.todoc.model.TaskEntity;
import com.cleanup.todoc.repository.ProjectRepository;
import com.cleanup.todoc.repository.TaskRepository;
import com.cleanup.todoc.viewmodel.TaskViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.mockito.Mockito;

public class TaskViewModelTest {

    private ProjectRepository mProjectRepository;
    private TaskRepository mTaskRepository;
    private TaskViewModel mTaskViewModel;

    private static final TaskEntity TASK1 = new TaskEntity(1, "aaa", 123);
    private static final TaskEntity TASK2 = new TaskEntity(2, "bbb", 124);
    private static final TaskEntity TASK3 = new TaskEntity(3, "ccc", 125);

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp() {
        mProjectRepository = Mockito.mock(ProjectRepository.class);
        mTaskRepository = Mockito.mock(TaskRepository.class);
        mTaskViewModel = new TaskViewModel(mProjectRepository, mTaskRepository);
    }
}
