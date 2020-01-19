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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DukeTest {
    final String NEWLINE = System.lineSeparator();
    final String INDENTATION = "    ";
    final String HORIZONTAL_BAR =
            "____________________________________________________________";
    final String HORIZONTAL_DIVIDER = INDENTATION + HORIZONTAL_BAR + NEWLINE;
    String taskDoneIcon = "\u2713";
    String taskNotDoneIcon = "\u2718";
    String exceptionIcon = "\u2639";
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
        // Change output stream
        System.setOut(new PrintStream(output));
    }

    @Test
    @DisplayName("Duke: Test for Greeting message")
    void greet() {
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
        assertEquals(expected, output.toString(), "Should display greeting message");
    }

    @Test
    void dukeException_invalidCommand_displayInvalidCommandMessage() {
        String input = "blah" + NEWLINE;
        Exception exception = assertThrows(DukeException.class,
                () -> duke.processCommands(new ByteArrayInputStream(input.getBytes())));
        // Check exception message
        assertEquals(exceptionIcon + " OOPS!!! I'm sorry, but I don't know what that means :-(",
                exception.getMessage());
    }

    @ParameterizedTest
    @MethodSource("generateOneEventTask")
    @DisplayName("Duke: Test for adding one event task")
    void processCommands_addEventTask_addEventTaskToList(Event task) {
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
        assertEquals(expected, output.toString(), "Should add event task to list");
        // Check if task has been added to list
        assertEquals(1, duke.tasks.size(), "Tasks list should have one more item");
        assertEquals(eventDescription, duke.tasks.get(0).getDescription(),
                "Event description should match");
    }

    @ParameterizedTest
    @MethodSource("generateOneDeadlineTask")
    @DisplayName("Duke: Test for adding one deadline task")
    void processCommands_addDeadlineTask_addDeadlineTaskToList(Deadline task) {
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
        assertEquals(1, duke.tasks.size(), "Should increase task list size by one");
        // Check task description
        assertEquals(deadlineDescription, duke.tasks.get(0).getDescription(),
                "Task description should match");
    }

    @Test
    @DisplayName("Duke: Test for Immediate exit command")
    void processCommands_exitCommand_noMessagePrinted() {
        String input = "bye" + NEWLINE;
        duke.processCommands(new ByteArrayInputStream(input.getBytes()));
        String expected = "";
        assertEquals(expected, output.toString(),
                "Immediate exit: No output should be present");
    }

    @Test
    @DisplayName("Duke: Test for list command")
    void processCommands_listCommand_listStoredItems() {
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
        assertEquals(expected.toString(), output.toString(),
                "Should list out tasks in correct format");
    }

    @ParameterizedTest
    @DisplayName("Duke: Test for marking todo task as done")
    @MethodSource("generateOneTodoTask")
    void processCommands_createNewTodoTaskAndMarkAsDone_todoTaskMarkedDone(Todo task) {
        String taskDescription = task.getDescription();
        String taskCommand = task.getTaskType().toString().toLowerCase() + " " +
                taskDescription;
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
        assertEquals(expected.toString(), output.toString(), "Should mark new task as done");
    }

    @Test
    @DisplayName("Duke: Test for Goodbye message")
    void goodbye() {
        duke.goodbye();
        String expected = HORIZONTAL_DIVIDER +
                INDENTATION + "Bye. Hope to see you again soon!" + NEWLINE +
                HORIZONTAL_DIVIDER;
        assertEquals(expected, output.toString(), "Should print goodbye message");
    }
}