package com.example.quiltingapp;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ProjectViewModel extends AndroidViewModel {

    private ProjRepository mRepository;
    private LiveData<List<ProjectModel>> mAllProjects;

    public ProjectViewModel(Application application) {
        super(application);
        mRepository = new ProjRepository(application);
        mAllProjects = mRepository.getAllProjects();
    }

    LiveData<List<ProjectModel>> getAllProjects() {
        return mAllProjects;
    }

    public void insert(ProjectModel projectModel) {
        mRepository.insert(projectModel);
    }

    public void update(ProjectModel projectModel) {
        mRepository.update(projectModel);
    }

/*
    public void delete(ProjectModel projectModel) {
        mRepository.delete(projectModel);
    }
*/

}
