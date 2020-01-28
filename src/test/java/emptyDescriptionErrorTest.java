import duke.Exception.EmptyDescriptionError;
import duke.Task.Task;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;


public class emptyDescriptionErrorTest {

@Test

    public void test() {

    EmptyDescriptionError error = new EmptyDescriptionError("ToDo");
    String output = error.errorMessage();
    String expected = "\nOPPS! The description of a " + Task.Types.ToDo + " cannot be empty" ;

    assertEquals(expected, output);

}


}
