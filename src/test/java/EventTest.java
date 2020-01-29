import org.junit.jupiter.api.Test;

import java.time.DateTimeException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EventTest {

    @Test
    void getTimeTest_invalidDateFormat_dateTimeExceptionThrown() {
        assertThrows(DateTimeException.class, () -> {
            new Event("Project Meeting", LocalDate.parse("05-05-1995"));
        });
    }

    @Test
    void getTimeTest_validDateFormat_dateInMmmDdYyyyFormat() {
        Event eventTest = new Event("Project Meeting", LocalDate.parse("2020-05-05"));
        String actualResult = eventTest.getTime();
        String expectedResult = "May 05 2020";
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testToStringTest_correctInput_stringDescribingEventObject() {
        Event eventTest = new Event("Project Meeting", LocalDate.parse("2020-05-05"));
        String actualResult = eventTest.toString();
        String expectedResult = "[E][:(] Project Meeting (by: May 05 2020)";
        assertEquals(expectedResult, actualResult);
    }
}