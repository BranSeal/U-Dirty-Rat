package teamminus1.udirtyrat.controllers;

import android.content.res.Resources;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Date;

import teamminus1.udirtyrat.R;
import teamminus1.udirtyrat.models.Model;
import teamminus1.udirtyrat.models.RatRegistry;
import teamminus1.udirtyrat.models.RatSighting;

/**
 * This CSVReader class is only here for the purpose of being a placeholder for reading from the
 * CSV file.  After persistence is added with Firebase, this class will not be needed and
 * the code using it will need to be refactored.
 */

final class CSVReader {

    private static CSVReader instance;
    private final Resources res;

    private static final int Max_Number_Sightings = 50;
    private static final int Location_Type = 7;
    private static final int Location_Zip = 8;
    private static final int Address = 9;
    private static final int City = 16;
    private static final int Borough = 23;
    private static final int Latitude = 49;
    private static final int Longitude = 50;

    /**
     * Instance method for CSVReader
     * @param res Needed resource object for consistency in android app
     * @return instance of CSVReader
     */
    public static void LoadFromCSVFile(Resources res) {
        if (instance == null) {
            instance = new CSVReader(res);
        }

    }

    private CSVReader(Resources res) {
        this.res = res;
        if (!Model.Instance().isLoadedFromFile()) {
            readCSVFile();
        }
    }

    private void readCSVFile() {

        int numSightings = 0;

        RatRegistry instance = RatRegistry.Instance();
        try {
            InputStream is = res.openRawResource(R.raw.rat_sightings);
            BufferedReader br = new BufferedReader(new InputStreamReader(is,
                    StandardCharsets.UTF_8));
            String line;
            br.readLine(); //get rid of header line
            line = br.readLine();
            while ((line != null) && (numSightings < Max_Number_Sightings)) {
                String[] tokens = line.split(",");
                if (tokens.length < (Max_Number_Sightings + 1)) {
                    continue;
                }
                Date createdDate;
                try {
                    String[] strings = tokens[1].split("/");
                    int monthPlusOne = Integer.parseInt(strings[0]);
                    int dayOfMonth = Integer.parseInt(strings[1]);
                    String yearAsString = strings[2].substring(0, 4);
                    int year = Integer.parseInt(yearAsString);
                    Calendar myCalendar = Calendar.getInstance();
                    myCalendar.set(Calendar.YEAR, year);
                    myCalendar.set(Calendar.MONTH, monthPlusOne - 1);
                    myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    createdDate = myCalendar.getTime();
                } catch (Exception e){
                    createdDate = new Date();
                }
                instance.addRatSighting(new RatSighting(
                        Integer.parseInt(tokens[0]), // Unique key
                        createdDate, // Created Date
                        tokens[Location_Type], // Location Type
                        tokens[Location_Zip], //Location Zip
                        tokens[Address], // Incident Address
                        tokens[City], // City
                        tokens[Borough], // Borough
                        Double.parseDouble(tokens[Latitude]), // Latitude
                        Double.parseDouble(tokens[Longitude]) // Longitude
                ));
                numSightings++;
                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            Log.e("ViewSightingList", "error reading assets", e);
        }
    }
}