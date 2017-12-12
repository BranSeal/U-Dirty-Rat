package teamminus1.udirtyrat.models;
import  java.lang.String;

/**
 * Class to handle the different types of registration results when a registration is attempted
 */

class RegistrationHandler {
    /**
     * Checks the system to see if a user already exists in the database and then handles the result
     * @param username user's username
     * @param password user's password
     * @param isAdmin user's admin status
     * @return the type of result when a registration is attempted
     */
    static RegistrationResult RegisterUser(String username, String password, Boolean isAdmin) {
          if (username.trim().isEmpty()) {
              return RegistrationResult.EMPTY_USER;
          } else if (password.trim().isEmpty()) {
              return RegistrationResult.EMPTY_PW;
          } else if (UserRegistry.Instance().containsUser(username)) {
              return RegistrationResult.USERNAME_ALREADY_EXISTS;
          } else {
              if (isAdmin) {
                  Admin newAdmin = new Admin(username, password);
                  UserRegistry.Instance().AddUser(newAdmin);
              } else {
                  User newUser = new User(username, password);
                  UserRegistry.Instance().AddUser(newUser);
              }
              return RegistrationResult.REGISTRATION_SUCCEEDED;
          }
      }
}
