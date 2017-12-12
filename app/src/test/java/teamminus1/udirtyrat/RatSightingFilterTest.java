package teamminus1.udirtyrat;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import teamminus1.udirtyrat.models.RatSighting;
import teamminus1.udirtyrat.models.RatSightingFilter;

/**
 * Tests rat sighting filters
 */

public class RatSightingFilterTest {

    private RatSightingFilter filter;

    private static final int TEST_INT = 42;
    private static final String TEST_STRING = "TEST";
    private static final double TEST_DOUBLE = 42d;

    @Before
    public void setUpFilter() {

        filter = new RatSightingFilter();

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 1900);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date startDate = calendar.getTime();

        calendar.set(Calendar.YEAR, 1950);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date endDate = calendar.getTime();

        filter.setStartDate(startDate);
        filter.setEndDate(endDate);
    }


    @Test
    public void TestPassesFilterMethod_BeforeStartDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 1875);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date sightingDate = calendar.getTime();


        RatSighting sighting = new RatSighting(TEST_INT, sightingDate, TEST_STRING,
                TEST_STRING, TEST_STRING, TEST_STRING, TEST_STRING, TEST_DOUBLE, TEST_DOUBLE);

        boolean passes = filter.passesFilter(sighting);

        Assert.assertFalse(passes);
    }

    @Test
    public void TestPassesFilterMethod_AfterEndDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 1975);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date sightingDate = calendar.getTime();

        RatSighting sighting = new RatSighting(TEST_INT, sightingDate, TEST_STRING, TEST_STRING,
                TEST_STRING, TEST_STRING, TEST_STRING, TEST_DOUBLE, TEST_DOUBLE);

        boolean passes = filter.passesFilter(sighting);

        Assert.assertFalse(passes);
    }

    @Test
    public void TestPassesFilterMethod_InDateRange_ReturnsTrue() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 1925);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date sightingDate = calendar.getTime();

        RatSighting sighting = new RatSighting(TEST_INT, sightingDate, TEST_STRING, TEST_STRING,
                TEST_STRING, TEST_STRING, TEST_STRING, TEST_DOUBLE, TEST_DOUBLE);

        boolean passes = filter.passesFilter(sighting);
        Assert.assertTrue(passes);
    }
}
