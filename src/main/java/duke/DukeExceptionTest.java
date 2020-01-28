package duke;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertNotNull;

public class DukeExceptionTest {
    @Test
    public void runTest() {
        assertNotNull(new DukeException("read"));
    }
}