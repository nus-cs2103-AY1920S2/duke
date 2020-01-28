package duke;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ToDoTest {
    @Test
    public void runTest() {
        assertEquals(new ToDo("read").writeDrive(), "T|0|read");
    }
}
