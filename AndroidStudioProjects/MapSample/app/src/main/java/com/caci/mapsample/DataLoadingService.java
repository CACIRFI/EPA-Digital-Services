package com.caci.mapsample;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.support.v4.content.LocalBroadcastManager;

/**
 * This is the background service used for the initial incident data load.  Sends broadcase
 * when data load has completed.
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 */
public class DataLoadingService extends IntentService {
    public DataLoadingService() {
        super("DataLoadingService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        IncidentDataSource ds = MainActivity.incidentDataSource;
        ds.loadInitialIncidents(this);

        // Broadcasts that data loading is complete
        Intent localIntent = new Intent(Constants.BROADCAST_ACTION_DATALOAD);
        LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);
    }

}
