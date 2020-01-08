package com.greentea.banzzak.ViewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.greentea.banzzak.AlarmDB.AlarmInfo;
import com.greentea.banzzak.AppDB.AppRepository;

import java.util.List;

public class AlarmViewModel extends AndroidViewModel {

    private final AppRepository repository;
    private final LiveData<List<AlarmInfo>> allAlarmInfo;

    public AlarmViewModel(Application application){
        super(application);
        repository = new AppRepository(application);
        allAlarmInfo = repository.getAllAlarmInfo();
    }

    public void insert(AlarmInfo alarmInfo) {repository.insert(alarmInfo);}
    public void deleteAll() {repository.deleteAll();}
    public LiveData<List<AlarmInfo>> getAllAlarmInfo() {return allAlarmInfo;}
}
