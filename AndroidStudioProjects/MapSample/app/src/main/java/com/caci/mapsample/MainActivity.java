package com.caci.mapsample;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Geocoder;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {
    public static IncidentDataSource incidentDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        incidentDataSource = new IncidentDataSource(this);
        incidentDataSource.open();

        final Button mapBtn = (Button) findViewById(R.id.btn_map);
        mapBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent mapIntent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(mapIntent);
            }
        });

        IntentFilter statusIntentFilter = new IntentFilter(Constants.BROADCAST_ACTION_DATALOAD);
        MyResponseReceiver responseReceiver = new MyResponseReceiver();
        // Registers the MyResponseReceiver and its intent filters
        LocalBroadcastManager.getInstance(this).registerReceiver(responseReceiver, statusIntentFilter);
        Intent mServiceIntent = new Intent(MainActivity.this, DataLoadingService.class);
        MainActivity.this.startService(mServiceIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Broadcast receiver for receiving status updates from the IntentService
    private class MyResponseReceiver extends BroadcastReceiver {
        // Called when the BroadcastReceiver gets an Intent it's registered to receive
        public void onReceive(Context context, Intent intent) {
            ((TextView) findViewById(R.id.txt_loading)).setVisibility(View.GONE);
            ((Button) findViewById(R.id.btn_map)).setVisibility(View.VISIBLE);
        }
    }
}
