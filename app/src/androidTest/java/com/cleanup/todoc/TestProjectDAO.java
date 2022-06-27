package com.cleanup.todoc;

import static com.cleanup.todoc.TestLiveData.getOrAwaitValue;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import android.content.Context;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;

import com.cleanup.todoc.database.DatabaseRoom;
import com.cleanup.todoc.database.ProjectDao;
import com.cleanup.todoc.model.ProjectEntity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

public class TestProjectDAO {

    private DatabaseRoom mDatabaseRoom;
    private ProjectDao mProjectDao;

    private static final ProjectEntity TEST_PROJECT = new ProjectEntity("Test Project A", 0xFFB4CDBB);
    private static final ProjectEntity TEST_PROJECT2 = new ProjectEntity("Test Project B", 0xFFB4CDBB);

    @Rule
    public InstantTaskExecutorRule mInstantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        mDatabaseRoom = Room.inMemoryDatabaseBuilder(context, DatabaseRoom.class)
                .allowMainThreadQueries()
                .build();
        mProjectDao = mDatabaseRoom.mProjectDao();
    }

    @After
    public void closeDb() {
        mDatabaseRoom.close();
    }

    @Test
    public void insertProject() throws InterruptedException {
        List<ProjectEntity> projectList = getOrAwaitValue(mProjectDao.getProjectList());
        assertFalse(projectList.contains(TEST_PROJECT));

        mProjectDao.insertProject(TEST_PROJECT);

        ProjectEntity projectFromTheList = getOrAwaitValue(mProjectDao.getProjectList()).get(0);
        assertThat(projectFromTheList.getName(), equalTo(TEST_PROJECT.getName()));
    }

    @Test
    public void getProjectById() throws InterruptedException {
        mProjectDao.insertProject(TEST_PROJECT);

        ProjectEntity expectedProject = mProjectDao.getProjectList(getOrAwaitValue(mProjectDao.getProjectList()).get(0).getId());

        assertThat(TEST_PROJECT.getName(), equalTo(expectedProject.getName()));
    }

    @Test
    public void getAllProject() throws InterruptedException {
        mProjectDao.insertProject(TEST_PROJECT);
        mProjectDao.insertProject(TEST_PROJECT2);

        List<ProjectEntity> projectList = getOrAwaitValue(mProjectDao.getProjectList());
        assertFalse(projectList.isEmpty());
        assertEquals(2, projectList.size());
        assertThat(TEST_PROJECT.getName(), equalTo(projectList.get(0).getName()));
        assertThat(TEST_PROJECT2.getName(), equalTo(projectList.get(1).getName()));
    }

}
