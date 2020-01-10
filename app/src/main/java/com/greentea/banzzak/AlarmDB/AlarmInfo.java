package com.greentea.banzzak.AlarmDB;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "alarm_table")
public class AlarmInfo implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id;

    @NonNull
    @ColumnInfo(name = "hour")
    private String hour;

    @NonNull
    @ColumnInfo(name = "minute")
    private String min;

    @NonNull
    @ColumnInfo(name = "ampm")
    private String ampm;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getHour() {
        return hour;
    }

    public void setHour(@NonNull String hour) {
        this.hour = hour;
    }

    @NonNull
    public String getMin() {
        return min;
    }

    public void setMin(@NonNull String min) {
        this.min = min;
    }

    @NonNull
    public String getAmpm() {
        return ampm;
    }

    public void setAmpm(@NonNull String ampm) {
        this.ampm = ampm;
    }
}
