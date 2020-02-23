package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void testStringConversion(){
        //a unit test for toString() method of "T o d o" class
        assertEquals("Todo: [Incomplete] Level-9",
                new Todo("Level-9").toString());
    }
}
