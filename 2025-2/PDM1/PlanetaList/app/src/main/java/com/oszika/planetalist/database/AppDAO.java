package com.oszika.planetalist.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.oszika.planetalist.model.UserPlanet;

import java.util.List;

@Dao
public interface AppDAO {
    @Insert
    Long inserirUserPlanet(UserPlanet c);

    @Query("SELECT * FROM user_planet")
    List<UserPlanet> obterUserPlanets();

}
