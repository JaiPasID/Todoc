package com.cleanup.todoc;

import com.cleanup.todoc.database.ProjectDao;
import com.cleanup.todoc.repository.ProjectRepository;

import org.junit.Before;
import org.mockito.Mockito;

public class ProjectRepositoryUnitTest {

    private ProjectDao mProjectDao;
    private ProjectRepository mProjectRepository;

    @Before
    public void setUp() {
        mProjectDao = Mockito.mock(ProjectDao.class);
        mProjectRepository = new ProjectRepository(mProjectDao);
    }

}
