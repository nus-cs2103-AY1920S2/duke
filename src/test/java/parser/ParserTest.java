package parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class ParserTest {

    @Test
    public void addTask_todoCommand_success() {
        Parser parser = new Parser();
        try {
            Command command = parser.parseCommand("todo something");
            assertTrue(command instanceof AddToDoCommand);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void addCommand_eventTask_success() {
        Parser parser = new Parser();
        try {
            Command command = parser.parseCommand("event career fair /at 2020-03-03 14:00");
            assertTrue(command instanceof AddEventCommand);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void addCommand_deadlineTask_success() {
        Parser parser = new Parser();
        try {
            Command command = parser.parseCommand("deadline career fair /by 2020-03-03");
            assertTrue(command instanceof AddDeadlineCommand);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void findCommand_success() {
        Parser parser = new Parser();
        try {
            Command command = parser.parseCommand("find proposal");
            assertTrue(command instanceof FindCommand);
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void addCommand_eventTaskWrongDate_exceptionThrown() {
        Parser parser = new Parser();
        try {
            Command command = parser.parseCommand("event career fair /at 2020-03-40");
        } catch (Exception e) {
            assertEquals(e.getMessage(),
                    "Text '2020-03-40' could not be parsed: Invalid value for DayOfMonth "
                            + "(valid values 1 - 28/31): 40\n");
        }
    }

    @Test
    public void addCommand_eventTaskWrongTime_exceptionThrown() {
        Parser parser = new Parser();
        try {
            Command command = parser.parseCommand("event career fair /at 2020-03-03 99:99");
        } catch (Exception e) {
            assertEquals(e.getMessage(),
                    "Text '99:99' could not be parsed: Invalid value for MinuteOfHour (valid values 0 - 59): 99\n");
        }
    }

}
