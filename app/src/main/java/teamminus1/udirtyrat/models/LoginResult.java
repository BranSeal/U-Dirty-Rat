package teamminus1.udirtyrat.models;

/**
 * Enum to hold the different types of login results when a login is attempted.
 */

public enum LoginResult {
    LOGIN_SUCCEEDED(true, "Login successful"),
    USERNAME_DOES_NOT_EXIST(false, "There is no account associated with that username."),
    PASSWORD_INCORRECT(false, "That is not the correct password.");

    //This boolean represents whether the user successfully logged in.
    private final boolean success;
    private final String defaultMessage;

    /**
     * Enum constructor
     * @param success status of login attempt
     * @param defaultMessage message that will be read to user
     */
    LoginResult(boolean success, String defaultMessage) {
        this.success = success;
        this.defaultMessage = defaultMessage;
    }

    /**
     * Returns whether the current login result was a success or not
     * @return success or no success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Getter for the default message of the current login result
     * @return default message
     */
    public String getDefaultMessage() {
        return defaultMessage;
    }
}
