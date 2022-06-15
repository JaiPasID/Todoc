package com.cleanup.todoc.viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.cleanup.todoc.database.DatabaseRoom;
import com.cleanup.todoc.repository.ProjectRepository;
import com.cleanup.todoc.repository.TaskRepository;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private ProjectRepository mProjectRepository;
    private TaskRepository mTaskRepository;

    private static ViewModelFactory mViewModelFactory;

    /**
     * Singleton
     * instantiate ViewModelFactory with things inside constructors
     */
    public static ViewModelFactory getInstance(Context context) {

        if (mViewModelFactory == null) {
            synchronized (ViewModelFactory.class) {
                mViewModelFactory = new ViewModelFactory(context);
            }
        }
        return mViewModelFactory;
    }

    /**
     * Constructor
     * Instantiate 2 repository
     */
    private ViewModelFactory(Context context) {
        DatabaseRoom lvDatabaseRoom = DatabaseRoom.getInstance(context);
        mProjectRepository = new ProjectRepository(lvDatabaseRoom.mProjectDao());
        mTaskRepository = new TaskRepository(lvDatabaseRoom.mTaskDao());
    }


    //TODO JE NE COMPRENDS PAS SON FONCTIONNEMENT
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass.isAssignableFrom(TaskViewModel.class)) {
            return (T) new TaskViewModel(mProjectRepository, mTaskRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }

}
