package teamminus1.udirtyrat.models;

/**
 * Enum to hold the different types of registration results when a registration is attempted.
 */

public enum RegistrationResult {
    REGISTRATION_SUCCEEDED(true, "Registration successful"),
    USERNAME_ALREADY_EXISTS(false, "That username is already taken."),
    EMPTY_USER(false, "username cannot be empty."),
    EMPTY_PW(false, "password cannot be empty.");

    //this boolean represents whether a new account was successfully created or not.
    private final boolean success;
    private final String defaultMessage;

    /**
     * Enum constructor
     * @param success status of registration attempt
     * @param defaultErrorMessage message that will be read to user
     */
    RegistrationResult(boolean success, String defaultErrorMessage) {
        this.success = success;
        this.defaultMessage = defaultErrorMessage;
    }

    /**
     * Returns whether the current registration result was a success or not
     * @return success or no success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Getter for the default message of the current registration result
     * @return default message
     */
    public String getDefaultMessage() {
        return defaultMessage;
    }
}
