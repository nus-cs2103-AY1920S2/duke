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
        System.setOut(console);
    }

    @Test
    @DisplayName("Duke: Test for invalid command")
    void processCommands_invalidCommand_repeatCommand() {
        System.setOut(new PrintStream(output));
        String input = "invalid" + NEWLINE + "bye";
        duke.processCommands(new ByteArrayInputStream(input.getBytes()));
        String expected = INDENTATION + HORIZONTAL_BAR + NEWLINE +
                INDENTATION + "invalid" + NEWLINE +
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