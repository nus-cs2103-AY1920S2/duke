import duke.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;


public class ParserTest {

    @Test
    public void parseMethod_notEnoughArguments_exceptionThrown() {
        try {
            Parser.parse("delete");
            fail();
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("A tad too few words, innit?"));
        }
    }
}