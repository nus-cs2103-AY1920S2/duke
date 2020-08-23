import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

import dukeexception.DukeIoException;

public class StorageTest {

    /**
     * Tests if DukeIOException is thrown when unknown code/type of task is given, and the task
     * is trying to be constructed using it. Random code "F" is used to attempt to construct the task.
     */
    @Test
    void buildTaskEventWrongCode() {
        String args = "do CS2103 project (at: FOREVER IT NEVER STOPS)";
        assertThrows(DukeIoException.class, () -> Storage.buildTask("F", args, true));
    }
}
