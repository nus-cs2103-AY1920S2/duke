import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class DukeTest {
    final String NEWLINE = System.lineSeparator();
    final String INDENTATION = "    ";
    final String HORIZONTAL_BAR =
            "____________________________________________________________";
    Duke duke;
    PrintStream console = System.out;
    ByteArrayOutputStream output;

    @BeforeEach
    void init() {
        duke = new Duke();
        output = new ByteArrayOutputStream();
    }

    @Test
    @DisplayName("Duke: Test for Greeting message")
    void greet() {
        System.setOut(new PrintStream(output));
        duke.greet();
        String expected = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n" + NEWLINE;
        expected += INDENTATION + HORIZONTAL_BAR + NEWLINE;
        expected += INDENTATION + "Hello! I'm Duke" + NEWLINE;
        expected += INDENTATION + "What can I do for you?" + NEWLINE;
        expected += INDENTATION + HORIZONTAL_BAR + NEWLINE;
        assertEquals(expected, output.toString());
    }

    @Test
    @DisplayName("Duke: Test for adding task")
    void processCommands_addTaskCommand_addTaskToList() {
        System.setOut(new PrintStream(output));
        String input = "read book" + NEWLINE + "bye";
        duke.processCommands(new ByteArrayInputStream(input.getBytes()));
        String expected = INDENTATION + HORIZONTAL_BAR + NEWLINE +
                INDENTATION + "added: read book" + NEWLINE +
                INDENTATION + HORIZONTAL_BAR + NEWLINE;
        assertEquals(expected, output.toString());
        // Check list of tasks
        assertEquals(1, duke.tasks.size());
        assertEquals("read book", duke.tasks.get(0));
    }

    @Test
    @DisplayName("Duke: Test for Immediate exit command")
    void processCommands_exitCommand_noMessagePrinted() {
        System.setOut(new PrintStream(output));
        String input = "bye" + NEWLINE;
        duke.processCommands(new ByteArrayInputStream(input.getBytes()));
        String expected = "";
        assertEquals(expected, output.toString());
    }

    @Test
    @DisplayName("Duke: Test for list command")
    void processCommands_listCommand_listStoredItems() {
        System.setOut(new PrintStream(output));
        String input = "read book" + NEWLINE +
                "return book" + NEWLINE +
                "list" + NEWLINE + "bye";
        duke.processCommands(new ByteArrayInputStream(input.getBytes()));
        String expected = INDENTATION + HORIZONTAL_BAR + NEWLINE +
                INDENTATION + "added: read book" + NEWLINE +
                INDENTATION + HORIZONTAL_BAR + NEWLINE;
        expected += INDENTATION + HORIZONTAL_BAR + NEWLINE +
                INDENTATION + "added: return book" + NEWLINE +
                INDENTATION + HORIZONTAL_BAR + NEWLINE;
        expected += INDENTATION + HORIZONTAL_BAR + NEWLINE +
                INDENTATION + "1. read book" + NEWLINE +
                INDENTATION + "2. return book" + NEWLINE +
                INDENTATION + HORIZONTAL_BAR + NEWLINE;
        assertEquals(expected, output.toString());
    }

    @Test
    @DisplayName("Duke: Test for Goodbye message")
    void goodbye() {
        System.setOut(new PrintStream(output));
        duke.goodbye();
        String expected = INDENTATION + HORIZONTAL_BAR + NEWLINE +
                INDENTATION + "Bye. Hope to see you again soon!" + NEWLINE +
                INDENTATION + HORIZONTAL_BAR + NEWLINE;
        assertEquals(expected, output.toString());
    }
}