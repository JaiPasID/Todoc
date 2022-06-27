package com.cleanup.todoc;


import static org.junit.Assert.assertSame;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;


import com.cleanup.todoc.model.TaskEntity;
import com.cleanup.todoc.repository.ProjectRepository;
import com.cleanup.todoc.repository.TaskRepository;
import com.cleanup.todoc.ui.MainActivity;
import com.cleanup.todoc.viewmodel.TaskViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class TaskViewModelTest {

    private ProjectRepository mProjectRepository;
    private TaskRepository mTaskRepository;
    private TaskViewModel mTaskViewModel;

    private static final TaskEntity TASK1 = new TaskEntity(1, "aaa", 123);
    private static final TaskEntity TASK3 = new TaskEntity(3, "ccc", 125);
    private static final TaskEntity TASK2 = new TaskEntity(2, "bbb", 124);

    List<TaskEntity> mList = new ArrayList<>();

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp() {
        mProjectRepository = Mockito.mock(ProjectRepository.class);
        mTaskRepository = Mockito.mock(TaskRepository.class);
        mTaskViewModel = new TaskViewModel(mProjectRepository, mTaskRepository);
    }

    @Test
    public void insertTask() throws InterruptedException {
        TaskEntity lvTaskEntity = Mockito.mock(TaskEntity.class);

        mTaskViewModel.insertTask(lvTaskEntity);
        Thread.sleep(100);
        Mockito.verify(mTaskRepository, Mockito.atLeastOnce()).insert(lvTaskEntity);
    }

    @Test
    public void updateTask() throws InterruptedException {
        TaskEntity lvTaskEntity = Mockito.mock(TaskEntity.class);

        mTaskViewModel.updateTask(lvTaskEntity);
        Thread.sleep(100);
        Mockito.verify(mTaskRepository, Mockito.atLeastOnce()).update(lvTaskEntity);
    }

    @Test
    public void deleteTask() throws InterruptedException {
        TaskEntity lvTaskEntity = Mockito.mock(TaskEntity.class);

        mTaskViewModel.deleteTask(lvTaskEntity);
        Thread.sleep(100);
        Mockito.verify(mTaskRepository, Mockito.atLeastOnce()).delete(lvTaskEntity);
    }

    @Test
    public void getListLiveDataProject(){

        mTaskViewModel.getListLiveDataProject();

        Mockito.verify(mProjectRepository, Mockito.atLeastOnce()).getAllProject();
    }
    @Test
    public void getListLiveDataTask(){


        mTaskViewModel.getListLiveDataTask();

        Mockito.verify(mTaskRepository, Mockito.atLeastOnce()).getAllTask();
    }

    @Test
    public void alphabetical(){

        mList.add(TASK1);
        mList.add(TASK3);
        mList.add(TASK2);

        List<TaskEntity> sortedList = mTaskViewModel.getSortedList(MainActivity.SortMethod.ALPHABETICAL, mList);


        assertSame(sortedList.get(0).getName(), TASK1.getName());
        assertSame(sortedList.get(1).getName(), TASK2.getName());
        assertSame(sortedList.get(2).getName(), TASK3.getName());

    }

}
