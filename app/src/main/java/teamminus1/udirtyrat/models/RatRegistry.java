package teamminus1.udirtyrat.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to handle different rat sightings within the database as well as the database itself.
 */

public final class RatRegistry {

    private static class InstanceHolder {
        private static final RatRegistry instance = new RatRegistry();
    }

    /**
     * Instance method for RatRegistry
     * @return instance of RatRegistry
     */
    public static RatRegistry Instance() {
        return InstanceHolder.instance;
    }

    private final List<RatSighting> ratSightingList;

    /**
     * Class constructor
     */
    private RatRegistry() {
        ratSightingList = new ArrayList<>();
    }

    /**
     * Getter method for rat sighting based on its unique key
     * @param key unique key of the rat sighting
     * @return Specific rat sighting associated with unique key
     */
    public RatSighting getRatSighting(int key) {
        for (int i = 0; i < ratSightingList.size(); i++) {
            if (ratSightingList.get(i).getUniqueKey() == key) {
                return ratSightingList.get(i);
            }
        }
        return null;
    }

    /**
     * Gets all the rat sightings in the form of a list
     * @return rat sightings list
     */
    public List<RatSighting> allRatSightings() {
        return ratSightingList;
    }

    /**
     * Gets the filtered rat sightings from the rat registry
     * @param filter rat sighting filter to filter rats
     * @return a filtered ArrayList of rat sightings
     */
    public ArrayList<RatSighting> getFilteredSightings(RatSightingFilter filter) {
        ArrayList<RatSighting> filteredSightings = new ArrayList<>();
        for (RatSighting rs : ratSightingList) {
            if (filter.passesFilter(rs)) {
                filteredSightings.add(rs);
            }
        }
        return filteredSightings;
    }

    /**
     * Adds a rat sighting to the rat list
     * @param ratSighting rat sighting to be added to sighting list
     */
    public void addRatSighting(RatSighting ratSighting) {
        ratSightingList.add(ratSighting);
    }

    /**
     * Adds rats from file to the registry
     * @param sightings list of rat sightings
     */
    public void addRatsFromFile(List<RatSighting> sightings) {
        //This is a separate method so that we don't bother writing these back into the file.
        for (RatSighting s : sightings) {
            ratSightingList.add(s);
        }
    }
}
