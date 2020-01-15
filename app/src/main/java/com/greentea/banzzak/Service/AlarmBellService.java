package com.greentea.banzzak.Service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class AlarmBellService extends Service {
    public AlarmBellService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
