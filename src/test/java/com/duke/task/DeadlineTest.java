package com.duke.task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class DeadlineTest {

    @Test
    public void testWriteForm() {
        assertEquals("D|0|test!|2019-01-01", new Deadline("test!", "2019-01-01").generateWriteFormat());
    }

    @Test
    public void testToString() {
        assertEquals("[D][\u2718] test! (by: Jan 1 2019)", new Deadline("test!", "2019-01-01").toString());
    }
}
