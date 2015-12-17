package com.caci.mapsample;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class IncidentDetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incident_detail);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Long incidentId = extras.getLong("incidentId");
            Incident i  = MainActivity.incidentDataSource.getIncident(incidentId);

            TextView summary = (TextView) findViewById(R.id.detail_lbl_summary);
            summary.setText(summary.getText()+" "+i.reportId);

            setupDetailGroup((TextView) findViewById(R.id.detail_lbl_operator), (TextView) findViewById(R.id.detail_txt_operator), i.operator);
            setupDetailGroup((TextView) findViewById(R.id.detail_lbl_op_code), (TextView) findViewById(R.id.detail_txt_op_code), i.operatorCode);
            setupDetailGroup((TextView) findViewById(R.id.detail_lbl_cause), (TextView) findViewById(R.id.detail_txt_cause), i.cause);
            setupDetailGroup((TextView) findViewById(R.id.detail_lbl_addr), (TextView) findViewById(R.id.detail_txt_addr), i.address);
            setupDetailGroup((TextView) findViewById(R.id.detail_lbl_city), (TextView) findViewById(R.id.detail_txt_city), i.city+ ", "+ i.state);
            setupDetailGroup((TextView) findViewById(R.id.detail_lbl_date), (TextView) findViewById(R.id.detail_txt_date), i.date);
            setupDetailGroup((TextView) findViewById(R.id.detail_lbl_time), (TextView) findViewById(R.id.detail_txt_time), i.detectionTime);
            setupDetailGroup((TextView) findViewById(R.id.detail_lbl_origin_leak), (TextView) findViewById(R.id.detail_txt_origin_leak), i.originLeak);
            setupDetailGroup((TextView) findViewById(R.id.detail_lbl_fat), (TextView) findViewById(R.id.detail_txt_fat), i.fatalities);
            setupDetailGroup((TextView) findViewById(R.id.detail_lbl_inj), (TextView) findViewById(R.id.detail_txt_inj), i.injuries);
            setupDetailGroup((TextView) findViewById(R.id.detail_lbl_ignition), (TextView) findViewById(R.id.detail_txt_ignition), i.gasIgnited);
            setupDetailGroup((TextView) findViewById(R.id.detail_lbl_explosion), (TextView) findViewById(R.id.detail_txt_explosion), i.explosionOccured);
            setupDetailGroup((TextView) findViewById(R.id.detail_lbl_dist_sewer_cont), (TextView) findViewById(R.id.detail_txt_dist_sewer_cont), i.distanceStormCont);
            setupDetailGroup((TextView) findViewById(R.id.detail_lbl_dist_sewer_impa), (TextView) findViewById(R.id.detail_txt_dist_sewer_impa), i.distanceSewerOtherImp);
            setupDetailGroup((TextView) findViewById(R.id.detail_lbl_dist_water), (TextView) findViewById(R.id.detail_txt_dist_water), i.distanceWaterContr);
            setupDetailGroup((TextView) findViewById(R.id.detail_lbl_dist_water_imp), (TextView) findViewById(R.id.detail_txt_dist_water_imp), i.distanceWaterImpaired);
            setupDetailGroup((TextView) findViewById(R.id.detail_lbl_loc_leak), (TextView) findViewById(R.id.detail_txt_loc_leak), i.locationLeak);
            setupDetailGroup((TextView) findViewById(R.id.detail_lbl_cover_depth), (TextView) findViewById(R.id.detail_txt_cover_depth), i.coverDepth);
            setupDetailGroup((TextView) findViewById(R.id.detail_lbl_soil_info), (TextView) findViewById(R.id.detail_txt_soil_info), i.soilInformation);
            setupDetailGroup((TextView) findViewById(R.id.detail_lbl_soil_temp), (TextView) findViewById(R.id.detail_txt_soil_temp), i.soilTemp);
            setupDetailGroup((TextView) findViewById(R.id.detail_lbl_soil_ph), (TextView) findViewById(R.id.detail_txt_soil_ph), i.phSoil);
            setupDetailGroup((TextView) findViewById(R.id.detail_lbl_soil_resist), (TextView) findViewById(R.id.detail_txt_soil_resist), i.soilResist);
            setupDetailGroup((TextView) findViewById(R.id.detail_lbl_soil_test_year), (TextView) findViewById(R.id.detail_txt_soil_test_year), i.soilTestYear);
            setupDetailGroup((TextView) findViewById(R.id.detail_lbl_rpt_by), (TextView) findViewById(R.id.detail_txt_rpt_by), i.reportedBy);
            setupDetailGroup((TextView) findViewById(R.id.detail_lbl_mat_leaked), (TextView) findViewById(R.id.detail_txt_mat_leaked), i.materialLeaked);
            setupDetailGroup((TextView) findViewById(R.id.detail_lbl_loc_corr), (TextView) findViewById(R.id.detail_txt_loc_corr), i.locCorrosion);
            setupDetailGroup((TextView) findViewById(R.id.detail_lbl_desc_corr), (TextView) findViewById(R.id.detail_txt_desc_corr), i.descCorrosion);
            setupDetailGroup((TextView) findViewById(R.id.detail_lbl_cause_corr), (TextView) findViewById(R.id.detail_txt_cause_corr), i.causeCorrosion);
            setupDetailGroup((TextView) findViewById(R.id.detail_lbl_soil_pot1), (TextView) findViewById(R.id.detail_txt_soil_pot1), i.pipeSoilPotential1);
            setupDetailGroup((TextView) findViewById(R.id.detail_lbl_soil_pot2), (TextView) findViewById(R.id.detail_txt_soil_pot2), i.pipeSoilPotential2);
            setupDetailGroup((TextView) findViewById(R.id.detail_lbl_cause_leak), (TextView) findViewById(R.id.detail_txt_cause_leak), i.causeLeak);
        }
    }

    private void setupDetailGroup(TextView txtLabel, TextView txtValue, String value) {
        txtLabel.setText(txtLabel.getText()+":");
        txtValue.setText(" "+value);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_incident_detail, menu);
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
}
