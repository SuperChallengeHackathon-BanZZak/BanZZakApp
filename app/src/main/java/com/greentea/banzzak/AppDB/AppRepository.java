package com.greentea.banzzak.AppDB;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.greentea.banzzak.AlarmDB.AlarmInfo;
import com.greentea.banzzak.AlarmDB.AlarmInfoDAO;

import java.util.List;

public class AppRepository {

    private final AlarmInfoDAO alarmInfoDAO;
    private final LiveData<List<AlarmInfo>> allAlarmInfo;

    public AppRepository(Application application){
        AppDB db = AppDB.getDB(application);
        alarmInfoDAO = db.alarmInfoDAO();
        allAlarmInfo = alarmInfoDAO.getAllAlarm();
    }

    public void insert(final AlarmInfo alarmInfo){
        new AsyncTask<AlarmInfo, Void, Void>(){

            @Override
            protected Void doInBackground(AlarmInfo... alarmInfos) {

                if(alarmInfoDAO == null) return null;
                else {
                    alarmInfoDAO.insert(alarmInfos[0]);
                    return null;
                }
            }

        }.execute(alarmInfo);
    }

    public void deleteAll(){

        new AsyncTask<Void, Void, Void>(){

            @Override
            protected Void doInBackground(Void... voids) {
                if(alarmInfoDAO == null) return null;
                else{
                    alarmInfoDAO.deleteAll();
                    return null;
                }
            }
        }.execute();
    }

    public void delete(AlarmInfo alarmInfo){
        new deleteAsyncTask(alarmInfoDAO).execute(alarmInfo);
    }

    public void deleteByID(int id){
        new deleteByIDAsynkTask(alarmInfoDAO).execute(id);
    }

    public LiveData<List<AlarmInfo>> getAllAlarmInfo() {
        return allAlarmInfo;
    }

    private static class deleteAsyncTask extends AsyncTask<AlarmInfo, Void, Void>{

        private AlarmInfoDAO alarmInfoDAO;

        deleteAsyncTask(AlarmInfoDAO dao){
            alarmInfoDAO = dao;
        }

        @Override
        protected Void doInBackground(AlarmInfo... alarmInfos) {
            alarmInfoDAO.deleteAlarm(alarmInfos[0]);
            return null;
        }
    }

    private static class deleteByIDAsynkTask extends AsyncTask<Integer, Void, Void>{

        private AlarmInfoDAO alarmInfoDAO;

        deleteByIDAsynkTask(AlarmInfoDAO dao) {alarmInfoDAO = dao;}

        @Override
        protected Void doInBackground(Integer... integers) {
            alarmInfoDAO.deleteByID(integers[0]);
            return null;
        }
    }
}
