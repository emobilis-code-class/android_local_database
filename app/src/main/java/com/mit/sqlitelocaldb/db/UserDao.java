package com.mit.sqlitelocaldb.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.mit.sqlitelocaldb.model.User;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM User")
    LiveData<List<User>> getAllUser();

    @Query("SELECT * FROM User WHERE id=:user_id")
    LiveData<User> getUserById(int user_id);

    @Query("DELETE FROM User WHERE id=:user_id")
    void deleteUser(int user_id);

    @Insert
    void insertUser(User user);


}
