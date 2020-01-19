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

    static Stream<Arguments> generateOneTodoTask() {
        return Stream.of(Arguments.of(new Todo("read book", false)));
    }

    static Stream<Arguments> generateOneDeadlineTask() {
        return Stream.of(Arguments.of(
                new Deadline("return book", "Sunday")));
    }

    static Stream<Arguments> generateOneEventTask() {
        return Stream.of(Arguments.of(
                new Event("project meeting", "Mon 2-4pm")));
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

    @ParameterizedTest
    @MethodSource("generateOneEventTask")
    @DisplayName("Duke: Test for adding one event task")
    void processCommands_addEventTask_addEventTaskToList(Event task) {
        System.setOut(new PrintStream(output));
        String delimiter = "/at";
        String eventDescription = task.getDescription();
        String eventTime = task.getEventTime();
        String input = String.format("event %s %s %s", eventDescription, delimiter,
                eventTime);
        input += NEWLINE + "bye";
        duke.processCommands(new ByteArrayInputStream(input.getBytes()));
        String expectedEventDescription = "  " + String.format("[E][%s] %s (at: %s)",
                taskNotDoneIcon, eventDescription, eventTime);
        String expected = HORIZONTAL_DIVIDER +
                INDENTATION + "Got it. I've added this task:" + NEWLINE +
                INDENTATION + expectedEventDescription + NEWLINE +
                INDENTATION + "Now you have 1 tasks in the list." + NEWLINE +
                HORIZONTAL_DIVIDER;
        assertEquals(expected, output.toString());
        // Check if task has been added to list
        assertEquals(1, duke.tasks.size());
        assertEquals(eventDescription, duke.tasks.get(0).getDescription());
    }

    @ParameterizedTest
    @MethodSource("generateOneDeadlineTask")
    @DisplayName("Duke: Test for adding one deadline task")
    void processCommands_addDeadlineTask_addDeadlineTaskToList(Deadline task) {
        System.setOut(new PrintStream(output));
        String delimiter = "/by";
        String deadlineDescription = task.getDescription();
        String deadline = task.getDeadline();
        String input = String.format("deadline %s %s %s", deadlineDescription,
                delimiter, deadline);
        input += NEWLINE + "bye";
        duke.processCommands(new ByteArrayInputStream(input.getBytes()));
        String expectedDeadlineDescription = "  " + String.format("[D][%s] %s (by: %s)",
                taskNotDoneIcon, deadlineDescription, deadline);
        String expected = HORIZONTAL_DIVIDER +
                INDENTATION + "Got it. I've added this task:" + NEWLINE +
                INDENTATION + expectedDeadlineDescription + NEWLINE +
                INDENTATION + "Now you have 1 tasks in the list." + NEWLINE +
                HORIZONTAL_DIVIDER;
        assertEquals(expected, output.toString());
        // Check if task has been added to list
        assertEquals(1, duke.tasks.size());
        // Check task description
        assertEquals(deadlineDescription, duke.tasks.get(0).getDescription());
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
        String input = "todo read book" + NEWLINE +
                "todo return book" + NEWLINE +
                "list" + NEWLINE + "bye";
        duke.processCommands(new ByteArrayInputStream(input.getBytes()));
        StringBuilder expected = new StringBuilder();
        // Add first task
        String firstTaskDescription = "  " + String.format("[T][%s] read book", taskNotDoneIcon);
        expected.append(HORIZONTAL_DIVIDER);
        expected.append(INDENTATION).append("Got it. I've added this task:").append(NEWLINE);
        expected.append(INDENTATION).append(firstTaskDescription).append(NEWLINE);
        expected.append(INDENTATION).append("Now you have 1 tasks in the list.").append(NEWLINE);
        expected.append(HORIZONTAL_DIVIDER);
        // Add second task
        String secondTaskDescription = "  " + String.format("[T][%s] return book", taskNotDoneIcon);
        expected.append(HORIZONTAL_DIVIDER);
        expected.append(INDENTATION).append("Got it. I've added this task:").append(NEWLINE);
        expected.append(INDENTATION).append(secondTaskDescription).append(NEWLINE);
        expected.append(INDENTATION).append("Now you have 2 tasks in the list.").append(NEWLINE);
        expected.append(HORIZONTAL_DIVIDER);
        // List out tasks
        expected.append(HORIZONTAL_DIVIDER);
        expected.append(INDENTATION).append("Here are the tasks in your list:").append(NEWLINE);
        expected.append(INDENTATION).append("1.[T][").append(taskNotDoneIcon).append("] ")
                .append("read book").append(NEWLINE);
        expected.append(INDENTATION).append("2.[T][").append(taskNotDoneIcon).append("] ")
                .append("return book").append(NEWLINE);
        expected.append(HORIZONTAL_DIVIDER);
        assertEquals(expected.toString(), output.toString());
    }

    @ParameterizedTest
    @DisplayName("Duke: Test for marking todo task as done")
    @MethodSource("generateOneTodoTask")
    void processCommands_createNewTodoTaskAndMarkAsDone_todoTaskMarkedDone(Todo task) {
        String taskDescription = task.getDescription();
        String taskCommand = task.getTaskType().toString().toLowerCase() + " " +
                taskDescription;
        System.setOut(new PrintStream(output));
        String input = taskCommand + NEWLINE +
                "list" + NEWLINE +
                "done 1" + NEWLINE +
                "list" + NEWLINE + "bye";
        duke.processCommands(new ByteArrayInputStream(input.getBytes()));
        // Add task
        StringBuilder expected = new StringBuilder();
        String expectedTaskDescription = String.format("[T][%s] %s", taskNotDoneIcon, taskDescription);
        expected.append(HORIZONTAL_DIVIDER);
        expected.append(INDENTATION).append("Got it. I've added this task:").append(NEWLINE);
        expected.append(INDENTATION).append("  ").append(expectedTaskDescription).append(NEWLINE);
        expected.append(INDENTATION).append("Now you have 1 tasks in the list.").append(NEWLINE);
        expected.append(HORIZONTAL_DIVIDER);
        // List initial task (Should be marked as not done)
        expected.append(HORIZONTAL_DIVIDER);
        expected.append(INDENTATION).append("Here are the tasks in your list:").append(NEWLINE);
        expected.append(INDENTATION);
        expected.append("1.").append(expectedTaskDescription).append(NEWLINE);
        expected.append(HORIZONTAL_DIVIDER);
        // Mark task as done
        String expectedTaskDoneString = String.format("[T][%s] %s", taskDoneIcon, taskDescription);
        expected.append(HORIZONTAL_DIVIDER);
        expected.append(INDENTATION).append("Nice! I've marked this task as done:").append(NEWLINE);
        expected.append(INDENTATION).append(expectedTaskDoneString).append(NEWLINE);
        expected.append(HORIZONTAL_DIVIDER);
        // List task again (Should be marked as done)
        expected.append(HORIZONTAL_DIVIDER);
        expected.append(INDENTATION).append("Here are the tasks in your list:").append(NEWLINE);
        expected.append(INDENTATION);
        expected.append("1.").append(expectedTaskDoneString).append(NEWLINE);
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