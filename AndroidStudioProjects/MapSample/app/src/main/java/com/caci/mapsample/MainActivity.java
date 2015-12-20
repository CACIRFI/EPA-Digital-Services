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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends Activity {
    //Reference to the incident database used throughout the app
    public static IncidentDataSource incidentDataSource;

    //Optional filters to use when displaying data
    private String stateFilter = null;
    private String causeFilter = null;

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
                mapIntent.putExtra("stateFilter", stateFilter);
                mapIntent.putExtra("causeFilter", causeFilter);
                startActivity(mapIntent);
            }
        });

        //Registers listener for when the initial dataload has completed
        IntentFilter statusIntentFilter = new IntentFilter(Constants.BROADCAST_ACTION_DATALOAD);
        MyResponseReceiver responseReceiver = new MyResponseReceiver();
        LocalBroadcastManager.getInstance(this).registerReceiver(responseReceiver, statusIntentFilter);

        //Kick off background process to load the initial dataset
        Intent mServiceIntent = new Intent(MainActivity.this, DataLoadingService.class);
        MainActivity.this.startService(mServiceIntent);

        Spinner stateSpinner = (Spinner) findViewById(R.id.state_spinner);
        ArrayAdapter<CharSequence> stateAdapter = ArrayAdapter
                .createFromResource(this, R.array.state_array,android.R.layout.simple_spinner_item);
        stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stateSpinner.setAdapter(stateAdapter);

        stateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,int position, long id) {
                String selectedState = (String) parent.getItemAtPosition(position);
                if (!Constants.STATE_NO_FILTER.equals(selectedState)) {
                    stateFilter = Constants.STATE_ABBREV.get(selectedState);
                } else {
                    stateFilter = null;
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Spinner causeSpinner = (Spinner) findViewById(R.id.cause_spinner);
        ArrayAdapter<CharSequence> causeAdapter = ArrayAdapter
                .createFromResource(this, R.array.cause_array,android.R.layout.simple_spinner_item);
        causeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        causeSpinner.setAdapter(causeAdapter);
        causeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCause = (String) parent.getItemAtPosition(position);
                if (!Constants.CAUSE_NO_FILTER.equals(selectedCause)) {
                    causeFilter = selectedCause;
                } else {
                    causeFilter = null;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

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

    /**
     * Broadcast receiver for when dataload has completed.  Updates the UI to allow
     * mapping of data.
     */
    private class MyResponseReceiver extends BroadcastReceiver {
        // Called when the BroadcastReceiver gets an Intent it's registered to receive
        public void onReceive(Context context, Intent intent) {
            findViewById(R.id.txt_loading).setVisibility(View.GONE);
            findViewById(R.id.loadingPanel).setVisibility(View.GONE);
            findViewById(R.id.btn_map).setVisibility(View.VISIBLE);
        }
    }
}
