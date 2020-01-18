import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DukeTest {
    final String NEWLINE = System.lineSeparator();
    final String INDENTATION = "    ";
    final String HORIZONTAL_BAR =
            "____________________________________________________________";
    final String HORIZONTAL_DIVIDER = INDENTATION + HORIZONTAL_BAR + NEWLINE;
    String taskDoneIcon = "\u2713";
    String taskNotDoneIcon = "\u2718";
    Duke duke;
    PrintStream console = System.out;
    ByteArrayOutputStream output;

    static Stream<Arguments> generateOneTask() {
        return Stream.of(Arguments.of(new Task("read book", false)));
    }

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
        expected += HORIZONTAL_DIVIDER;
        expected += INDENTATION + "Hello! I'm Duke" + NEWLINE;
        expected += INDENTATION + "What can I do for you?" + NEWLINE;
        expected += HORIZONTAL_DIVIDER;
        assertEquals(expected, output.toString());
    }

    @Test
    @DisplayName("Duke: Test for adding task")
    void processCommands_addTaskCommand_addTaskToList() {
        System.setOut(new PrintStream(output));
        String input = "read book" + NEWLINE + "bye";
        duke.processCommands(new ByteArrayInputStream(input.getBytes()));
        String expected = HORIZONTAL_DIVIDER +
                INDENTATION + "added: read book" + NEWLINE +
                HORIZONTAL_DIVIDER;
        assertEquals(expected, output.toString());
        // Check list of tasks
        assertEquals(1, duke.tasks.size());
        // Check task description
        assertEquals("read book", duke.tasks.get(0).getDescription());
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
        StringBuilder expected = new StringBuilder();
        // Add first task
        expected.append(HORIZONTAL_DIVIDER);
        expected.append(INDENTATION).append("added: read book").append(NEWLINE);
        expected.append(HORIZONTAL_DIVIDER);
        // Add second task
        expected.append(HORIZONTAL_DIVIDER);
        expected.append(INDENTATION).append("added: return book").append(NEWLINE);
        expected.append(HORIZONTAL_DIVIDER);
        // List out tasks
        expected.append(HORIZONTAL_DIVIDER);
        expected.append(INDENTATION).append("Here are the tasks in your list:").append(NEWLINE);
        expected.append(INDENTATION).append("1.[").append(taskNotDoneIcon).append("] ")
                .append("read book").append(NEWLINE);
        expected.append(INDENTATION).append("2.[").append(taskNotDoneIcon).append("] ")
                .append("return book").append(NEWLINE);
        expected.append(HORIZONTAL_DIVIDER);
        assertEquals(expected.toString(), output.toString());
    }

    @ParameterizedTest
    @DisplayName("Duke: Test for marking task as done")
    @MethodSource("generateOneTask")
    void processCommands_createNewTaskAndMarkAsDone_taskMarkedDone(Task task) {
        String taskDescription = task.getDescription();
        System.setOut(new PrintStream(output));
        String input = taskDescription + NEWLINE +
                "list" + NEWLINE +
                "done 1" + NEWLINE +
                "list" + NEWLINE + "bye";
        duke.processCommands(new ByteArrayInputStream(input.getBytes()));
        // Add task
        StringBuilder expected = new StringBuilder();
        expected.append(HORIZONTAL_DIVIDER);
        expected.append(INDENTATION).append("added: ").append(taskDescription).append(NEWLINE);
        expected.append(HORIZONTAL_DIVIDER);
        // List initial task (Should be marked as not done)
        expected.append(HORIZONTAL_DIVIDER);
        expected.append(INDENTATION).append("Here are the tasks in your list:").append(NEWLINE);
        expected.append(INDENTATION);
        expected.append("1.[").append(taskNotDoneIcon).append("] ").append(taskDescription).append(NEWLINE);
        expected.append(HORIZONTAL_DIVIDER);
        // Mark task as done
        expected.append(HORIZONTAL_DIVIDER);
        expected.append(INDENTATION).append("Nice! I've marked this task as done:").append(NEWLINE);
        expected.append(INDENTATION).append("[" + taskDoneIcon + "] " + taskDescription).append(NEWLINE);
        expected.append(HORIZONTAL_DIVIDER);
        // List task again (Should be marked as done)
        expected.append(HORIZONTAL_DIVIDER);
        expected.append(INDENTATION).append("Here are the tasks in your list:").append(NEWLINE);
        expected.append(INDENTATION).append("1.[" + taskDoneIcon + "] " + taskDescription).append(NEWLINE);
        expected.append(HORIZONTAL_DIVIDER);
        assertEquals(expected.toString(), output.toString());
    }

    @Test
    @DisplayName("Duke: Test for Goodbye message")
    void goodbye() {
        System.setOut(new PrintStream(output));
        duke.goodbye();
        String expected = HORIZONTAL_DIVIDER +
                INDENTATION + "Bye. Hope to see you again soon!" + NEWLINE +
                HORIZONTAL_DIVIDER;
        assertEquals(expected, output.toString());
    }
}