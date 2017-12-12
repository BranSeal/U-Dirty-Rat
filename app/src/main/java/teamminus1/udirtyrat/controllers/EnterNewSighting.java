package teamminus1.udirtyrat.controllers;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.Calendar;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;


import teamminus1.udirtyrat.R;
import teamminus1.udirtyrat.models.Model;
import teamminus1.udirtyrat.models.RatRegistry;
import teamminus1.udirtyrat.models.RatSighting;

/**
 * Class to handle the addition of a new rat sightings
 */

public class EnterNewSighting extends AppCompatActivity implements View.OnClickListener{

    private Spinner spinnerLocation;
    private Spinner spinnerBorough;
    private EditText Longitude;
    private EditText Latitude;
    private EditText zip;
    private EditText address;
    private EditText city;
    private double LatCoordinate;
    private double LonCoordinate;
    private int uniqueKey;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_new_sighting);

        Longitude = findViewById(R.id.editLongitude);
        Latitude = findViewById(R.id.editLatitude);

        zip = findViewById(R.id.editIncidentZip);
        address = findViewById(R.id.editIncidentAddress);
        city = findViewById(R.id.editIncidentCity);

        //---------------- cancel button events --------------//
        Button cancel_button = findViewById(R.id.buttonCancelSighting);
        cancel_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EnterNewSighting.this, Home.class);
                startActivity(intent);
                finish();
            }
        });


        //------ submit button events -----------//
        Button submit_button = findViewById(R.id.buttonSubmitSighting);
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("New Sighting Activity", "Pressed the submit button");
                AddRightSightingIfConfirmed();
            }
        });

        // spinner for "Location Types" and event listeners
        addItemsOnSpinnerLocationType();

        // spinner for "Borough" and event listeners
        addItemsOnSpinnerBorough();
    }


     /**
     * adds items into spinner dynamically
     */
    private void addItemsOnSpinnerLocationType() {

        spinnerLocation = findViewById(R.id.spinnerLocationType);
        List<String> list = new ArrayList<>();
        list.add("1-2 Family Dwelling");
        list.add("3+ Family Apt. Building");
        list.add("3+ Family Mixed Use Building");
        list.add("Commercial Building" );
        list.add("Vacant Lot" );
        list.add("Construction Site" );
        list.add("Hospital");
        list.add("Catch Basin/Sewer" );


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLocation.setAdapter(dataAdapter);
    }

    /**
     * adds items into spinner dynamically
     */
    private void addItemsOnSpinnerBorough() {

        spinnerBorough = findViewById(R.id.spinnerBorough);
        List<String> list = new ArrayList<>();
        list.add("MANHATTAN");
        list.add("STATEN ISLAND");
        list.add("QUEENS");
        list.add("BROOKLYN" );
        list.add("BRONX" );

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBorough.setAdapter(dataAdapter);
    }

    /**
     * Determines if user desires to create Rat Sighting and creates it if so
     */
    private void AddRightSightingIfConfirmed (){

        //boolean success = false;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Confirm");
        builder.setMessage("Are you ready to add this Rat Sighting?");

        //--- if YES, then add Rat Sighting
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                uniqueKey = (int) System.currentTimeMillis();

                String selectedLocationType = spinnerLocation.getSelectedItem().toString();
                String selectedBorough = spinnerBorough.getSelectedItem().toString();
                String selectedZip = zip.getText().toString();
                String selectedAddress = address.getText().toString();
                String selectedCity = city.getText().toString();


                try
                {
                    LatCoordinate = Double.parseDouble( Latitude.getText().toString() );
                }
                catch(NumberFormatException e) { /*not a double*/ }

                try
                {
                    LonCoordinate = Double.parseDouble( Longitude.getText().toString() );
                }
                catch(NumberFormatException e) { /*not a double*/ }




                RatRegistry instance = RatRegistry.Instance();
                instance.addRatSighting(new RatSighting(
                        uniqueKey, // Unique key
                        new Date(), //formattedDateTime // Created Date
                        selectedLocationType, // Location Type
                        selectedZip, //Location Zip
                        selectedAddress, // Incident Address
                        selectedCity, // City
                        selectedBorough, // Borough
                        LatCoordinate, // Latitude
                        LonCoordinate // Longitude
                ));

                Model.Instance().writeRatsToFile(getApplicationContext());

                Intent intent = new Intent(EnterNewSighting.this, Home.class);
                startActivity(intent);
                finish();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // dismiss activity
                finish();
            }
        });

        AlertDialog alert = builder.create();
        alert.setCancelable(false);
        alert.setCanceledOnTouchOutside(false);
        alert.show();
             try{ Looper.loop(); } //--- dialog won't stay up waiting for input without this
        catch(RuntimeException e){System.out.println("Popup failed");}
    }


    @Override
    public void onClick(View view) {
    }

}


