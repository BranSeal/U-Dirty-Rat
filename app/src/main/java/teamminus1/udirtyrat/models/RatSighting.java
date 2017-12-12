package teamminus1.udirtyrat.models;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Information holder for a rat sighting incident a user has entered into the system
 */
public class RatSighting implements Serializable {
    private final int uniqueKey;
    private final Date createdDate;
    private final String locationType;
    private final String incidentZip;
    private final String incidentAddress;
    private final String city;
    private final String borough;
    private final double latitude;
    private final double longitude;


    /**
     * Class constructor
     * @param uniqueKey unique key of rat sighting
     * @param createdDate created date of rat sighting
     * @param locationType location type of rat sighting
     * @param incidentZip incident zip of rat sighting
     * @param incidentAddress incident address of rat sighting
     * @param city city of rat sighting
     * @param borough borough of rat sighting
     * @param latitude latitude of rat sighting
     * @param longitude longitude of rat sighting
     */
    public RatSighting(int uniqueKey, Date createdDate, String locationType, String incidentZip,
                       String incidentAddress, String city, String borough, double latitude,
                       double longitude) {
        this.uniqueKey = uniqueKey;
        this.createdDate = createdDate;
        this.locationType = locationType;
        this.incidentZip = incidentZip;
        this.incidentAddress = incidentAddress;
        this.city = city;
        this.borough = borough;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Getter method for unique key
     * @return unique key
     */
    public int getUniqueKey() {
        return uniqueKey;
    }

    /**
     * Getter method for created date
     * @return created date
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * Converts date into a readable string.
     * @return date as a string
     */
    public String getCreatedDateAsString() {
        DateFormat df = new SimpleDateFormat(Model.DATE_STRING_FORMAT, Locale.US);
        return df.format(createdDate);
    }

    /**
     * Getter method for location type
     * @return location type
     */
    public String getLocationType() {
        return locationType;
    }

    /**
     * Getter method for incident zip
     * @return incident zip
     */
    public String getIncidentZip() {
        return incidentZip;
    }

    /**
     * Getter method for incident address
     * @return incident address
     */
    public String getIncidentAddress() {
        return incidentAddress;
    }

    /**
     * Getter method for city
     * @return city
     */
    public String getCity() {
        return city;
    }

    /**
     * Getter method for borough
     * @return borough
     */
    public String getBorough() {
        return borough;
    }

    /**
     * Getter method for latitude
     * @return latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Getter method for longitude
     * @return longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Print a rat sighting's basic info
     * @return unique key and created date
     */
    public String toString() {
        return uniqueKey + " " + createdDate;
    }
}
