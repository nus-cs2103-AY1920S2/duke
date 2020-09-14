import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {
    @Test
    public void grabTaskNameTest() {
        Parser parser = new Parser();
        StringBuilder taskname = new StringBuilder();
        String test = "event Swimming at USC /at 12-02-2020 22:00 - 00:00";
        String[] testCommand = test.split(" ");
        try {
            int indexFound = parser.grabTaskName(taskname, testCommand, "/at");
            assertEquals("Swimming at USC", taskname.toString());
        } catch (DukeException e) {
            fail();
        }
    }

    @Test
    public void grabDateTime() {
        Parser parser = new Parser();
        StringBuilder dateTime = new StringBuilder();
        String test = "deadline CS2103T project /by 12-03-2020 23:59";
        String[] testCommand = test.split(" ");
        try {
            parser.grabDateTime(3, testCommand, dateTime);
            assertEquals("12-03-2020 23:59", dateTime.toString());
        } catch (DukeException e) {
            fail();
        }
    }
}
