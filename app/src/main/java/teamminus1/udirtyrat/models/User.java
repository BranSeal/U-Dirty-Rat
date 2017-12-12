package teamminus1.udirtyrat.models;

/**
 * Information holder for default user type
 */

public class User implements IUser {
    private String username;
    private String password;
    private String token;

    /**
     * Class constructor
     * @param username user's username
     * @param password user's password
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * User username getter method
     * @return user's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * User password getter method
     * @return user's password
     */
    public String getPassword() {
        return password;
    }

    public String getToken() {  return token; }

    public String setToken(String newtoken) { this.token = newtoken; return this.token; }

}
