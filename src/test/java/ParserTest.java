import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void ByeTest() {
        Parser parser = new Parser();
        try {
            assertEquals("CommandBye", parser.parse("bye").getClass().getTypeName());
        } catch (Exception e) {
            fail("Can't parse");
        }
    }
    @Test
    public void DeadlineTest() {
        Parser parser = new Parser();
        try {
            Command cmd = parser.parse("deadline die/20-01-20 3:05");
        } catch (Exception e) {
            fail("Can't parse");
        }
    }
}
