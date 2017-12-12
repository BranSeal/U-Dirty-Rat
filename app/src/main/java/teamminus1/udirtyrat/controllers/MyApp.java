package teamminus1.udirtyrat.controllers;

import android.util.Log;

import android.app.Application;

import teamminus1.udirtyrat.models.Model;

/**
 * Sets up persistence for rat sightings and users
 */

public class MyApp extends Application {

    /**
     * Class constructor
     */
    public MyApp() {
        // this method fires only once per application start.
        // getApplicationContext returns null here

        Log.i("main", "Constructor fired");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Model.Instance().readRatsFromFile(getApplicationContext());
        Model.Instance().readUsersFromFile(getApplicationContext());
        CSVReader.LoadFromCSVFile(getResources());

    }
}
