package com.greentea.banzzak;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.greentea.banzzak.AlarmDB.AlarmInfo;
import com.greentea.banzzak.Receiver.AlarmReceiver;
import com.greentea.banzzak.Utils.Codes;

import java.util.Calendar;

public class AlarmSettingActivity extends AppCompatActivity {

    Button applyBtn, deleteBtn;
    TimePicker timePicker;
    int hour, min, flag;
    AlarmInfo alarmInfo;
    AlarmManager alarmManager;
    Calendar calendar;
    Intent receiverIntent;
    PendingIntent pendingIntent;
    long time;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_setting);

        applyBtn = findViewById(R.id.setting_btn);
        deleteBtn = findViewById(R.id.delete_btn);
        timePicker = findViewById(R.id.timepicker);

        Intent gotton = getIntent();

        alarmInfo = (AlarmInfo) gotton.getSerializableExtra("origin");
        flag = gotton.getIntExtra("flag", 0);

        applyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    hour = timePicker.getHour();
                    min = timePicker.getMinute();
                } else {
                    hour = timePicker.getCurrentHour();
                    min = timePicker.getCurrentMinute();
                }

                alarmInfo.setHour(getHour());
                alarmInfo.setMin(getMin());
                alarmInfo.setAmpm(getAmpm());

                Intent intent = new Intent();
                intent.putExtra("alarm_info", alarmInfo);

                makeAlarm(hour, min, alarmInfo.getId());
//                Toast.makeText(AlarmSettingActivity.this, "" + alarmInfo.getId(), Toast.LENGTH_SHORT).show();
                Toast.makeText(AlarmSettingActivity.this, getHour() + ":" + getMin() + " " + getAmpm() + "에 알람이 울립니다.", Toast.LENGTH_SHORT).show();
                setResult(Codes.NEW_ALARM_CODE, intent);

                finish();
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(flag == 2){
                    Toast.makeText(AlarmSettingActivity.this, "삭제할 알람이 없습니다.", Toast.LENGTH_SHORT).show();
                    finish();
                }
                else {
                    Intent intent = new Intent();
                    intent.putExtra("delete_this", alarmInfo);

                    deleteAlarm(alarmInfo.getId());
//                    Toast.makeText(AlarmSettingActivity.this, "" + alarmInfo.getId(), Toast.LENGTH_SHORT).show();

                    setResult(Codes.DELETE_THIS_ALARM, intent);
                    finish();
                }
            }
        });
    }

    public String getHour(){
        if(hour < 10) return "0" + hour;
        else if(hour > 12){
            if(hour < 22) return "0" + (hour - 12);
            else return "" + (hour - 12);
        }
        else return "" + hour;
    }

    public String getMin(){
        if(min < 10) return "0" + min;
        return "" + min;
    }

    public String getAmpm(){
        return hour < 12 ? "AM" : "PM";
    }

    private void makeAlarm(int hour, int min, int alarmId){

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        calendar = Calendar.getInstance();

        receiverIntent = new Intent(this, AlarmReceiver.class);

        if (Build.VERSION.SDK_INT < 23) {
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, min);
            calendar.set(Calendar.SECOND, 0);
        }
        // 23 이상
        else {
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, min);
            calendar.set(Calendar.SECOND, 0);
        }

        time = calendar.getTimeInMillis();

        pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), alarmId, receiverIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);

        if (Build.VERSION.SDK_INT < 23) {
            if (Build.VERSION.SDK_INT >= 19) {
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, time, pendingIntent);
            }
            else {
                alarmManager.set(AlarmManager.RTC_WAKEUP, time, pendingIntent);
            }
        }
        else {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, time, pendingIntent);
        }
    }

    public void deleteAlarm(int alarmId){

        Intent intent = new Intent(this, AlarmReceiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), alarmId, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        if(pendingIntent != null) {
            alarmManager.cancel(pendingIntent);
            pendingIntent.cancel();
        }
    }
}
