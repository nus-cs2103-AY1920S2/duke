package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.TaskList;
import duke.task.TaskListHistory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

class UndoCommandTest {
    private String horizontalBar =
            "____________________________________________________________";
    private String newline = System.lineSeparator();
    private String indentation = "    ";

    @Mock
    private TaskList tasks;
    @Mock
    private Ui ui;
    @Mock
    private Storage storage;

    @BeforeEach
    void setUp() {
        tasks = mock(TaskList.class);
        ui = mock(Ui.class);
        storage = mock(Storage.class);
        TaskListHistory.reset();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void execute_unableToUndo() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        String expected = indentation + horizontalBar + newline
                + indentation + "Nothing to undo..." + newline
                + indentation + horizontalBar + newline;
        // Setup ui mock answer for exception message
        doAnswer((answer) -> {
            System.out.print(expected);
            return expected;
        }).when(ui).showExceptionMessage(any(DukeException.class));

        UndoCommand undoCommand = new UndoCommand();
        undoCommand.execute(tasks, ui, storage);
        assertEquals(expected, outputStream.toString());
        System.setOut(System.out);
    }

    @Test
    void isExit() {
        UndoCommand command = new UndoCommand();
        assertFalse(command.isExit());
    }
}