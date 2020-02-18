package duke.parser;

import duke.exception.DukeException;
import duke.exception.DukeInvalidCommandException;
import duke.exception.DukeInvalidDateException;
import duke.exception.DukeIoException;
import duke.exception.DukeMissingInfoException;
import duke.exception.DukeNoSuchTaskException;
import duke.exception.DukeNumberFormatException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ParserTest {

    private TaskList tasks = new TaskList();
    private Ui ui = new Ui();
    private Storage storage;

    {
        try {
            storage = new Storage("data/duke.txt");
        } catch (DukeIoException e) {
            e.printStackTrace();
        }
    }

    @Test
    void parse() {

        try {
            assertEquals("     You have no tasks in your list :)",
                    Parser.parse("list").execute(tasks, ui, storage));
            assertEquals("     Got it. I've added this task:\n       [T][N] homework\n     Now you have 1 "
                    + "tasks "
                    + "in the list.", Parser.parse("todo homework").execute(tasks, ui, storage));
            assertEquals("     Got it. I've added this task:\n       [E][N] performance (at: Jun 6 2020)\n "
                            + "    Now you have 2 tasks in the list.",
                    Parser.parse("event performance /at 2020-06-06").execute(tasks, ui, storage));
            assertEquals("     Got it. I've added this task:\n       [D][N] assignment (by: Feb 14 2020)\n "
                            + "    Now you have 3 tasks in the list.",
                    Parser.parse("deadline assignment /by 2020-02-14").execute(tasks, ui, storage));
        } catch (DukeException e) {
            e.printStackTrace();
        }

        //testing invalid commands
        assertThrows(DukeInvalidCommandException.class, () -> Parser.parse("invalid"));

        // testing exception of delete
        assertThrows(DukeNumberFormatException.class, () -> Parser.parse("delete homework"));
        assertThrows(DukeMissingInfoException.class, () -> Parser.parse("delete"));
        assertThrows(DukeNoSuchTaskException.class, () -> Parser.parse("delete 5")
                .execute(tasks, ui, storage));

        // testing exception of done
        assertThrows(DukeNumberFormatException.class, () -> Parser.parse("done homework"));
        assertThrows(DukeMissingInfoException.class, () -> Parser.parse("done"));
        assertThrows(DukeNoSuchTaskException.class, () -> Parser.parse("done 5")
                .execute(tasks, ui, storage));

        // testing exception of todo
        assertThrows(DukeMissingInfoException.class, () -> Parser.parse("todo"));

        // testing exception of event
        assertThrows(DukeMissingInfoException.class, () -> Parser.parse("event"));
        assertThrows(DukeMissingInfoException.class, () -> Parser.parse("event concert"));
        assertThrows(DukeMissingInfoException.class, () -> Parser.parse("event /at 2020-01-27"));
        assertThrows(DukeInvalidDateException.class, () -> Parser.parse("event concert /at 27 January 2020"));

        // testing exception of deadline
        assertThrows(DukeMissingInfoException.class, () -> Parser.parse("deadline"));
        assertThrows(DukeMissingInfoException.class, () -> Parser.parse("deadline assignment"));
        assertThrows(DukeMissingInfoException.class, () -> Parser.parse("deadline /by 2020-01-27"));
        assertThrows(DukeInvalidDateException.class, () -> Parser.parse("deadline assignment /by 27 January "
                + "2020"));
    }
}