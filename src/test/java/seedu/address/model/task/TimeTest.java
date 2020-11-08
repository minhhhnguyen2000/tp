package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Time((String) null));
    }

    @Test
    public void constructor_invalidDeadline_throwsIllegalArgumentException() {
        String invalidDeadline = "";
        assertThrows(IllegalArgumentException.class, () -> new Time(invalidDeadline));
    }

    @Test
    public void isValidDeadline() {
        // null deadline
        assertThrows(NullPointerException.class, () -> Time.isValidTime(null));

        // invalid deadlines
        assertFalse(Time.isValidTime("")); // empty string
        assertFalse(Time.isValidTime(" ")); // spaces only
        assertFalse(Time.isValidTime("0000")); // time only
        assertFalse(Time.isValidTime("deadline")); // non-numeric
        assertFalse(Time.isValidTime("01-01-2020")); // date only
        assertFalse(Time.isValidTime("2a-02-2O2O aa00")); // alphabets within digits
        assertFalse(Time.isValidTime("2*-02-2O2O #a00")); // special characters within digits
        assertFalse(Time.isValidTime("01- 01-2020 1800")); // spaces within date
        assertFalse(Time.isValidTime("01-01-2020 18 00")); // spaces within time
        assertFalse(Time.isValidTime("00-00-0000 0000")); // all zeros

        // Invalid day of month (boundary values)
        assertFalse(Time.isValidTime("30-02-2020 1800")); // invalid day of month
        assertFalse(Time.isValidTime("31-02-2020 1800")); // invalid day of month
        assertFalse(Time.isValidTime("00-02-2020 1800")); // invalid day of month

        // Invalid month (boundary values)
        assertFalse(Time.isValidTime("01-00-2020 1800")); // invalid month
        assertFalse(Time.isValidTime("01-13-2020 1800")); // invalid month

        // Invalid year
        assertFalse(Time.isValidTime("01-01-99999 1800")); // invalid year

        // Invalid hours (boundary values)
        assertFalse(Time.isValidTime("01-01-2020 2400")); // invalid hour
        assertFalse(Time.isValidTime("01-01-2020 999")); // invalid hour

        // Invalid minutes (boundary values)
        assertFalse(Time.isValidTime("01-01-2020 1860")); // invalid minute
        assertFalse(Time.isValidTime("01-01-2020 1899")); // invalid minute

        // Invalid formats
        assertFalse(Time.isValidTime("2020-01-01 1800")); // ("uuuu-MM-dd HHmm")
        assertFalse(Time.isValidTime("04-30-2020 1800")); // ("MM-dd-uuuu HHmm")
        assertFalse(Time.isValidTime("1-01-2020 1800")); // ("d-MM-uuuu HHmm")
        assertFalse(Time.isValidTime("01-1-2020 1800")); // ("dd-M-uuuu HHmm")
        assertFalse(Time.isValidTime("01-01-20 1800")); // ("dd-MM-y HHmm")
        assertFalse(Time.isValidTime("01-01-20 18:00")); // ("dd-MM-y HH:mm")
        assertFalse(Time.isValidTime("01-01-2020 06:00 PM")); // ("dd-MM-uuuu hh:mm a")

        // valid deadline dates and time
        assertTrue(Time.isValidTime("01-12-2020 2359")); // boundary value of day of month
        assertTrue(Time.isValidTime("29-12-2020 2359")); // boundary value of day of month
        assertTrue(Time.isValidTime("30-12-2020 2359")); // boundary value of day of month
        assertTrue(Time.isValidTime("31-12-2020 2359")); // boundary value of day of month
        assertTrue(Time.isValidTime("31-12-2020 2359")); // boundary value of month
        assertTrue(Time.isValidTime("31-01-2020 2359")); // boundary value of month
        assertTrue(Time.isValidTime("30-04-2020 1800")); // exactly of format "dd-MM-uuuu HHmm"
        assertTrue(Time.isValidTime("31-12-2020 2359")); // boundary value of minute
        assertTrue(Time.isValidTime("31-12-2020 2300")); // boundary value of minute
        assertTrue(Time.isValidTime("31-12-2020 2359")); // boundary value of hour
        assertTrue(Time.isValidTime("31-12-2020 0059")); // boundary value of hour

    }
}
