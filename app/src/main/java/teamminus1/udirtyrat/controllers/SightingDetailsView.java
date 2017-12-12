package teamminus1.udirtyrat.controllers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import teamminus1.udirtyrat.R;
import teamminus1.udirtyrat.models.RatRegistry;
import teamminus1.udirtyrat.models.RatSighting;

/**
 * Activity controller for the details of a rat sighting
 */

public class SightingDetailsView extends AppCompatActivity {

    public static final String ARG_UNIQUE_KEY = "unique_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sighting_details_view);

        TextView textKey= findViewById(R.id.DetailedViewUniqueKey);
        TextView textDate = findViewById(R.id.DetailedViewCreatedDate);
        TextView textType = findViewById(R.id.DetailedViewLocationType);
        TextView textZip = findViewById(R.id.DetailedViewZip);
        TextView textAddress = findViewById(R.id.DetailedViewAddress);
        TextView textCity = findViewById(R.id.DetailedViewCity);
        TextView textBorough = findViewById(R.id.DetailedViewBorough);
        TextView textLat = findViewById(R.id.DetailedViewLatitude);
        TextView textLong = findViewById(R.id.DetailedViewLongitude);

        int unique_key =  getIntent().getIntExtra(ARG_UNIQUE_KEY, 0);
        RatSighting sighting = RatRegistry.Instance().getRatSighting(unique_key);
        textKey.setText(Integer.toString(sighting.getUniqueKey()));
        textDate.setText(sighting.getCreatedDateAsString());
        textType.setText(sighting.getLocationType());
        textZip.setText(sighting.getIncidentZip());
        textAddress.setText(sighting.getIncidentAddress());
        textCity.setText(sighting.getCity());
        textBorough.setText(sighting.getBorough());
        textLat.setText(Double.toString(sighting.getLatitude()));
        textLong.setText(Double.toString(sighting.getLongitude()));
    }
}
