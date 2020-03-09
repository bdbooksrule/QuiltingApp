package com.example.quiltingapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ProjectModel.class}, version = 1, exportSchema = false)
public abstract class ProjRoomDatabase extends RoomDatabase {
    public abstract ProjDao projDao();

    private static ProjRoomDatabase INSTANCE;

    public static ProjRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ProjRoomDatabase.class) {
                if (INSTANCE == null) {
                    //Create Database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ProjRoomDatabase.class,
                            "project_database")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            // Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
