package com.mit.sqlitelocaldb.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.mit.sqlitelocaldb.model.User;

@Database(entities = {User.class} , version = 1)
public abstract class MyLocalDb extends RoomDatabase {
    private static final String DB_NAME = "myuser.db";
    private static volatile MyLocalDb instance;
    public static synchronized MyLocalDb getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }
    private static MyLocalDb create(final Context context) {
        return Room.databaseBuilder(
                context,
                MyLocalDb.class,
                DB_NAME)
                .fallbackToDestructiveMigration()
                .build();
    }
    public abstract UserDao getUserDao();
}
