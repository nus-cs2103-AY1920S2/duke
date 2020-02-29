package seedu.duke;

import org.junit.jupiter.api.Test;
import seedu.duke.storage.Storage;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void testIsValidDate() throws IOException {
        Storage storage = new Storage("duke.txt");
        assertEquals(true,
                storage.isValidDate("2020-01-01"));
        assertEquals(false,
                storage.isValidDate("1 Jan 2020"));
    }
}
