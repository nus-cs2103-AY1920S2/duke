import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void test1() {
        ToDo td = new ToDo("Task1");
        String expectedStringFormat = "[T] [\u2718] Task1";
        assertEquals(td.toString(), expectedStringFormat);
    }

    @Test
    public void test2() {
        LocalDateTime d1 = LocalDateTime.parse("2019-01-28 2359" , DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        Deadline d = new Deadline("CS2103T assignment", d1);
        String expectedStringFormat = "[D] [\u2718] CS2103T assignment (by: Jan 28 2019 11:59PM)";
        assertEquals(d.toString(), expectedStringFormat);
    }

    @Test
    public void test3() {
        Event e = new Event("CNY", "25th Aug 2019");
        String expectedStringFormat = "[E] [\u2718] CNY (at: 25th Aug 2019)";
        assertEquals(e.toString(), expectedStringFormat);
    }

}
