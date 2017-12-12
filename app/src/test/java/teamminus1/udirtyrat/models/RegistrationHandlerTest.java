package teamminus1.udirtyrat.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * JUnit test for RegistrationHandler
 * @author Brandon Seal
 */
public class RegistrationHandlerTest {

    /**
     * Basic setup
     */
    @Before
    public void setUp() {
        UserRegistry registry;
        IUser expectedUser;
        IUser expectedAdmin;
        registry = UserRegistry.Instance();
        expectedUser = new User("Brandon", "A_cool_boi");
        expectedAdmin = new Admin("WednesdayFrog", "My_dudes");
        registry.AddUser(expectedUser);
        registry.AddUser(expectedAdmin);
    }

    /**
     * Checks if registration result will return USERNAME_ALREADY_EXISTS for usernames that already exist
     */
    @Test
    public void testUserAlreadyExists() {
        assertEquals(RegistrationResult.USERNAME_ALREADY_EXISTS, RegistrationHandler.RegisterUser("Brandon", "A_cool_boi", false));
        assertEquals(RegistrationResult.USERNAME_ALREADY_EXISTS, RegistrationHandler.RegisterUser("WednesdayFrog", "My_dudes", false));
    }

    /**
     * Tests a new user addition, and tests if it can't be added again
     */
    @Test
    public void testAddUserTwice() {
        assertEquals(RegistrationResult.REGISTRATION_SUCCEEDED, RegistrationHandler.RegisterUser("Bob", "Great_Professor", false));
        assertEquals(RegistrationResult.USERNAME_ALREADY_EXISTS, RegistrationHandler.RegisterUser("Bob", "Awesome", false));
    }

    /**
     * Tests a new admin addition, and tests if it can't be added again.
     */
    @Test
    public void testAddAdminTwice() {
        assertEquals(RegistrationResult.REGISTRATION_SUCCEEDED, RegistrationHandler.RegisterUser("Corgi", "A_Good_Boye", true));
        assertEquals(RegistrationResult.USERNAME_ALREADY_EXISTS, RegistrationHandler.RegisterUser("Corgi", "Bork", true));
    }
}