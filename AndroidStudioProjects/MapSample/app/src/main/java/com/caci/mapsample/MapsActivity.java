package com.caci.mapsample;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    private Map<Marker,Incident> incidentMarkerMap = new HashMap<Marker, Incident>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #addMapData()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();

//            try {
                MapsInitializer.initialize(MapsActivity.this);
//            }
//            catch (GooglePlayServicesNotAvailableException e) {
//                Log.e("mapSample", "Have GoogleMap but then error", e);
//                return;
//            }

            // CHICAGO CENTER coords
            CameraUpdate center= CameraUpdateFactory.newLatLng(new LatLng(42.33270497589648, -96.02653473615646));
            CameraUpdate zoom = CameraUpdateFactory.zoomTo((float) 3.05);

            mMap.moveCamera(center);
            mMap.animateCamera(zoom);

            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                addMapData();
            }

            // Setting a custom info window adapter for the google map
            mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
                //Use default InfoWindow frame
                @Override
                public View getInfoWindow(Marker arg0) {
                    return null;
                }
                // Defines the contents of the InfoWindow
                @Override
                public View getInfoContents(Marker arg0) {
                    // Getting view from the layout file info_window_layout
                    View v = getLayoutInflater().inflate(R.layout.windowlayout, null);
                    Incident i = incidentMarkerMap.get(arg0);

                    TextView title = (TextView) v.findViewById(R.id.info_txt_address);
                    String addressStr = i.city +", "+i.state;
                    title.setText(addressStr);
                    TextView op = (TextView) v.findViewById(R.id.info_txt_operator);
                    op.setText(i.operator);
                    return v;
                }
            });

            // When marker clicked, go to detail window
            mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                @Override
                public void onInfoWindowClick(Marker marker) {
                    try {
                        Incident i = incidentMarkerMap.get(marker);
                        Intent intent = new Intent(MapsActivity.this, IncidentDetailActivity.class);
                        intent.putExtra("incidentId", i._id);
                        startActivity(intent);

                    } catch (Exception e) {
                        System.out.println("**************** exception"+e.getMessage());
                    }
                }
            });
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void addMapData() {
        List<Incident> incidentList = MainActivity.incidentDataSource.getAll();
        for (Incident incident : incidentList) {
            Marker m = mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(incident.latitude, incident.longitude)));
            incidentMarkerMap.put(m, incident);
        }
    }

}
