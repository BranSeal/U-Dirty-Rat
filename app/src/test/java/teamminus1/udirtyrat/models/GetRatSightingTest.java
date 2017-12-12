package teamminus1.udirtyrat.models;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.*;

/**
 * Tests getRatSighting()
 */
public class GetRatSightingTest {

    /**
     * Creates a rat sighting object, adds it to the registry, and then tries to retrieve it.
     * Result: The returned rat sighting is equal to the created rat sighting.
     */
    @Test
    public void testGetValidRatSighting() {
        int input = 10001;
        RatSighting output;
        RatSighting expected = new RatSighting(input, // unique key
                Calendar.getInstance().getTime(),
                "Commercial Building",
                "10024",
                "213 WEST 82 STREET",
                "NEW YORK",
                "MANHATTAN",
                40.785,
                -73.977);

        RatRegistry instance = RatRegistry.Instance();
        instance.addRatSighting(expected);
        output = instance.getRatSighting(input);

        assertEquals("Getting valid rat sighting by key failed", expected, output);
    }

    /**
     * Tries to retrieve a rat sighting object from the registry.
     * Result: The method returns null because no rat sighting in the registry has a key of 0.
     */
    @Test
    public void testGetInvalidRatSighting() {
        int input = 0;
        RatSighting output;
        RatSighting expected = null;

        RatRegistry instance = RatRegistry.Instance();
        output = instance.getRatSighting(input);

        assertEquals("Getting invalid rat sighting by key failed", expected, output);
    }

}