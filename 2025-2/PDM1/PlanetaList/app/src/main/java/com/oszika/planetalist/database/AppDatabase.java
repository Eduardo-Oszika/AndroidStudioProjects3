package com.oszika.planetalist.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.oszika.planetalist.model.UserPlanet;

@Database(entities = UserPlanet.class, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AppDAO appDAO();

}
