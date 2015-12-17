package com.caci.mapsample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Mike Coddington on 12/7/2015.
 */
public class IncidentDataSource {

    private SQLiteDatabase database;
    private IncidentDbHelper dbHelper;

    public IncidentDataSource(Context context) {
        dbHelper = new IncidentDbHelper(context);
    }

    /**
     * Populates the database from the hardcoded 'incidents' input file that must be packaged with the app
     * @param ctx
     */
    public void loadInitialIncidents(Context ctx) {
        //Only proceed if the database is not empty
        if (!isDatabaseEmpty()) return;

        Geocoder geocoder = new Geocoder(ctx);
        int numIncidentsGeocoded = 0;
        int numIncidentsNotGeocoded = 0;
        int recordsLoaded = 0;
        int max_records = 200;
        InputStream inputStream = ctx.getResources().openRawResource(R.raw.incidents);

        InputStreamReader inputreader = new InputStreamReader(inputStream);
        BufferedReader buffreader = new BufferedReader(inputreader);
        String line;

        try {
            while (( line = buffreader.readLine()) != null) {
                //Each line is expected to be a comma separated record with 48 entries.  See constructor for expected order of fields
                if (line.endsWith(",")) line+=" ";
                String[] row = line.split(",");
                if (row.length == 48) {
                    Incident incident = new Incident(row[0],row[1],row[2],row[3],row[4],row[5],row[6],row[7],row[8],row[9],row[10],
                            row[11],row[12],row[13],row[14],row[15],row[16],row[17],row[18],row[19],row[20],row[21],row[22],row[23],row[24],
                            row[25],row[26],row[27],row[28],row[29],row[30],row[31],row[32],row[33],row[34],row[35],row[36],row[37],row[38],
                            row[39],row[40],row[41],row[42],row[43],row[44],row[45],row[46],row[47]
                    );

                    //Geocode incident as it's loaded from file.  If it can't be geocoded, do not add to DB
                    try {
                        String addressStr = incident.address + "," + incident.city +","+ incident.county +" County,"+ incident.state +","+ incident.zip;
                        List<Address> addressList = geocoder.getFromLocationName(addressStr, 1);
                        if (addressList !=null && addressList.size()>0) {
                            Address addr = addressList.get(0);
                            incident.latitude = addr.getLatitude();
                            incident.longitude = addr.getLongitude();
                            numIncidentsGeocoded++;
                            addIncident(incident);
                        } else {
                            numIncidentsNotGeocoded++;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (max_records==numIncidentsGeocoded) break;
            }
        } catch (IOException e) {
            throw new RuntimeException("Error in reading CSV file: "+e);
        } finally {
            try {
                inputStream.close();
            }
            catch (IOException e) {
                throw new RuntimeException("Error while closing input stream: "+e);
            }
        }
        System.out.println("loading complete");

    }

    /**
     * Adds the specified incident to the database
     * @param incident
     * @return
     */
    public Incident addIncident(Incident incident) {
        ContentValues values = new ContentValues();
        values.put(IncidentDbHelper.COL_INCIDENT_ID, incident.reportId);
        values.put(IncidentDbHelper.COL_OP, incident.operator);
        values.put(IncidentDbHelper.COL_OP_CODE, incident.operatorCode);
        values.put(IncidentDbHelper.COL_ADDR, incident.address);
        values.put(IncidentDbHelper.COL_CAUSE, incident.cause);
        values.put(IncidentDbHelper.COL_CITY, incident.city);
        values.put(IncidentDbHelper.COL_COUNTY, incident.county);
        values.put(IncidentDbHelper.COL_STATE, incident.state);
        values.put(IncidentDbHelper.COL_ZIP, incident.zip);
        values.put(IncidentDbHelper.COL_LAT, incident.latitude);
        values.put(IncidentDbHelper.COL_LONG, incident.longitude);
        values.put(IncidentDbHelper.COL_INCIDENT_DATE, incident.date);
        values.put(IncidentDbHelper.COL_DETECTION_TIME, incident.detectionTime);
        values.put(IncidentDbHelper.COL_ORIGIN_LEAK, incident.originLeak);
        values.put(IncidentDbHelper.COL_ORIGIN_LEAK_OTHER, incident.originLeakOther);
        values.put(IncidentDbHelper.COL_FATALITIES, incident.fatalities);
        values.put(IncidentDbHelper.COL_INJURIES, incident.injuries);
        values.put(IncidentDbHelper.COL_GAS_IGNITED, incident.gasIgnited);
        values.put(IncidentDbHelper.COL_EXPLOSION_OCCURED, incident.explosionOccured);
        values.put(IncidentDbHelper.COL_SECONDARY_EXPLOSIONS_FIRE, incident.secondaryExplFire);
        values.put(IncidentDbHelper.COL_DISTANCE_SEWER_STORM_CONT, incident.distanceStormCont);
        values.put(IncidentDbHelper.COL_DISTANCE_SEWER_STORM_IMPA, incident.distanceSewerStorm);
        values.put(IncidentDbHelper.COL_DISTANCE_SEWER_OTHER_CONT, incident.distanceSewerOther);
        values.put(IncidentDbHelper.COL_DISTANCE_SEWER_OTHER_IMPA, incident.distanceSewerOtherImp);
        values.put(IncidentDbHelper.COL_DISTANCE_WATER_CONTR, incident.distanceWaterContr);
        values.put(IncidentDbHelper.COL_DISTANCE_WATER_IMPAIRED, incident.distanceWaterImpaired);
        values.put(IncidentDbHelper.COL_LOCATION_LEAK, incident.locationLeak);
        values.put(IncidentDbHelper.COL_LOCATION_LEAK_OTHER, incident.locationLeakOther);
        values.put(IncidentDbHelper.COL_COVER_DEPTH, incident.coverDepth);
        values.put(IncidentDbHelper.COL_SOIL_INFORMATION, incident.soilInformation);
        values.put(IncidentDbHelper.COL_SOIL_TEMPERATURE, incident.soilTemp);
        values.put(IncidentDbHelper.COL_REPORTED_BY, incident.reportedBy);
        values.put(IncidentDbHelper.COL_REPORTED_BY_OTHER, incident.reportedByOther);
        values.put(IncidentDbHelper.COL_MATERIAL_LEAKED_D, incident.materialLeaked);
        values.put(IncidentDbHelper.COL_MATERIAL_LEAKED_D_OTHER, incident.materialLeakedOther);
        values.put(IncidentDbHelper.COL_LOCATION_CORROSION, incident.locCorrosion);
        values.put(IncidentDbHelper.COL_DESCRIPTION_CORROSION, incident.descCorrosion);
        values.put(IncidentDbHelper.COL_CAUSE_CORROSION, incident.causeCorrosion);
        values.put(IncidentDbHelper.COL_CAUSE_CORROSION_OTHER, incident.causeCorrosionOther);
        values.put(IncidentDbHelper.COL_PH_SOIL, incident.phSoil);
        values.put(IncidentDbHelper.COL_SOIL_RESISTIVITY, incident.soilResist);
        values.put(IncidentDbHelper.COL_SOIL_TEST_YEAR, incident.soilTestYear);
        values.put(IncidentDbHelper.COL_PIPE_SOIL_POTENTIAL_1, incident.pipeSoilPotential1);
        values.put(IncidentDbHelper.COL_PIPE_SOIL_POTENTIAL_2, incident.pipeSoilPotential2);
        values.put(IncidentDbHelper.COL_CAUSE_LEAK, incident.causeLeak);
        values.put(IncidentDbHelper.COL_CAUSE_LEAK_OTHER, incident.causeLeakOther);

        long insertId = database.insert(IncidentDbHelper.TABLE_NAME, null, values);
        Cursor cursor = database.query(IncidentDbHelper.TABLE_NAME, IncidentDbHelper.allColumns,
                IncidentDbHelper.COL_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        Incident newIncident = cursorToIncident(cursor);
        cursor.close();
        return newIncident;
    }

    /**
     * Translates from a database cursor to an individual incident records
     * @param cursor
     * @return
     */
    private Incident cursorToIncident(Cursor cursor) {
        Incident incident = new Incident();
        incident._id = cursor.getLong(cursor.getColumnIndex(IncidentDbHelper.COL_ID));
        incident.reportId = cursor.getLong(cursor.getColumnIndex(IncidentDbHelper.COL_INCIDENT_ID));
        incident.operator = cursor.getString(cursor.getColumnIndex(IncidentDbHelper.COL_OP));
        incident.cause = cursor.getString(cursor.getColumnIndex(IncidentDbHelper.COL_CAUSE));
        incident.state = cursor.getString(cursor.getColumnIndex(IncidentDbHelper.COL_STATE));
        incident.zip = cursor.getString(cursor.getColumnIndex(IncidentDbHelper.COL_ZIP));
        incident.address = cursor.getString(cursor.getColumnIndex(IncidentDbHelper.COL_ADDR));
        incident.city = cursor.getString(cursor.getColumnIndex(IncidentDbHelper.COL_CITY));
        incident.county = cursor.getString(cursor.getColumnIndex(IncidentDbHelper.COL_COUNTY));
        incident.operatorCode = cursor.getString(cursor.getColumnIndex(IncidentDbHelper.COL_OP_CODE));
        incident.latitude = cursor.getDouble(cursor.getColumnIndex(IncidentDbHelper.COL_LAT));
        incident.longitude = cursor.getDouble(cursor.getColumnIndex(IncidentDbHelper.COL_LONG));
        incident.date = cursor.getString(cursor.getColumnIndex(IncidentDbHelper.COL_INCIDENT_DATE));
        incident.detectionTime = cursor.getString(cursor.getColumnIndex(IncidentDbHelper.COL_DETECTION_TIME));
        incident.originLeak = cursor.getString(cursor.getColumnIndex(IncidentDbHelper.COL_ORIGIN_LEAK));
        incident.originLeakOther = cursor.getString(cursor.getColumnIndex(IncidentDbHelper.COL_ORIGIN_LEAK_OTHER));
        incident.fatalities = cursor.getString(cursor.getColumnIndex(IncidentDbHelper.COL_FATALITIES));
        incident.injuries = cursor.getString(cursor.getColumnIndex(IncidentDbHelper.COL_INJURIES));
        incident.gasIgnited = cursor.getString(cursor.getColumnIndex(IncidentDbHelper.COL_GAS_IGNITED));
        incident.explosionOccured = cursor.getString(cursor.getColumnIndex(IncidentDbHelper.COL_EXPLOSION_OCCURED));
        incident.secondaryExplFire = cursor.getString(cursor.getColumnIndex(IncidentDbHelper.COL_SECONDARY_EXPLOSIONS_FIRE));
        incident.distanceStormCont = cursor.getString(cursor.getColumnIndex(IncidentDbHelper.COL_DISTANCE_SEWER_STORM_CONT));
        incident.distanceSewerStorm = cursor.getString(cursor.getColumnIndex(IncidentDbHelper.COL_DISTANCE_SEWER_STORM_IMPA));
        incident.distanceSewerOther = cursor.getString(cursor.getColumnIndex(IncidentDbHelper.COL_DISTANCE_SEWER_OTHER_CONT));
        incident.distanceSewerOtherImp = cursor.getString(cursor.getColumnIndex(IncidentDbHelper.COL_DISTANCE_SEWER_OTHER_IMPA));
        incident.distanceWaterContr = cursor.getString(cursor.getColumnIndex(IncidentDbHelper.COL_DISTANCE_WATER_CONTR));
        incident.distanceWaterImpaired = cursor.getString(cursor.getColumnIndex(IncidentDbHelper.COL_DISTANCE_WATER_IMPAIRED));
        incident.locationLeak = cursor.getString(cursor.getColumnIndex(IncidentDbHelper.COL_LOCATION_LEAK));
        incident.locationLeakOther = cursor.getString(cursor.getColumnIndex(IncidentDbHelper.COL_LOCATION_LEAK_OTHER));
        incident.coverDepth = cursor.getString(cursor.getColumnIndex(IncidentDbHelper.COL_COVER_DEPTH));
        incident.soilInformation = cursor.getString(cursor.getColumnIndex(IncidentDbHelper.COL_SOIL_INFORMATION));
        incident.soilTemp = cursor.getString(cursor.getColumnIndex(IncidentDbHelper.COL_SOIL_TEMPERATURE));
        incident.reportedBy = cursor.getString(cursor.getColumnIndex(IncidentDbHelper.COL_REPORTED_BY));
        incident.reportedByOther = cursor.getString(cursor.getColumnIndex(IncidentDbHelper.COL_REPORTED_BY_OTHER));
        incident.materialLeaked = cursor.getString(cursor.getColumnIndex(IncidentDbHelper.COL_MATERIAL_LEAKED_D));
        incident.materialLeakedOther = cursor.getString(cursor.getColumnIndex(IncidentDbHelper.COL_MATERIAL_LEAKED_D_OTHER));
        incident.locCorrosion = cursor.getString(cursor.getColumnIndex(IncidentDbHelper.COL_LOCATION_CORROSION));
        incident.descCorrosion = cursor.getString(cursor.getColumnIndex(IncidentDbHelper.COL_DESCRIPTION_CORROSION));
        incident.causeCorrosion = cursor.getString(cursor.getColumnIndex(IncidentDbHelper.COL_CAUSE_CORROSION));
        incident.causeCorrosionOther = cursor.getString(cursor.getColumnIndex(IncidentDbHelper.COL_CAUSE_CORROSION_OTHER));
        incident.phSoil = cursor.getString(cursor.getColumnIndex(IncidentDbHelper.COL_PH_SOIL));
        incident.soilResist = cursor.getString(cursor.getColumnIndex(IncidentDbHelper.COL_SOIL_RESISTIVITY));
        incident.soilTestYear = cursor.getString(cursor.getColumnIndex(IncidentDbHelper.COL_SOIL_TEST_YEAR));
        incident.pipeSoilPotential1 = cursor.getString(cursor.getColumnIndex(IncidentDbHelper.COL_PIPE_SOIL_POTENTIAL_1));
        incident.pipeSoilPotential2 = cursor.getString(cursor.getColumnIndex(IncidentDbHelper.COL_PIPE_SOIL_POTENTIAL_2));
        incident.causeLeak = cursor.getString(cursor.getColumnIndex(IncidentDbHelper.COL_CAUSE_LEAK));
        return incident;
    }


    /**
     * Retrieves all incidents from the database
     * @return
     */
    public Incident getIncident(Long incidentId) {
        Incident i = null;

        String selectQuery = "SELECT  * FROM " + IncidentDbHelper.TABLE_NAME + " WHERE "
                + IncidentDbHelper.COL_ID + " = " + incidentId;
        Cursor cursor = database.rawQuery(selectQuery, null);
        if (cursor!=null) {
            cursor.moveToFirst();
            i = cursorToIncident(cursor);
            cursor.close();
        }
        return i;
    }

    /**
     * Retrieves all incidents from the database
     * @return
     */
    public List<Incident> getAll() {
        List<Incident> incidents = new ArrayList<Incident>();

        Cursor cursor = database.query(IncidentDbHelper.TABLE_NAME,IncidentDbHelper.allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Incident incident = cursorToIncident(cursor);
            incidents.add(incident);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return incidents;
    }

    /**
     * Deletes the specified incident from the database
     * @param incident
     */
    public void deleteIncident(Incident incident) {
        long id = incident._id;
        database.delete(IncidentDbHelper.TABLE_NAME, IncidentDbHelper.COL_ID + " = " + id, null);
    }

    /**
     * Deletes all incidents from the database
     */
    public void deleteAll() {
        dbHelper.onUpgrade(database, 1, 2);
    }

    public boolean isDatabaseEmpty() {
        Cursor cursor = database.query(IncidentDbHelper.TABLE_NAME,IncidentDbHelper.allColumns, null, null, null, null, null, "1");
        boolean isEmpty = !(cursor.getCount()>0);
        cursor.close();
        return isEmpty;
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

}