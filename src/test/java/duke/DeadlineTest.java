package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testDeadlineStringConversion(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        Deadline deadline = new Deadline(" read book",
                LocalDateTime.parse("2020-02-02 2020", formatter));
        assertEquals("[D][INCOMPLETE] read book (by: Feb 2 2020 20:20)", deadline.toString());
        deadline.done();
        assertEquals("[D][DONE] read book", deadline.toString());
    }
}
