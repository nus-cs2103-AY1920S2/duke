import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

public class EventTest {
    @Test
    public void parseDateAndFormatTest() {
        Event event = new Event("DESCRIPTION", false, LocalDate.parse("2020-02-15"));
        assertEquals(event.getFormattedDate(), "Feb 15 2020");
    }
}