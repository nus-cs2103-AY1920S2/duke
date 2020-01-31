import DukeException.DukeIOException;
import DukeException.DukeUnknownInputException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StorageTest {

    @Test
    void buildTaskEventWrongCode() {
        String args = "do CS2103 project (at: FOREVER IT NEVER STOPS)";
        assertThrows(DukeIOException.class, () -> Storage.buildTask("F", args, true));
    }
}
