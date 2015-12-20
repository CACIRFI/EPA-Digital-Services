package com.caci.mapsample;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Used to define and manage the incident database structure
 * Created by Mike Coddington on 12/7/2015.
 */
public class IncidentDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Incidents.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    public static final String TABLE_NAME = "incidents";
    public static final String COL_ID = "_ID";
    public static final String COL_INCIDENT_ID = "REPORT_ID";
    public static final String COL_OP = "OPERATOR_NAME";
    public static final String COL_OP_CODE = "OPERATOR_CODE";
    public static final String COL_CAUSE = "CAUSE_IDENTIFIER";
    public static final String COL_STATE = "INCIDENT_STATE";
    public static final String COL_ZIP = "ZIP_CODE";
    public static final String COL_ADDR = "INCIDENT_ADDRESS";
    public static final String COL_CITY = "INCIDENT_CITY";
    public static final String COL_COUNTY = "INCIDENT_COUNTY";
    public static final String COL_INCIDENT_DATE = "INCIDENT_DATE";
    public static final String COL_DETECTION_TIME = "DETECTION_TIME";
    public static final String COL_ORIGIN_LEAK = "ORIGIN_LEAK";
    public static final String COL_ORIGIN_LEAK_OTHER = "ORIGIN_LEAK_OTHER";
    public static final String COL_FATALITIES = "FATALITIES";
    public static final String COL_INJURIES = "INJURIES";
    public static final String COL_EMPLOYEE_FATALITIES = "EMPLOYEE_FATALITIES";
    public static final String COL_EMPLOYEE_INJURIES = "EMPLOYEE_INJURIES";
    public static final String COL_NON_EMPLOYEE_FATALITIES = "NON_EMPLOYEE_FATALITIES";
    public static final String COL_NON_EMPLOYEE_INJURIES = "NON_EMPLOYEE_INJURIES";
    public static final String COL_GAS_IGNITED = "GAS_IGNITED";
    public static final String COL_EXPLOSION_OCCURED = "EXPLOSION_OCCURED";
    public static final String COL_SECONDARY_EXPLOSIONS_FIRE = "SECONDARY_EXPLOSIONS_FIRE";
    public static final String COL_DISTANCE_SEWER_STORM_CONT = "DISTANCE_SEWER_STORM_CONT";
    public static final String COL_DISTANCE_SEWER_STORM_IMPA = "DISTANCE_SEWER_STORM_IMPA";
    public static final String COL_DISTANCE_SEWER_OTHER_CONT = "DISTANCE_SEWER_OTHER_CONT";
    public static final String COL_DISTANCE_SEWER_OTHER_IMPA = "DISTANCE_SEWER_OTHER_IMPA";
    public static final String COL_DISTANCE_WATER_CONTR = "DISTANCE_WATER_CONTR";
    public static final String COL_DISTANCE_WATER_IMPAIRED = "DISTANCE_WATER_IMPAIRED";
    public static final String COL_LOCATION_LEAK = "LOCATION_LEAK";
    public static final String COL_LOCATION_LEAK_OTHER = "LOCATION_LEAK_OTHER";
    public static final String COL_COVER_DEPTH = "COVER_DEPTH";
    public static final String COL_SOIL_INFORMATION = "SOIL_INFORMATION";
    public static final String COL_SOIL_TEMPERATURE = "SOIL_TEMPERATURE";
    public static final String COL_REPORTED_BY = "REPORTED_BY";
    public static final String COL_REPORTED_BY_OTHER = "REPORTED_BY_OTHER";
    public static final String COL_MATERIAL_LEAKED_D = "MATERIAL_LEAKED_D";
    public static final String COL_MATERIAL_LEAKED_D_OTHER = "MATERIAL_LEAKED_D_OTHER";
    public static final String COL_LOCATION_CORROSION = "LOCATION_CORROSION";
    public static final String COL_DESCRIPTION_CORROSION = "DESCRIPTION_CORROSION";
    public static final String COL_CAUSE_CORROSION = "CAUSE_CORROSION";
    public static final String COL_CAUSE_CORROSION_OTHER = "CAUSE_CORROSION_OTHER";
    public static final String COL_PH_SOIL = "PH_SOIL";
    public static final String COL_SOIL_RESISTIVITY = "SOIL_RESISTIVITY";
    public static final String COL_SOIL_TEST_YEAR = "SOIL_TEST_YEAR";
    public static final String COL_PIPE_SOIL_POTENTIAL_1 = "PIPE_SOIL_POTENTIAL_1";
    public static final String COL_PIPE_SOIL_POTENTIAL_2 = "PIPE_SOIL_POTENTIAL_2";
    public static final String COL_CAUSE_LEAK = "CAUSE_LEAK";
    public static final String COL_CAUSE_LEAK_OTHER = "CAUSE_LEAK_OTHER";
    public static final String COL_LAT = "LATITUDE";
    public static final String COL_LONG = "LONGITUDE";

    public static final String[] allColumns = {COL_ID, COL_INCIDENT_ID, COL_OP, COL_OP_CODE, COL_CAUSE, COL_STATE, COL_ZIP,
            COL_ADDR, COL_CITY, COL_COUNTY, COL_INCIDENT_DATE, COL_DETECTION_TIME, COL_ORIGIN_LEAK, COL_ORIGIN_LEAK_OTHER,
            COL_FATALITIES, COL_INJURIES, COL_EMPLOYEE_FATALITIES, COL_EMPLOYEE_INJURIES, COL_NON_EMPLOYEE_FATALITIES,
            COL_NON_EMPLOYEE_INJURIES, COL_GAS_IGNITED, COL_EXPLOSION_OCCURED, COL_SECONDARY_EXPLOSIONS_FIRE, COL_DISTANCE_SEWER_STORM_CONT,
            COL_DISTANCE_SEWER_STORM_IMPA, COL_DISTANCE_SEWER_OTHER_CONT, COL_DISTANCE_SEWER_OTHER_IMPA, COL_DISTANCE_WATER_CONTR,
            COL_DISTANCE_WATER_IMPAIRED, COL_LOCATION_LEAK, COL_LOCATION_LEAK_OTHER, COL_COVER_DEPTH, COL_SOIL_INFORMATION,
            COL_SOIL_TEMPERATURE, COL_REPORTED_BY, COL_REPORTED_BY_OTHER, COL_MATERIAL_LEAKED_D, COL_MATERIAL_LEAKED_D_OTHER,
            COL_LOCATION_CORROSION, COL_DESCRIPTION_CORROSION, COL_CAUSE_CORROSION, COL_CAUSE_CORROSION_OTHER, COL_PH_SOIL,
            COL_SOIL_RESISTIVITY, COL_SOIL_TEST_YEAR, COL_PIPE_SOIL_POTENTIAL_1, COL_PIPE_SOIL_POTENTIAL_2, COL_CAUSE_LEAK,
            COL_CAUSE_LEAK_OTHER, COL_LAT, COL_LONG};

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COL_ID + " INTEGER PRIMARY KEY," +
                    COL_INCIDENT_ID + " INTEGER" + COMMA_SEP +
                    COL_OP + TEXT_TYPE + COMMA_SEP +
                    COL_CAUSE + TEXT_TYPE + COMMA_SEP +
                    COL_STATE + TEXT_TYPE + COMMA_SEP +
                    COL_ZIP + TEXT_TYPE + COMMA_SEP +
                    COL_ADDR + TEXT_TYPE + COMMA_SEP +
                    COL_CITY + TEXT_TYPE + COMMA_SEP +
                    COL_COUNTY + TEXT_TYPE + COMMA_SEP +
                    COL_LAT + " REAL" + COMMA_SEP +
                    COL_LONG + " REAL" + COMMA_SEP +
                    COL_INCIDENT_DATE+ TEXT_TYPE + COMMA_SEP +
                    COL_DETECTION_TIME+ TEXT_TYPE + COMMA_SEP +
                    COL_ORIGIN_LEAK+ TEXT_TYPE + COMMA_SEP +
                    COL_ORIGIN_LEAK_OTHER+ TEXT_TYPE + COMMA_SEP +
                    COL_FATALITIES+ TEXT_TYPE + COMMA_SEP +
                    COL_INJURIES+ TEXT_TYPE + COMMA_SEP +
                    COL_EMPLOYEE_FATALITIES+ TEXT_TYPE + COMMA_SEP +
                    COL_EMPLOYEE_INJURIES+ TEXT_TYPE + COMMA_SEP +
                    COL_NON_EMPLOYEE_FATALITIES+ TEXT_TYPE + COMMA_SEP +
                    COL_NON_EMPLOYEE_INJURIES+ TEXT_TYPE + COMMA_SEP +
                    COL_GAS_IGNITED+ TEXT_TYPE + COMMA_SEP +
                    COL_EXPLOSION_OCCURED+ TEXT_TYPE + COMMA_SEP +
                    COL_SECONDARY_EXPLOSIONS_FIRE+ TEXT_TYPE + COMMA_SEP +
                    COL_DISTANCE_SEWER_STORM_CONT+ TEXT_TYPE + COMMA_SEP +
                    COL_DISTANCE_SEWER_STORM_IMPA+ TEXT_TYPE + COMMA_SEP +
                    COL_DISTANCE_SEWER_OTHER_CONT+ TEXT_TYPE + COMMA_SEP +
                    COL_DISTANCE_SEWER_OTHER_IMPA+ TEXT_TYPE + COMMA_SEP +
                    COL_DISTANCE_WATER_CONTR+ TEXT_TYPE + COMMA_SEP +
                    COL_DISTANCE_WATER_IMPAIRED+ TEXT_TYPE + COMMA_SEP +
                    COL_LOCATION_LEAK+ TEXT_TYPE + COMMA_SEP +
                    COL_LOCATION_LEAK_OTHER+ TEXT_TYPE + COMMA_SEP +
                    COL_COVER_DEPTH+ TEXT_TYPE + COMMA_SEP +
                    COL_SOIL_INFORMATION+ TEXT_TYPE + COMMA_SEP +
                    COL_SOIL_TEMPERATURE+ TEXT_TYPE + COMMA_SEP +
                    COL_REPORTED_BY+ TEXT_TYPE + COMMA_SEP +
                    COL_REPORTED_BY_OTHER+ TEXT_TYPE + COMMA_SEP +
                    COL_MATERIAL_LEAKED_D+ TEXT_TYPE + COMMA_SEP +
                    COL_MATERIAL_LEAKED_D_OTHER+ TEXT_TYPE + COMMA_SEP +
                    COL_LOCATION_CORROSION+ TEXT_TYPE + COMMA_SEP +
                    COL_DESCRIPTION_CORROSION+ TEXT_TYPE + COMMA_SEP +
                    COL_CAUSE_CORROSION+ TEXT_TYPE + COMMA_SEP +
                    COL_CAUSE_CORROSION_OTHER+ TEXT_TYPE + COMMA_SEP +
                    COL_PH_SOIL+ TEXT_TYPE + COMMA_SEP +
                    COL_SOIL_RESISTIVITY+ TEXT_TYPE + COMMA_SEP +
                    COL_SOIL_TEST_YEAR+ TEXT_TYPE + COMMA_SEP +
                    COL_PIPE_SOIL_POTENTIAL_1+ TEXT_TYPE + COMMA_SEP +
                    COL_PIPE_SOIL_POTENTIAL_2+ TEXT_TYPE + COMMA_SEP +
                    COL_CAUSE_LEAK+ TEXT_TYPE + COMMA_SEP +
                    COL_CAUSE_LEAK_OTHER+ TEXT_TYPE + COMMA_SEP +
                    COL_OP_CODE + TEXT_TYPE  +
            " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public IncidentDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}