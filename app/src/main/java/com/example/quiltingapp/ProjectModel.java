package com.example.quiltingapp;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity (tableName = "project_table")
public class ProjectModel {
    @PrimaryKey
    @NonNull
    @ColumnInfo (name = "id")
    private int id;

    @ColumnInfo (name = "title")
    private String title;

    @ColumnInfo (name = "designer")
    private String designer;

    @ColumnInfo (name = "status")
    private String status;

    @ColumnInfo (name = "notes")
    private String notes;

    public ProjectModel(int id) {
        this.id = id;
        this.title = "";
        this.designer = "";
        this.status = "";
        this.notes = "";
    }

    public int getId() { return id; }

    public String getDesigner() {
        return designer;
    }

    public String getTitle() {
        return title;
    }

    public String getNotes() {
        return notes;
    }

    public String getStatus() {
        return status;
    }

    public void setDesigner(String designer) {
        this.designer = designer;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @NonNull
    @Override
    public String toString() {
        String sum = this.title + ";\n" + this.status + ";\n" + this.designer + ";\n" + this.id + ";\n" + this.notes;
        return sum;
    }
}
