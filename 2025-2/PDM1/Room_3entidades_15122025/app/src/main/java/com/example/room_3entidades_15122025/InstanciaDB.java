package com.example.room_3entidades_15122025;

import android.content.Context;

import androidx.room.Room;

public class InstanciaDB {

    public static AppDatabase getInstance(Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, "escola.db").allowMainThreadQueries().build();
    }
}
