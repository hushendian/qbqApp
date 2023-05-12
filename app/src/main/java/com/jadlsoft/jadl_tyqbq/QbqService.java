package com.jadlsoft.jadl_tyqbq;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class QbqService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new IQbqServiceImpl();
    }
}