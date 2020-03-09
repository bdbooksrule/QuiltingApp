package com.example.quiltingapp;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProjDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert (ProjectModel projectModel);

    //will probably want to add delete and update methods as well
    @Update
    void updateProjects(ProjectModel... projectModels);

    @Delete
    void deleteProjects(ProjectModel... projectModels);

    @Query("DELETE FROM project_table")
    void deleteAll();

    @Query("SELECT * from project_table ORDER BY id ASC")
    LiveData<List<ProjectModel>> getAllProjects();
}
