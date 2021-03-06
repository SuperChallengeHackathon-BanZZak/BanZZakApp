package com.greentea.banzzak.AlarmDB;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AlarmInfoDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(AlarmInfo alarmInfo);

    @Query("DELETE FROM alarm_table")
    void deleteAll();

    @Delete
    void deleteAlarm(AlarmInfo alarmInfo);

    @Query("DELETE FROM alarm_table WHERE :id = id")
    void deleteByID(int id);

    @Query("SELECT * FROM alarm_table ORDER BY hour and minute ASC")
    LiveData<List<AlarmInfo>> getAllAlarm();

    @Query("SELECT * FROM alarm_table")
    List<AlarmInfo> getAll();
}
