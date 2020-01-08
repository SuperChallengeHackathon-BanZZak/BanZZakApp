package com.greentea.banzzak.AlarmDB;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "alarm_table")
public class AlarmInfo implements Serializable {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "alarmName")
    private String alarmName;

    @NonNull
    @ColumnInfo(name = "alarmTime")
    private String alarmTime;

    @NonNull
    public String getAlarmName() {
        return alarmName;
    }

    public void setAlarmName(@NonNull String alarmName) {
        this.alarmName = alarmName;
    }

    @NonNull
    public String getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(@NonNull String alarmTime) {
        this.alarmTime = alarmTime;
    }
}
