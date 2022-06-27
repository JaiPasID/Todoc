package com.cleanup.todoc;

import static com.cleanup.todoc.TestLiveData.getOrAwaitValue;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import android.content.Context;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.cleanup.todoc.database.DatabaseRoom;
import com.cleanup.todoc.database.TaskDao;
import com.cleanup.todoc.model.ProjectEntity;
import com.cleanup.todoc.model.TaskEntity;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.List;

public class TestTaskDAO {

    private DatabaseRoom mDatabaseRoom;
    private TaskDao mTaskDao;

    private static final ProjectEntity TEST_PROJECT = new ProjectEntity("Test Project A", 0xFFB4CDBF);

    private static final TaskEntity TEST_TASK = new TaskEntity(1, "Test Task A", new Date().getTime());
    private static final TaskEntity TEST_TASK2 = new TaskEntity(1, "Test Task B", new Date().getTime());

    @Rule
    public InstantTaskExecutorRule mInstantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        mDatabaseRoom = Room.inMemoryDatabaseBuilder(context, DatabaseRoom.class).allowMainThreadQueries().build();
        mTaskDao = mDatabaseRoom.mTaskDao();

        mDatabaseRoom.mProjectDao().insertProject(TEST_PROJECT);
    }

    @After
    public void closeDb() throws IOException {
        mDatabaseRoom.close();
    }

    @Test
    public void insertTask() throws InterruptedException {
        List<TaskEntity> taskListBefore = getOrAwaitValue(mTaskDao.getTaskDataList());
        assertEquals(0, taskListBefore.size());

        mTaskDao.insertTask(TEST_TASK);
        List<TaskEntity> taskListAfter = getOrAwaitValue(mTaskDao.getTaskDataList());
        assertEquals(1, taskListAfter.size());

        TaskEntity taskFromTheList = getOrAwaitValue(mTaskDao.getTaskDataList()).get(0);
        assertThat(taskFromTheList.getName(), equalTo(TEST_TASK.getName()));
    }

    @Test
    public void updateTask() throws InterruptedException {
        mTaskDao.insertTask(TEST_TASK);

        TaskEntity task = getOrAwaitValue(mTaskDao.getTaskDataList()).get(0);
        assertEquals(TEST_TASK.getName(), task.getName());

        task.setName("Updated Task");
        mTaskDao.updateTask(task);

        TaskEntity taskAfterModification = getOrAwaitValue(mTaskDao.getTaskDataList()).get(0);
        assertEquals("Updated Task", taskAfterModification.getName());
    }


    @Test
    public void deleteAllTasks() throws InterruptedException {
        mTaskDao.insertTask(TEST_TASK);
        mTaskDao.insertTask(TEST_TASK2);
        List<TaskEntity> taskList = getOrAwaitValue(mTaskDao.getTaskDataList());

        assertThat(TEST_TASK.getName(), equalTo(taskList.get(0).getName()));
        assertThat(TEST_TASK2.getName(), equalTo(taskList.get(1).getName()));
        assertEquals(2, taskList.size());

        mTaskDao.deleteAllTask();
        List<TaskEntity> taskListAfter = getOrAwaitValue(mTaskDao.getTaskDataList());
        assertEquals(0, taskListAfter.size());
    }

    @Test
    public void getAllTasks() throws InterruptedException {
        List<TaskEntity> taskList = getOrAwaitValue(mTaskDao.getTaskDataList());
        assertTrue(taskList.isEmpty());

        mTaskDao.insertTask(TEST_TASK);
        mTaskDao.insertTask(TEST_TASK2);

        List<TaskEntity> taskListAfter = getOrAwaitValue(mTaskDao.getTaskDataList());
        Assert.assertFalse(taskListAfter.isEmpty());
        assertEquals(2, taskListAfter.size());
    }
}
