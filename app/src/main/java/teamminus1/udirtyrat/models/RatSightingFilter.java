package teamminus1.udirtyrat.models;

import java.util.Date;

/**
 * Filter used for sorting rat sightings
 */

public class RatSightingFilter
{
    private Date startDate = null;

    private Date endDate = null;

    /**
     * Sets the start date for the filter
     * @param start start date of filter
     */
    public void setStartDate(Date start) {
        startDate = start;
    }

    /**
     * Gets the start date of the filter
     * @return start date of filter
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * Sets the end date for the filter
     * @param end end date of filter
     */
    public void setEndDate(Date end) {
        endDate = end;
    }

    /**
     * Gets the end date of the filter
     * @return end date of filter
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * Checks whether rat sighting is within the given filter
     * @param sighting rat sighting
     * @return successfully passes filter
     */
    public boolean passesFilter(RatSighting sighting) {
        boolean passes = true;
        if ((startDate != null) && sighting.getCreatedDate().before(startDate)) {
            passes = false;
        }
        if ((endDate != null) && sighting.getCreatedDate().after(endDate)) {
            passes = false;
        }
        return passes;
    }

}
