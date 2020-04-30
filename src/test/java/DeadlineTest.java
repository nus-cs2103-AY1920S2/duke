import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * DeadlineTest class is a test class for Deadline class.
 */
public class DeadlineTest {

    /**
     * Tests the deadline's toString method for the valid format.
     */
    @Test
    public void testDeadlineToString() {
        LocalDate date = LocalDate.parse("2020-02-02", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        Task deadLine = new Deadline("return book", date, "1800");
        String expectedFormat = "[D][X] return book (by: Feb 2 2020 1800)";
        assertEquals(expectedFormat, deadLine.toString());
    }

}
