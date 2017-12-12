package teamminus1.udirtyrat.models;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests login
 */
public class LoginHandlerTest {

    /**
     * Tries to log into the system with a valid username and password
     * Result: Returned LoginResult.LOGIN_SUCCEEDED
     */

    @Test
    public void loginSuccessful() {

        String int1 = "user";
        String int2 = "pass";
        LoginResult output;

        LoginResult expected = LoginResult.LOGIN_SUCCEEDED;

        output = LoginHandler.Login(int1, int2);

        assertEquals("Logging into system failed", expected, output);

    }

    /**
     * Tries to log into the system with a valid username but an incorrect password
     * Result: Returned LoginResult.PASSWORD_INCORRECT;
     */

    @Test
    public void loginFailed() {

        String int1 = "user";
        String int2 = "thisIsNotThePassWord";
        LoginResult output;

        LoginResult expected = LoginResult.PASSWORD_INCORRECT;

        output = LoginHandler.Login(int1, int2);

        assertEquals("Check for incorrect password failed", expected, output);

    }

    /**
     * Tries to log into the system with an invalid username and password
     * Result: Returned LoginResult.LOGIN_SUCCEEDED
     */

    @Test
    public void badUsername() {

        String int1 = "blank";
        String int2 = "pass";
        LoginResult output;

        LoginResult expected = LoginResult.USERNAME_DOES_NOT_EXIST;

        output = LoginHandler.Login(int1, int2);

        assertEquals("Username Valid Check has failed", expected, output);

    }

}