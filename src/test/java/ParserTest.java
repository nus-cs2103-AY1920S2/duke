import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    public void testCommandDetected(){
        assertEquals(Commands.LIST_TASKS, new Parser("list", 0).getCommand());
        assertEquals(Commands.BYE, new Parser("bye", 0).getCommand());
        assertEquals(Commands.DONE, new Parser("done 1", 1).getCommand());
        assertEquals(Commands.DEL_TASK, new Parser("delete 1", 1).getCommand());
        assertEquals(Commands.NEW_TASK, new Parser("todo help", 0).getCommand());
    }

    @Test
    public void newDeadlineTask_noDetails_DukeExceptionThrown() {
        try {
            assertEquals(0, new Parser("deadline", 0));
        } catch (DukeException e){
            assertEquals("    ____________________________________________________________\n"
                    + "     ☹ OOPS!!! The description of a deadline cannot be empty.\n"
                    + "    ____________________________________________________________\n", e.toString());
        }
    }
}
