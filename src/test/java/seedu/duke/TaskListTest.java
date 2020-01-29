package seedu.duke;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void testIsValidDate() throws IOException {
        assertEquals(true,
                new TaskList(new ArrayList<Task>(), new Storage("data/test.txt")).isValidDate("2020-01-01"));
        assertEquals(false,
                new TaskList(new ArrayList<Task>(), new Storage("data/test.txt")).isValidDate("1 Jan 2020"));
    }
}
