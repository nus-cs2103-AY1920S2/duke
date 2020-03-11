package dude.component;

import dude.task.Deadline;
import dude.task.Todo;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DukeTest {
    private Duke duke;
    private StubUI ui;
    private StubStorage storage;
    private TaskList tasks;

    private void setup(String input) {
        this.ui = new StubUI(input);
        this.storage = new StubStorage();
        this.tasks = storage.restoreSession(ui);
        this.duke = new Duke(storage, tasks, ui);
    }

    @Test
    void serve_byeInput_uiClosed() {
        setup("bye");
        duke.serve();
        assertTrue(ui.isClosed);
    }

    @Test
    void serve_byeWithOtherInput_uiErrorMessageNotClosed() {
        setup("bye dude");
        duke.serve();
        assertFalse(ui.isClosed);
        assertTrue(ui.isParseError);
        assertFalse(ui.isCommandError); // should not be a command error
    }

    @Test
    void serve_doneWithInvalidIndex_uiErrorMessage() {
        setup("done 5");
        duke.serve();
        assertFalse(ui.isParseError);
        assertTrue(ui.isCommandError);
    }

    @Test
    void serve_doneRepeatedIndex_uiErrorMessage() {
        setup("done 1");
        duke.serve();
        duke.serve();
        assertTrue(tasks.getTask(1).isDone());
        assertTrue(ui.isCommandError);
    }

    class StubStorage implements IStorage {

        @Override
        public TaskList restoreSession(IUserInterface ui) {
            TaskList tasks = new TaskList();
            tasks.addTask(new Todo("1st item", false));
            tasks.addTask(new Todo("2nd item", true));
            tasks.addTask(new Deadline("3rd item", LocalDate.of(2000, 1, 20), true));
            return tasks;
        }

        @Override
        public void saveSession(IUserInterface ui, TaskList session) {
        }
    }

    class StubUI implements IUserInterface {
        private String input;
        public boolean isClosed;
        public boolean isParseError;
        public boolean isCommandError;

        public StubUI(String input) {
            this.input = input;
            this.isClosed = false;
            this.isParseError = false;
            this.isCommandError = false;
        }

        @Override
        public String readCommand() {
            return input;
        }

        @Override
        public void respond(Runnable r) {
        }

        @Override
        public void respondParsingError(String errorMsg, String usageMsg) {
            this.isParseError = true;
        }

        @Override
        public void respondError(String errorMsg) {
            this.isCommandError = true;
        }

        @Override
        public void speak(String str) {
        }

        @Override
        public void close() {
            this.isClosed = true;
        }
    }
}