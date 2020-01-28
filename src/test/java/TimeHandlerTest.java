import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeHandlerTest {
    @Test
    public void dateToStringTest() {
        Optional<Date> myDate = TimeHandler.dateFromString("1993-10-12");
        assertEquals("Tue Oct 12 00:00:00 SGT 1993",myDate.get().toString());
    }
}
