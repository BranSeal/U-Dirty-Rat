package teamminus1.udirtyrat.models;

/**
 * Information holder responsible for admin data.
 */

public class Admin implements IUser {
    private String username;
    private String password;
    private String token;

    /**
     * Class constructor
     * @param username login username for admin
     * @param password password for admin
     */
    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Admin username getter method
     * @return admin's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Admin password getter method
     * @return admin's password
     */
    public String getPassword() {
        return password;
    }

    public String getToken() {  return token; }

    public String setToken(String newtoken) { this.token = newtoken; return this.token; }

}
