import org.junit.jupiter.api.Test;

import java.time.DateTimeException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DeadlineTest {

    @Test
    void getByTest_invalidDateFormat_DateTimeExceptionThrown() {
        assertThrows(DateTimeException.class, () -> {
            new Event("abcd", LocalDate.parse("05-05-1995"));
        });
    }

    @Test
    void getByTest_validDateFormat_dateInMmmDdYyyyFormat() {
        Deadline deadlineTest = new Deadline("Submit report", LocalDate.parse("2020-05-05"));
        String actualResult = deadlineTest.getBy();
        String expectedResult = "May 05 2020";
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void testToStringTest_correctInput_stringDescribingDeadlineObject() {
        Deadline deadlineTest = new Deadline("Submit report", LocalDate.parse("2020-05-05"));
        String actualResult = deadlineTest.toString();
        String expectedResult = "[D][:(] Submit report (by: May 05 2020)";
        assertEquals(expectedResult, actualResult);
    }
}