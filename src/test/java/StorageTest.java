import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

import dukeexception.DukeIoException;

public class StorageTest {

    @Test
    void buildTaskEventWrongCode() {
        String args = "do CS2103 project (at: FOREVER IT NEVER STOPS)";
        assertThrows(DukeIoException.class, () -> Storage.buildTask("F", args, true));
    }
}
