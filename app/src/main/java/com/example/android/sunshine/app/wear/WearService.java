package com.example.android.sunshine.app.wear;

import android.util.Log;

import com.example.android.sunshine.app.sync.SunshineSyncAdapter;
import com.google.android.gms.wearable.DataEvent;
import com.google.android.gms.wearable.DataEventBuffer;
import com.google.android.gms.wearable.WearableListenerService;

/**
 * Created by
 * --Vatsal Bajpai on
 * --08/09/16 at
 * --11:02 PM
 */
public class WearService extends WearableListenerService {

    private static final String TAG = WearService.class.getSimpleName();

    // path should be same as in watch module
    private static final String WEATHER_PATH = "/weather";

    @Override
    public void onDataChanged(DataEventBuffer dataEvents) {
        for (DataEvent dataEvent : dataEvents) {
            if (dataEvent.getType() == DataEvent.TYPE_CHANGED) {
                String path = dataEvent.getDataItem().getUri().getPath();
                Log.d(TAG, path);
                if (path.equals(WEATHER_PATH)) {
                    SunshineSyncAdapter.syncImmediately(this);
                }
            }
        }
    }
}