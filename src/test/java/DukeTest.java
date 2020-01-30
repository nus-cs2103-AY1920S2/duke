import duke.interaction.Parser;
import duke.command.*;

import duke.task.Deadline;
import duke.task.TaskList;
import duke.util.DateTimeUtil;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class DukeTest {

    @Test
    public void ParserTest() {
        System.out.println("TEST: ParserTest");
        Command c = Parser.parse("bye");
        assertEquals(true, c.isExit());
    }

    @Test
    public void DateTimeTest() {
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
    public void TaskTest() {
        System.out.println("TEST: TaskTest");
        LocalDateTime dt = DateTimeUtil.stringAsDateTime("2020-02-25 22:30");
        Deadline testDeadline = new Deadline("Homework", dt);
        testDeadline.markAsDone();
        assertEquals(testDeadline.toSaveString(), "D1Homework@2020-02-25T22:30");
    }

    @Test
    public void TaskListTest() {
        System.out.println("TEST: TaskListTest");
        TaskList tl = new TaskList();
        tl.addSaveStringAsTask("E0flight@2020-02-25T00:00");
        assertEquals(true, tl.doneTask(0));
        assertEquals(false, tl.doneTask(2));
        assertEquals(true, tl.deleteTask(0));
        assertEquals(false, tl.deleteTask(0));
    }
}
