package duke;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * A JUnit test to test correctness of Duke Exception.
 */
public class DukeExceptionTest {
    @Test
    /**
     * A test to make sure that the Exception generated is not a null.
     */
    public void runTest(){
        assertNotNull(new DukeException("read"));
    }
}