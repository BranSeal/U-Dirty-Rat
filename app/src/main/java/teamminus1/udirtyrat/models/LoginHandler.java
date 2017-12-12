package teamminus1.udirtyrat.models;

/**
 * Class used to determine the result of a login attempt and logout the current user
 */

public class LoginHandler {
    /**
     * Logs in the user
     * @param username the username of the requesting user
     * @param password the password of the requesting user
     * @return the result of the attempted login
     */
    public static LoginResult Login(String username, String password) {
        if (UserRegistry.Instance().containsUser(username)) {
            IUser user = UserRegistry.Instance().getUser(username);
            if (user.getPassword().equals(password)) {
                Model.Instance().setCurrentUser(user);
                return LoginResult.LOGIN_SUCCEEDED;
            }
            else if (user.getToken().equals(password)) { //----- token is only set if user uses the 'forgot password' feature
                Model.Instance().setCurrentUser(user);
                return LoginResult.LOGIN_SUCCEEDED;
            }
            else {
                return LoginResult.PASSWORD_INCORRECT;
            }
        }
        return LoginResult.USERNAME_DOES_NOT_EXIST;
    }

    /**
     * Logs out the current user in the instance
     * @return successful logout
     */
    public static boolean Logout() {
        Model.Instance().setCurrentUser(null);
        Model.Instance().setLoggedIn(false);
        return true;
    }
}
