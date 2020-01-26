import duke.interaction.Parser;
import duke.command.*;

import duke.interaction.Ui;
import duke.task.Deadline;
import duke.task.TaskList;
import duke.util.DateTimeUtil;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class DukeTest {

    @Test
    public void ParserTest() {
        Command c = Parser.parse("bye");
        assertEquals(true, c.isExit());
    }

    @Test
    public void DateTimeTest() {
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
        LocalDateTime dt = DateTimeUtil.stringAsDateTime("2020-02-25 22:30");
        Deadline testDeadline = new Deadline("Homework", dt);
        testDeadline.MarkAsDone();
        assertEquals(testDeadline.toSaveString(), "D1Homework@2020-02-25T22:30");
    }

    @Test
    public void TaskListTest() {
        TaskList tl = new TaskList();
        tl.AddSaveStringAsTask("E0flight@2020-02-25T00:00");
        assertEquals(true, tl.DoneTask(0));
        assertEquals(false, tl.DoneTask(2));
        assertEquals(true, tl.DeleteTask(0));
        assertEquals(false, tl.DeleteTask(0));
    }
}
