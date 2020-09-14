import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Tests the Parser class.
 */
public class ParserTest {

    @Test
    public void getDate_emptyDate_exceptionThrown() {
        Parser parser = new Parser("deadline return book");

        Assertions.assertThrows(DukeException.class, () -> {
            parser.getDate();
        });
    }

    @Test
    public void getDate_datePresent_success() {
        Parser parser = new Parser("deadline return book /by 2020-01-26 11:00");

        LocalDate date = LocalDate.parse("2020-01-26");

        try {
            Assertions.assertEquals(date, parser.getDate());
        } catch (DukeException exception) {
            // Not supposed to reach here.
            Assertions.fail();
        }
    }

    @Test
    public void getTime_emptyTime_exceptionThrown() {
        Parser parser = new Parser("deadline return book /by 2020-01-26");

        Assertions.assertThrows(DukeException.class, () -> {
            parser.getTime();
        });
    }

    @Test
    public void getTime_timePresent_success() {
        Parser parser = new Parser("deadline return book /by 2020-01-26 11:00");

        LocalTime time = LocalTime.parse("11:00");

        try {
            Assertions.assertEquals(time, parser.getTime());
        } catch (DukeException exception) {
            // Not supposed to reach here.
            Assertions.fail();
        }
    }

    @Test
    public void getDescription_emptyDescription_exceptionThrown() {
        Parser parser = new Parser("deadline");

        Assertions.assertThrows(DukeException.class, () -> {
            parser.getDescription();
        });
    }

    @Test
    public void getDescription_descriptionPresent_success() {
        Parser parser = new Parser("deadline return book /by 2020-01-26 11:00");

        String description = "return book";

        try {
            Assertions.assertEquals(description, parser.getDescription());
        } catch (DukeException exception) {
            // Not supposed to reach here.
            Assertions.fail();
        }
    }
}
