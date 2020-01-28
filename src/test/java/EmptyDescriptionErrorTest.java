import duke.exceptions.EmptyDescriptionError;
import duke.task.Task;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

/**
 * The emptyDescriptionErrorTest program tests if the emptyDescriptionError outputs
 * correct error message.
 *
 * @version 1.0
 * @since 2020-01-28
 */
public class EmptyDescriptionErrorTest {

    /**
     * Tests if the output message of emptyDescriptionError is correct.
     */
    @Test
    public void test() {

        EmptyDescriptionError error = new EmptyDescriptionError("ToDo");
        String output = error.errorMessage();
        String expected = "\nOPPS! The description of a " + Task.Types.ToDo + " cannot be empty";

        assertEquals(expected, output);
    }


}
