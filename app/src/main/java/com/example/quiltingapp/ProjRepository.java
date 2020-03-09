package com.example.quiltingapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ProjRepository {
    private ProjDao mProjDao;
    private LiveData<List<ProjectModel>> mAllProjects;

    ProjRepository(Application application) {
        ProjRoomDatabase db = ProjRoomDatabase.getDatabase(application);
        mProjDao = db.projDao();
        mAllProjects = mProjDao.getAllProjects();
    }

    LiveData<List<ProjectModel>> getAllProjects() {
        return mAllProjects;
    }

    public void insert(ProjectModel projectModel) {
        new insertAsyncTask(mProjDao).execute(projectModel);
    }

    private static class insertAsyncTask extends AsyncTask<ProjectModel, Void, Void> {

        private ProjDao mAsyncTaskDao;

        insertAsyncTask(ProjDao projDao) {
            mAsyncTaskDao = projDao;
        }

        @Override
        protected Void doInBackground(final ProjectModel... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    public void update(ProjectModel... projectModels) {
        new updateAsyncTask(mProjDao).execute(projectModels);
    }

    private static class updateAsyncTask extends AsyncTask<ProjectModel, Void, Void> {

        private ProjDao mAsyncTaskDao;

        updateAsyncTask(ProjDao projDao) {
            mAsyncTaskDao = projDao;
        }

        @Override
        protected Void doInBackground(ProjectModel... projectModels) {
            mAsyncTaskDao.updateProjects(projectModels);
            return null;
        }
    }
/*
    public void  delete(ProjectModel... projectModels) {
        new deleteAsyncTask(mProjDao).execute(projectModels);
    }

    private static class deleteAsyncTask extends AsyncTask<ProjectModel, Void, Void> {
        private ProjDao mAsyncTaskDao;

        deleteAsyncTask(ProjDao projDao) {
            mAsyncTaskDao = projDao;
        }

        @Override
        protected Void doInBackground(ProjectModel... projectModels) {
            mAsyncTaskDao.deleteProjects(projectModels);
            return null;
        }
    }*/
}
