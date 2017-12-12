package teamminus1.udirtyrat.models;

import android.content.Context;

import java.util.ArrayList;

/**
 * Class that manages behavior of the problem domain, managing data, logic, and rules of the app.
 */

public class Model {

    public static final String DATE_STRING_FORMAT = "MM/dd/yyyy hh:mm";
    public static final String RAT_FILE_NAME = "RATS.ser";
    public static final String USER_FILE_NAME = "USERS.ser";
    private static boolean loadedFromFile = false;

    private static class InstanceHolder {
        private static final Model instance = new Model();
    }

    /**
     * A singleton instance of the Model object.
     * @return a Model object
     */
    public static Model Instance() {
        return InstanceHolder.instance;
    }

    private ArrayList<RatSighting> filteredSightings;

    /**
     * Gets filtered rat sightings
     * @return ArrayList of filtered rat sightings
     */
    public ArrayList<RatSighting> getFilteredSightings() {
        return filteredSightings;
    }

    private IUser currentUser;

    /**
     * Getter method for the current user of the instance
     * @return current user
     */
    public IUser getCurrentUser() {
        return currentUser;
    }

    //package private - only LoginHandler should probably use this

    /**
     * Setter method for current user in the instance
     * @param user user to be set to current
     */
    void setCurrentUser(IUser user) {
        this.currentUser = user;
    }

    private boolean loggedIn;

    /**
     * Method to return the state of the user login.
     * @return true for logged in, false for logged out
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    //package private - only LoginHandler should probably use this

    /**
     * Update the status for the state of the user login
     * @param loggedIn true for logged in, false for logged out
     */
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    /**
     * Method for registering a user into the system
     * @param username user's username
     * @param password user's password
     * @param isAdmin user's admin status
     * @return registration result of the registration attempt
     */
    public RegistrationResult RegisterUser(String username, String password, boolean isAdmin) {
        return RegistrationHandler.RegisterUser(username, password, isAdmin);
    }

    /**
     * Method for logging a user into the system
     * @param username user's username
     * @param password user's password
     * @return login result of the login attempt
     */
    public LoginResult LoginUser(String username, String password) {
        return LoginHandler.Login(username, password);
    }

    /**
     * Logs out the current user
     * @return successfully logged out
     */
    public boolean Logout() {
        return LoginHandler.Logout();
    }

    /**
     * Filters the rat sightings given a filter
     * @param filter rat sighting filter
     */
    public void filterSightings(RatSightingFilter filter) {
        filteredSightings = RatRegistry.Instance().getFilteredSightings(filter);
    }

    /**
     * Reads rats from file
     * @param context given context of the app
     */
    public void readRatsFromFile(Context context) {
        TempSerializationHandler t = new TempSerializationHandler(context);
        t.ReadRatsFromFile();
    }

    /**
     * Sets the state of being loaded from file
     */
    public static void setLoadedFromFileToTrue() {
        loadedFromFile = true;
    }

    /**
     * Checks if loaded from file or not
     * @return whether loaded from file or not
     */
    public boolean isLoadedFromFile() {
        return loadedFromFile;
    }

    /**
     * Writes rats to file
     * @param context given context of the app
     */
    public void writeRatsToFile(Context context) {
        TempSerializationHandler t = new TempSerializationHandler(context);
        t.WriteRatsToFile();
    }

    /**
     * Reads users from file
     * @param context given context of the app
     */
    public void readUsersFromFile(Context context) {
        TempSerializationHandler t = new TempSerializationHandler(context);
        t.ReadUsersFromFile();
    }

    /**
     * Writes users to file
     * @param context given context of the app
     */
    public void writeUsersToFile(Context context) {
        TempSerializationHandler t = new TempSerializationHandler(context);
        t.WriteUsersToFile();
    }
}
