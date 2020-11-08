package seedu.address.timetable;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalTimetableData.DATA;
import static seedu.address.testutil.TypicalTimetableData.VALID_DATA_2;
import static seedu.address.testutil.TypicalUrls.VALID_URL;
import static seedu.address.timetable.TimetableUrlParser.parseTimetableUrl;

import org.junit.jupiter.api.Test;

public class TimetableUrlParserTest {
    @Test
    public void parseTimetableUrlTest() {
        TimetableData data = parseTimetableUrl(VALID_URL);
        assertTrue(data.isSameTimetableData(VALID_DATA_2));
        assertFalse(data.isSameTimetableData(DATA));
    }
}
