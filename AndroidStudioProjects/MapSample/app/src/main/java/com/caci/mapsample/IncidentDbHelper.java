package com.caci.mapsample;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
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
    public static final String COL_LAT = "LATITUDE";
    public static final String COL_LONG = "LONGITUDE";

    public static final String[] allColumns = {COL_ID, COL_INCIDENT_ID, COL_OP, COL_OP_CODE, COL_CAUSE, COL_STATE, COL_ZIP,
            COL_ADDR, COL_CITY, COL_COUNTY, COL_LAT, COL_LONG};

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