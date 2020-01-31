package duke.task;

import duke.storage.CSV;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void testStringConversion() {
        assertEquals(Task.parseFromCSV(new CSV(new CSV("D"), new CSV("by"), new CSV(" Sun"), new CSV("false"), new CSV("fuckyou"))).toString(), "[D][✗] fuckyou (by: Sun)");
    }
}
