package com.greentea.banzzak;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.greentea.banzzak.Adapter.RecyclerViewAdapter;
import com.greentea.banzzak.AlarmDB.AlarmInfo;
import com.greentea.banzzak.ViewModel.AlarmViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.OnListItemSelectedInterface{

    final int TIME_SETTING_REQUEST_CODE = 1;

    FloatingActionButton addAlarmBtn;
    Intent intent;

    private AlarmViewModel alarmViewModel;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

        addAlarmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(MainActivity.this, AlarmSettingActivity.class);
                startActivityForResult(intent, TIME_SETTING_REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void init(){

        addAlarmBtn = findViewById(R.id.fab_main);
        recyclerView = findViewById(R.id.recyclerView);

        List<AlarmInfo> asdf = new ArrayList<>();

//        for(int i=0; i<10; i++){
            AlarmInfo aa = new AlarmInfo();
            aa.setAlarmName(Integer.toString(0));
            aa.setAlarmTime(Integer.toString(0));
            asdf.add(aa);
//        }

        aa = new AlarmInfo();
        aa.setAlarmName(Integer.toString(1));
        aa.setAlarmTime(Integer.toString(1));
        asdf.add(aa);
        aa = new AlarmInfo();
        aa.setAlarmName(Integer.toString(2));
        aa.setAlarmTime(Integer.toString(2));
        asdf.add(aa);

        adapter = new RecyclerViewAdapter(this, this);
        adapter.setAlarms(asdf);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


//        alarmViewModel = ViewModelProviders.of(this).get(AlarmViewModel.class);
//        alarmViewModel.getAllAlarmInfo().observe(this, new Observer<List<AlarmInfo>>() {
//            @Override
//            public void onChanged(List<AlarmInfo> alarmInfos) {
//
//                adapter.setAlarms(alarmInfos);
//
//            }
//        });
    }

    @Override
    public void onItemSelected(View v, int pos) {
        RecyclerViewAdapter.ItemViewHolder viewHolder = (RecyclerViewAdapter.ItemViewHolder)recyclerView.findViewHolderForAdapterPosition(pos);
    }
}
