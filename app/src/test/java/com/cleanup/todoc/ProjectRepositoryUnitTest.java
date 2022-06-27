package com.cleanup.todoc;

import com.cleanup.todoc.database.ProjectDao;
import com.cleanup.todoc.repository.ProjectRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class ProjectRepositoryUnitTest {

    private ProjectDao mProjectDao;
    private ProjectRepository mProjectRepository;

    @Before
    public void setUp() {
        mProjectDao = Mockito.mock(ProjectDao.class);
        mProjectRepository = new ProjectRepository(mProjectDao);
    }
    @Test
    public void getAllProjects() {

        mProjectRepository.getAllProject();
        Mockito.verify(mProjectDao, Mockito.atLeastOnce()).getProjectList();
    }


}
