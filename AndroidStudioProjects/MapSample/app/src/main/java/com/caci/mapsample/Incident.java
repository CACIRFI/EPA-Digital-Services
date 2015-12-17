package com.caci.mapsample;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

/**
 * Created by Mike Coddington on 12/6/2015.
 */
public class Incident {
    //REPORT_ID | CAUSE_IDENTIFIER | OPERATOR_CODE | OPERATOR_NAME | INCIDENT_ADDRESS | INCIDENT_CITY | INCIDENT_COUNTY | INCIDENT_STATE | ZIP_CODE | INCIDENT_DATE | DETECTION_TIME | ORIGIN_LEAK | ORIGIN_LEAK_OTHER |Fatalities Count|Injuries count| EMPLOYEE_FATALITIES | EMPLOYEE_INJURIES | NON_EMPLOYEE_FATALITIES | NON_EMPLOYEE_INJURIES | GAS_IGNITED | EXPLOSION_OCCURED | SECONDARY_EXPLOSIONS_FIRE | DISTANCE_SEWER_STORM_CONT | DISTANCE_SEWER_STORM_IMPA | DISTANCE_SEWER_OTHER_CONT | DISTANCE_SEWER_OTHER_IMPA | DISTANCE_WATER_CONTR | DISTANCE_WATER_IMPAIRED | LOCATION_LEAK | LOCATION_LEAK_OTHER | COVER_DEPTH | SOIL_INFORMATION | SOIL_TEMPERATURE | REPORTED_BY | REPORTED_BY_OTHER | MATERIAL_LEAKED_D | MATERIAL_LEAKED_D_OTHER | LOCATION_CORROSION | DESCRIPTION_CORROSION | CAUSE_CORROSION | CAUSE_CORROSION_OTHER | PH_SOIL | SOIL_RESISTIVITY | SOIL_TEST_YEAR | PIPE_SOIL_POTENTIAL_1 | PIPE_SOIL_POTENTIAL_2 | CAUSE_LEAK | CAUSE_LEAK_OTHER

    public Long _id;
    public Long reportId; //REPORT_ID
    public String cause; // CAUSE_IDENTIFIER
    public String operatorCode; //  OPERATOR_CODE
    public String operator; // OPERATOR_NAME
    public String address; //INCIDENT_ADDRESS
    public String city; //INCIDENT_CITY
    public String county;//INCIDENT_COUNTY
    public String state; //INCIDENT_STATE
    public String zip; //ZIP_CODE
    public String date; //INCIDENT_DATE
    public String detectionTime; //DETECTION_TIME
    public String originLeak; // ORIGIN_LEAK
    public String originLeakOther; //ORIGIN_LEAK_OTHER
    public String fatalities; //Fatalities Count
    public String injuries; //Injuries count
    public String empFatalities; //EMPLOYEE_FATALITIES
    public String empInjuries; // | EMPLOYEE_INJURIES
    public String nonEmpFatalities; // | NON_EMPLOYEE_FATALITIES
    public String nonEmpInjuries; // | NON_EMPLOYEE_INJURIES
    public String gasIgnited; // | GAS_IGNITED
    public String explosionOccured; // | EXPLOSION_OCCURED
    public String secondaryExplFire; // | SECONDARY_EXPLOSIONS_FIRE
    public String distanceStormCont;// | DISTANCE_SEWER_STORM_CONT
    public String distanceSewerStorm; // | DISTANCE_SEWER_STORM_IMPA
    public String distanceSewerOther;// | DISTANCE_SEWER_OTHER_CONT
    public String distanceSewerOtherImp; // | DISTANCE_SEWER_OTHER_IMPA
    public String distanceWaterContr;// | DISTANCE_WATER_CONTR
    public String distanceWaterImpaired;// | DISTANCE_WATER_IMPAIRED
    public String locationLeak;// | LOCATION_LEAK
    public String locationLeakOther;// | LOCATION_LEAK_OTHER
    public String coverDepth;// | COVER_DEPTH
    public String soilInformation;// | SOIL_INFORMATION
    public String soilTemp;// | SOIL_TEMPERATURE
    public String reportedBy;// | REPORTED_BY
    public String reportedByOther;// | REPORTED_BY_OTHER
    public String materialLeaked;// | MATERIAL_LEAKED_D
    public String materialLeakedOther;// | MATERIAL_LEAKED_D_OTHER
    public String locCorrosion;// | LOCATION_CORROSION |
    public String descCorrosion;// DESCRIPTION_CORROSION
    public String causeCorrosion;// | CAUSE_CORROSION
    public String causeCorrosionOther;// | CAUSE_CORROSION_OTHER
    public String phSoil;// | PH_SOIL
    public String soilResist;// | SOIL_RESISTIVITY
    public String soilTestYear;// | SOIL_TEST_YEAR
    public String pipeSoilPotential1;// | PIPE_SOIL_POTENTIAL_1
    public String pipeSoilPotential2;// | PIPE_SOIL_POTENTIAL_2
    public String causeLeak;// | CAUSE_LEAK
    public String causeLeakOther;// | CAUSE_LEAK_OTHER

    public double latitude;
    public double longitude;

    public Incident() {}

    public Incident(Long reportId, String cause, String operatorCode, String operator, String address, String city, String county, String state, String zip, String date) {
        this.address = address;
        this.cause = cause;
        this.city = city;
        this.county = county;
        this.date = date;
        this.reportId = reportId;
        this.operator = operator;
        this.operatorCode = operatorCode;
        this.state = state;
        this.zip= zip;
    }


    public Incident(String reportId, String cause, String operatorCode, String operator, String address, String city, String county, String state, String zip, String date, String detectionTime,
                    String originLeak, String originLeakOther, String fatalities, String injuries, String empFatalities, String empInjuries, String nonEmpFatalities, String nonEmpInjuries, String gasIgnited,
                    String explosionOccured, String secondaryExplFire, String distanceStormCont, String distanceSewerStorm, String distanceSewerOther, String distanceSewerOtherImp, String distanceWaterContr,
                    String distanceWaterImpaired, String locationLeak, String locationLeakOther, String coverDepth, String soilInformation, String soilTemp, String reportedBy, String reportedByOther,
                    String materialLeaked, String materialLeakedOther, String locCorrosion, String descCorrosion, String causeCorrosion, String causeCorrosionOther, String phSoil, String soilResist,
                    String soilTestYear, String pipeSoilPotential1, String pipeSoilPotential2, String causeLeak, String causeLeakOther) {
        this.reportId = Long.valueOf(reportId);
        this.cause = cause; // CAUSE_IDENTIFIER
        this.operatorCode = operatorCode; //  OPERATOR_CODE
        this.operator = operator; // OPERATOR_NAME
        this.address = address; //INCIDENT_ADDRESS
        this.city = city; //INCIDENT_CITY
        this.county = county;//INCIDENT_COUNTY
        this.state = state; //INCIDENT_STATE
        this.zip = zip; //ZIP_CODE
        this.date = date; //INCIDENT_DATE
        this.detectionTime = detectionTime; //DETECTION_TIME
        this.originLeak = originLeak; // ORIGIN_LEAK
        this.originLeakOther = originLeakOther; //ORIGIN_LEAK_OTHER
        this.fatalities = fatalities; //Fatalities Count
        this.injuries = injuries; //Injuries count
        this.empFatalities = empFatalities; //EMPLOYEE_FATALITIES
        this.empInjuries = empInjuries; // | EMPLOYEE_INJURIES
        this.nonEmpFatalities = nonEmpFatalities; // | NON_EMPLOYEE_FATALITIES
        this.nonEmpInjuries = nonEmpInjuries; // | NON_EMPLOYEE_INJURIES
        this.gasIgnited = gasIgnited; // | GAS_IGNITED
        this.explosionOccured = explosionOccured; // | EXPLOSION_OCCURED
        this.secondaryExplFire = secondaryExplFire; // | SECONDARY_EXPLOSIONS_FIRE
        this.distanceStormCont = distanceStormCont;// | DISTANCE_SEWER_STORM_CONT
        this.distanceSewerStorm = distanceSewerStorm; // | DISTANCE_SEWER_STORM_IMPA
        this.distanceSewerOther = distanceSewerOther;// | DISTANCE_SEWER_OTHER_CONT
        this.distanceSewerOtherImp = distanceSewerOtherImp; // | DISTANCE_SEWER_OTHER_IMPA
        this.distanceWaterContr = distanceWaterContr;// | DISTANCE_WATER_CONTR
        this.distanceWaterImpaired = distanceWaterImpaired;// | DISTANCE_WATER_IMPAIRED
        this.locationLeak = locationLeak;// | LOCATION_LEAK
        this.locationLeakOther = locationLeakOther;// | LOCATION_LEAK_OTHER
        this.coverDepth = coverDepth;// | COVER_DEPTH
        this.soilInformation = soilInformation;// | SOIL_INFORMATION
        this.soilTemp = soilTemp;// | SOIL_TEMPERATURE
        this.reportedBy = reportedBy;// | REPORTED_BY
        this.reportedByOther = reportedByOther;// | REPORTED_BY_OTHER
        this.materialLeaked = materialLeaked;// | MATERIAL_LEAKED_D
        this.materialLeakedOther = materialLeakedOther;// | MATERIAL_LEAKED_D_OTHER
        this.locCorrosion = locCorrosion;// | LOCATION_CORROSION |
        this.descCorrosion = descCorrosion;// DESCRIPTION_CORROSION
        this.causeCorrosion = causeCorrosion;// | CAUSE_CORROSION
        this.causeCorrosionOther = causeCorrosionOther;// | CAUSE_CORROSION_OTHER
        this.phSoil = phSoil;// | PH_SOIL
        this.soilResist = soilResist;// | SOIL_RESISTIVITY
        this.soilTestYear = soilTestYear;// | SOIL_TEST_YEAR
        this.pipeSoilPotential1 = pipeSoilPotential1;// | PIPE_SOIL_POTENTIAL_1
        this.pipeSoilPotential2 = pipeSoilPotential2;// | PIPE_SOIL_POTENTIAL_2
        this.causeLeak = causeLeak;// | CAUSE_LEAK
        this.causeLeakOther = causeLeakOther;// | CAUSE_LEAK_OTHER
    }

}
