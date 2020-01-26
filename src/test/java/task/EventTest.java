package task;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {

    @Test
    void test(){
        Task event = new Event("project meeting ", LocalDate.parse("2020-12-12"));
        String expectedFirst = "[E][Not Done] project meeting (at: Dec 12 2020)";
        String actualFirst = event.toString();
        String expectedSec = "E/0/project meeting /2020-12-12\n";
        String actualSec = event.toStringTaskstxt();
        assertEquals(expectedFirst, actualFirst);
        assertEquals(expectedSec, actualSec);
    }
}
