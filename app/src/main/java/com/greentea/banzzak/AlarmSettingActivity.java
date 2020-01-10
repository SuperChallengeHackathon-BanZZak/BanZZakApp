package com.greentea.banzzak;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.greentea.banzzak.AlarmDB.AlarmInfo;
import com.greentea.banzzak.Utils.Codes;

public class AlarmSettingActivity extends AppCompatActivity {

    Button applyBtn, deleteBtn;
    TimePicker timePicker;
    int hour, min, flag;
    AlarmInfo alarmInfo;

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

                Toast.makeText(AlarmSettingActivity.this, getHour() + " : " + getMin(), Toast.LENGTH_SHORT).show();

                alarmInfo.setHour(getHour());
                alarmInfo.setMin(getMin());
                alarmInfo.setAmpm(getAmpm());

                Intent intent = new Intent();
                intent.putExtra("alarm_info", alarmInfo);

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
}
