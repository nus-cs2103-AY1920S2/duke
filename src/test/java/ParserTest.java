import org.junit.jupiter.api.Test;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
   public void testParsingException() {
        Exception exception = assertThrows(DukeException.class, () -> Parser.parse(""));

        String expected = "--------------------------------------------------\n" +
                "Nani? What talking you? \n" +
                "--------------------------------------------------\n";

        String actual = exception.toString();
        
        assertEquals(expected, actual);
    }
}
