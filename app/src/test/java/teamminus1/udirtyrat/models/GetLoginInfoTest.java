package teamminus1.udirtyrat.models;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests getUsername()
 */

public class GetLoginInfoTest {

    @Test
    public void testGetLoginInfo() throws Exception {
        String username = "abcdefghijklmnopqrstuvwxyz1234567890!@#$%^&*()-=_+/.,m?><';";
        String username11 = "";
        String username12 = "     ";
        String username13 = "  ";
        String username14 = "\n";

        String pw = "abcdefghijklmnopqrstuvwxyz1234567890!@#$%^&*()-=_+/.,m?><';";

        //-----------non-admin-user-------------------------------

        RegistrationResult result = RegistrationHandler.RegisterUser(username, pw, false);
        assertEquals(RegistrationResult.REGISTRATION_SUCCEEDED, result);

        RegistrationResult result11 = RegistrationHandler.RegisterUser(username11, pw, false);
        assertEquals("the username is empty", RegistrationResult.EMPTY_USER, result11);

        RegistrationResult result12 = RegistrationHandler.RegisterUser(username12, pw, false);
        assertEquals("The user contains white spaces", RegistrationResult.EMPTY_USER, result12);

        RegistrationResult result13 = RegistrationHandler.RegisterUser(username13, pw, false);
        assertEquals("The user contains tabs", RegistrationResult.EMPTY_USER, result13);

        RegistrationResult result14 = RegistrationHandler.RegisterUser(username14, pw, false);
        assertEquals("The user contains new line characters", RegistrationResult.EMPTY_USER, result14);


        String pw11 = "";
        String pw12 = "     ";
        String pw13 = "  ";
        String pw14 = "\n";

        RegistrationResult result21 = RegistrationHandler.RegisterUser(username, pw11, false);
        assertEquals("the password is empty", RegistrationResult.EMPTY_PW, result21);

        RegistrationResult result22 = RegistrationHandler.RegisterUser(username, pw12, false);
        assertEquals("The password contains white spaces", RegistrationResult.EMPTY_PW, result22);

        RegistrationResult result23 = RegistrationHandler.RegisterUser(username, pw13, false);
        assertEquals("The password contains tabs", RegistrationResult.EMPTY_PW, result23);

        RegistrationResult result24 = RegistrationHandler.RegisterUser(username, pw14, false);
        assertEquals("The passwords contains new line characters", RegistrationResult.EMPTY_PW, result24);


        //------------------------admin user---------------------------
        username = "admin_abcdefghijklmnopqrstuvwxyz1234567890!@#$%^&*()-=_+/.,m?><';";

        result = RegistrationHandler.RegisterUser(username, pw, true);
        assertEquals(RegistrationResult.REGISTRATION_SUCCEEDED, result);

        result11 = RegistrationHandler.RegisterUser(username11, pw, true);
        assertEquals("the username is empty", RegistrationResult.EMPTY_USER, result11);

        result12 = RegistrationHandler.RegisterUser(username12, pw, true);
        assertEquals("The user contains white spaces", RegistrationResult.EMPTY_USER, result12);

        result13 = RegistrationHandler.RegisterUser(username13, pw, true);
        assertEquals("The user contains tabs", RegistrationResult.EMPTY_USER, result13);

        result14 = RegistrationHandler.RegisterUser(username14, pw, true);
        assertEquals("The user contains new line characters", RegistrationResult.EMPTY_USER, result14);

        result21 = RegistrationHandler.RegisterUser(username, pw11, true);
        assertEquals("the password is empty", RegistrationResult.EMPTY_PW, result21);

        result22 = RegistrationHandler.RegisterUser(username, pw12, true);
        assertEquals("The password contains white spaces", RegistrationResult.EMPTY_PW, result22);

        result23 = RegistrationHandler.RegisterUser(username, pw13, true);
        assertEquals("The password contains tabs", RegistrationResult.EMPTY_PW, result23);

        result24 = RegistrationHandler.RegisterUser(username, pw14, true);
        assertEquals("The passwords contains new line characters", RegistrationResult.EMPTY_PW, result24);

    }
}
