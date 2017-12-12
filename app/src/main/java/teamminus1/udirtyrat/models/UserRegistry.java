package teamminus1.udirtyrat.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to handle different users within the database
 */

public final class UserRegistry {

    private static class InstanceHolder {
        private static final UserRegistry instance = new UserRegistry();
    }

    /**
     * Instance method for UserRegistry
     * @return instance of user registry
     */
    public static UserRegistry Instance() {
        return InstanceHolder.instance;
    }

    private final List<IUser> userList;

    /**
     * Class constructor
     */
    private UserRegistry() {
        userList = new ArrayList<>();
        userList.add(new User("user", "pass"));
    }

    /**
     * Verifies the status of user within the database
     * @param username user's username
     * @return Whether the user is within the database already
     */
    public boolean containsUser(String username)  {
        for (IUser user : userList) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retrieves user information from the database
     * @param username user's username
     * @return user from database
     * @exception IllegalArgumentException user isn't found
     */
    public IUser getUser(String username) {
        for (IUser user : userList) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        throw new IllegalArgumentException("There is no user with the username " + username + ".");
    }

    /**
     * Adds user to the database
     * @param user user to be added
     */
    public void AddUser(IUser user) {
        userList.add(user);
    }

    /**
     * Gets all the users from user registry
     * @return returns user registry List
     */
    List<IUser> AllUsers() {
        return userList;
    }

    /**
     * Adds a list of users to the user registry
     * @param users list of users
     */
    void addUsersFromFile(List<IUser> users) {
        for (IUser u: users) {
            userList.add(u);
        }
    }

}
