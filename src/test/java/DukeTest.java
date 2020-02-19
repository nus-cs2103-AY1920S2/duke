import duke.interaction.Parser;
import duke.command.Command;

import duke.task.Deadline;
import duke.task.TaskList;
import duke.util.DateTimeUtil;
import duke.util.DukeException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DukeTest {

    @Test
    public void parserTest() {
        System.out.println("TEST: ParserTest");
        Command c = null;
        try {
            c = Parser.parse("bye");
        } catch (DukeException.InvalidCommand invalidCommand) {
            System.out.println(invalidCommand.getMessage());
        }
        assertEquals(true, c.isExit());
    }

    @Test
    public void dateTimeTest() {
        System.out.println("TEST: DateTimeTest");
        LocalDateTime dt = DateTimeUtil.stringAsDateTime("2020-02-25");
        LocalDateTime expected = LocalDateTime.parse("2020-02-25T00:00");
        assertEquals(expected, dt);

        dt = DateTimeUtil.stringAsDateTime("2020-04-05 11:04");
        expected = LocalDateTime.parse("2020-04-05T11:04");
        assertEquals(expected, dt);

        dt = DateTimeUtil.stringAsDateTime("2020-04-05 11:59");
        expected = LocalDateTime.parse("2020-04-05T23:59");
        assertNotEquals(expected, dt);
    }

    @Test
    public void taskTest() {
        System.out.println("TEST: TaskTest");
        LocalDateTime dt = DateTimeUtil.stringAsDateTime("2020-02-25 22:30");
        Deadline testDeadline = new Deadline("Homework", dt);
        testDeadline.markAsDone();
        assertEquals(testDeadline.toSaveString(), "D1Homework@2020-02-25T22:30");
    }

    @Test
    public void taskListTest() {
        System.out.println("TEST: TaskListTest");
        TaskList tl = new TaskList();
        tl.addSaveStringAsTask("E0flight@2020-02-25T00:00");
        assertEquals(true, tl.doneTask(0));
        assertEquals(false, tl.doneTask(2));
        tl.deleteTask(0);
        assertEquals(null, tl.deleteTask(0));
    }
}
