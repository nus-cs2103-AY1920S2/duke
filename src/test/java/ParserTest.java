import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;



public class ParserTest {


    @Test
    public void testcheckMessageType() throws DukeException{

        assertEquals("todo", new Parser().checkMessageType("todo run"));

    }

    @Test
    public void checkMessageType_WrongInput_exceptionThrown() {

        try {
            assertEquals(0, new Parser().checkMessageType("random"));
            fail();
        } catch (DukeException ex){
            assertEquals("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n", ex.getMessage());

        }

    }
}
