package duke.parser;

import duke.DukeException;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    private TaskList tasks = new TaskList();
    private Ui ui = new Ui();
    private Storage storage = new Storage("data/duke.txt");

    @Test
    void parse() {

        // testing exception of delete
        try {
            Parser.parse("delete homework").execute(tasks, ui, storage);
        } catch (DukeException e) {
            assertEquals("OOPS!!! Please give me the task number to delete.", e.getMessage());
        }
        try {
            Parser.parse("delete").execute(tasks, ui, storage);
        } catch (DukeException e) {
            assertEquals("OOPS!!! Which task should I remove?", e.getMessage());
        }

        // testing exception of done
        try {
            Parser.parse("done homework").execute(tasks, ui, storage);
        } catch (DukeException e) {
            assertEquals("OOPS!!! Please give me the task number.", e.getMessage());
        }
        try {
            Parser.parse("done").execute(tasks, ui, storage);
        } catch (DukeException e) {
            assertEquals("OOPS!!! Which task is done?", e.getMessage());
        }

        // testing exception of todo
        try {
            Parser.parse("todo").execute(tasks, ui, storage);
        } catch (DukeException e) {
            assertEquals("OOPS!!! The description of a todo cannot be empty.", e.getMessage());
        }

        // testing exception of event
        try {
            Parser.parse("event").execute(tasks, ui, storage);
        } catch (DukeException e) {
            assertEquals("OOPS!!! Missing information regarding event.", e.getMessage());
        }
        try {
            Parser.parse("event concert").execute(tasks, ui, storage);
        } catch (DukeException e) {
            assertEquals("OOPS!!! Missing information regarding event.", e.getMessage());
        }
        try {
            Parser.parse("event /at 2020-01-27").execute(tasks, ui, storage);
        } catch (DukeException e) {
            assertEquals("OOPS!!! Missing information regarding event.", e.getMessage());
        }
        try {
            Parser.parse("event concert /at 27 January 2020").execute(tasks, ui, storage);
        } catch (DukeException e) {
            assertEquals("OOPS!!! Please give me the date in yyyy-mm-dd format!", e.getMessage());
        }

        // testing exception of deadline
        try {
            Parser.parse("deadline").execute(tasks, ui, storage);
        } catch (DukeException e) {
            assertEquals("OOPS!!! Missing information regarding deadline.", e.getMessage());
        }
        try {
            Parser.parse("deadline assignment").execute(tasks, ui, storage);
        } catch (DukeException e) {
            assertEquals("OOPS!!! Missing information regarding deadline.", e.getMessage());
        }
        try {
            Parser.parse("deadline /by 2020-01-27").execute(tasks, ui, storage);
        } catch (DukeException e) {
            assertEquals("OOPS!!! Missing information regarding deadline.", e.getMessage());
        }
        try {
            Parser.parse("deadline assignment /by 27 January 2020").execute(tasks, ui, storage);
        } catch (DukeException e) {
            assertEquals("OOPS!!! Please give me the date in yyyy-mm-dd format!", e.getMessage());
        }


    }
}