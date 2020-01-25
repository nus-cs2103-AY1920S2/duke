import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class DukeTest {
    final String NEWLINE = System.lineSeparator();
    final String INDENTATION = "    ";
    final String HORIZONTAL_BAR =
            "____________________________________________________________";
    final String HORIZONTAL_DIVIDER = INDENTATION + HORIZONTAL_BAR + NEWLINE;
    final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM d yyyy");
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
                new Deadline("return book", "2020-12-03")));
    }

    static Stream<Arguments> generateOneEventTask() {
        return Stream.of(Arguments.of(
                new Event("project meeting", "2020-01-25")));
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
        String expected = "  __  __        _____       _           _   " + NEWLINE +
                " |  \\/  |      |  __ \\     | |         | |  " + NEWLINE +
                " | \\  / |_ __  | |__) |___ | |__   ___ | |_ " + NEWLINE +
                " | |\\/| | '__| |  _  // _ \\| '_ \\ / _ \\| __|" + NEWLINE +
                " | |  | | |    | | \\ \\ (_) | |_) | (_) | |_ " + NEWLINE +
                " |_|  |_|_|    |_|  \\_\\___/|_.__/ \\___/ \\__|" + NEWLINE;
        expected += HORIZONTAL_DIVIDER;
        expected += INDENTATION + "Hello friend. Hello friend?" + NEWLINE;
        expected += INDENTATION + "That's lame. Maybe I should give you a name." + NEWLINE;
        expected += INDENTATION + "But that's a slippery slope, you're only in my head," + NEWLINE;
        expected += INDENTATION + "we have to remember that." + NEWLINE;
        expected += HORIZONTAL_DIVIDER;
        assertEquals(expected, output.toString(), "Should display greeting message");
    }

    @Test
    @DisplayName("Duke: Test for invalid command")
    void dukeException_invalidCommand_displayInvalidCommandMessage() {
        String input = "blah" + NEWLINE;
        Exception exception = assertThrows(DukeException.class,
                () -> duke.processCommands(new BufferedReader(
                        new InputStreamReader(new ByteArrayInputStream(input.getBytes())))));
        // Check exception message
        assertEquals(exceptionIcon + " OOPS!!! I'm sorry, but I don't know what that means :-(",
                exception.getMessage(), "Should display invalid command message");
    }

    @Test
    @DisplayName("Duke: Test for empty delete command")
    void dukeException_deleteCommandWithNoNumber_displayInvalidDeleteCommandMessage() {
        String input = "delete" + NEWLINE;
        Exception exception = assertThrows(DukeException.class,
                () -> duke.processCommands(new BufferedReader(
                        new InputStreamReader(new ByteArrayInputStream(input.getBytes())))));
        // Check exception message
        assertEquals("Invalid task number given for deletion...", exception.getMessage(),
                "Should display invalid message for empty delete command");
    }

    @Test
    @DisplayName("Duke: Test for delete command with invalid task number")
    void dukeException_deleteCommandWithOutOfBoundsTaskNumber_displayInvalidDeleteCommandMessage() {
        String input = "delete 10" + NEWLINE;
        Exception exception = assertThrows(DukeException.class,
                () -> duke.processCommands(new BufferedReader(
                        new InputStreamReader(new ByteArrayInputStream(input.getBytes())))));
        // Check exception message
        assertEquals("Invalid task number given for deletion...",
                exception.getMessage(),
                "Should display invalid message for delete command with invalid task number");
    }

    @ParameterizedTest
    @ValueSource(strings = {"done", "done 10"})
    void dukeException_invalidDoneCommand_displayInvalidDoneCommandMessage(String s) {
        // Add newline to input string, to ensure command is executed
        String input =  s + NEWLINE;
        Exception exception = assertThrows(DukeException.class,
                () -> duke.processCommands(new BufferedReader(
                        new InputStreamReader(new ByteArrayInputStream(input.getBytes())))));
        // Check exception message
        assertEquals("Invalid Task Number given!",
                exception.getMessage(),
                "Should display invalid message for done command with invalid task number");
    }

    @Test
    @DisplayName("Duke: Test for empty Todo command")
    void dukeException_emptyTodoCommand_displayInvalidTodoMessage() {
        String input = "todo" + NEWLINE;
        Exception exception = assertThrows(DukeException.class,
                () -> duke.processCommands(new BufferedReader(
                        new InputStreamReader(new ByteArrayInputStream(input.getBytes())))));
        // Check exception message
        assertEquals(exceptionIcon + " The description of a todo cannot be empty...",
                exception.getMessage(),
                "Should display invalid message for empty todo command");
    }

    @Test
    @DisplayName("Duke: Test for deadline command with no arguments")
    void dukeException_emptyDeadlineCommand_displayInvalidDeadlineMessage() {
        String input = "deadline" + NEWLINE;
        Exception exception = assertThrows(DukeException.class,
                () -> duke.processCommands(new BufferedReader(
                        new InputStreamReader(new ByteArrayInputStream(input.getBytes())))));
        // Check exception message
        assertEquals(exceptionIcon + " The description of a deadline cannot be empty...",
                exception.getMessage(), "Should throw exception for empty deadline command");
    }

    @Test
    @DisplayName("Duke: Test for deadline command with missing delimiter")
    void dukeException_missingDeadlineDelimiter_displayInvalidDeadlineMessage() {
        String input = "deadline return book" + NEWLINE;
        Exception exception = assertThrows(DukeException.class,
                () -> duke.processCommands(new BufferedReader(
                        new InputStreamReader(new ByteArrayInputStream(input.getBytes())))));
        // Check exception message
        String expectedMessage = String.format("%s No deadline given... Format: deadline [description] /by " +
                "[due by]", exceptionIcon);
        assertEquals(expectedMessage, exception.getMessage(),
                "Should throw exception message for deadline command with no due date");
    }

    @Test
    @DisplayName("Duke: Test for deadline command with only delimiter")
    void dukeException_onlyDeadlineDelimiterPresent_displayInvalidDeadlineMessage() {
        String input = "deadline /by" + NEWLINE;
        Exception exception = assertThrows(DukeException.class,
                () -> duke.processCommands(new BufferedReader(
                        new InputStreamReader(new ByteArrayInputStream(input.getBytes())))));
        // Check exception message
        String expectedMessage = String.format("%s No deadline given... Format: deadline [description] /by " +
                "[due by]", exceptionIcon);
        assertEquals(expectedMessage, exception.getMessage(),
                "Should throw exception message for deadline command with no arguments");
    }

    @Test
    @DisplayName("Duke: Test for event command with no arguments")
    void dukeException_emptyEventCommand_displayInvalidEventMessage() {
        String input = "event" + NEWLINE;
        Exception exception = assertThrows(DukeException.class,
                () -> duke.processCommands(new BufferedReader(
                        new InputStreamReader(new ByteArrayInputStream(input.getBytes())))));
        // Check exception message
        String expectedMessage = String.format("%s Wrong input format for adding an event... Format: event " +
                "[description] /at [event time]", exceptionIcon);
        assertEquals(expectedMessage, exception.getMessage(),
                "Should throw exception message for event command with no arguments");
    }

    @Test
    @DisplayName("Duke: Test for event command missing delimiter")
    void dukeException_missingEventDelimiterCommand_displayInvalidEventMessage() {
        String input = "event project meeting Mon 2-4pm" + NEWLINE;
        Exception exception = assertThrows(DukeException.class,
                () -> duke.processCommands(new BufferedReader(
                        new InputStreamReader(new ByteArrayInputStream(input.getBytes())))));
        // Check exception message
        String expectedMessage = String.format("%s Wrong input format for adding an event... Format: event " +
                "[description] /at [event time]", exceptionIcon);
        assertEquals(expectedMessage, exception.getMessage(),
                "Should throw exception message for event command with no arguments");
    }

    @Test
    @DisplayName("Duke: Test for event command with only delimiter")
    void dukeException_onlyEventDelimiterPresent_displayInvalidEventMessage() {
        String input = "event /at" + NEWLINE;
        Exception exception = assertThrows(DukeException.class,
                () -> duke.processCommands(new BufferedReader(
                        new InputStreamReader(new ByteArrayInputStream(input.getBytes())))));
        // Check exception message
        String expectedMessage = String.format("%s Wrong input format for adding an event... Format: event " +
                "[description] /at [event time]", exceptionIcon);
        assertEquals(expectedMessage, exception.getMessage(),
                "Should throw exception message for event command with no arguments");
    }

    @ParameterizedTest
    @MethodSource("generateOneEventTask")
    @DisplayName("Duke: Test for adding one event task")
    void processCommands_addEventTask_addEventTaskToList(Event task) {
        String delimiter = "/at";
        String eventDescription = task.getDescription();
        String eventTime = task.getEventTime().format(dateTimeFormatter);
        String input = String.format("event %s %s %s", eventDescription, delimiter,
                task.getEventTime().toString());
        input += NEWLINE + "bye";
        try {
            duke.processCommands(new BufferedReader(
                    new InputStreamReader(new ByteArrayInputStream(input.getBytes()))));
        } catch (IOException ioException) {
            fail("Should not throw IOException");
        } catch (DukeException dukeException) {
            fail("Should not throw exception");
        }
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
        String deadline = task.getDeadline().format(dateTimeFormatter);
        String input = String.format("deadline %s %s %s", deadlineDescription,
                delimiter, task.getDeadline().toString());
        input += NEWLINE + "bye";
        try {
            duke.processCommands(new BufferedReader(
                    new InputStreamReader(new ByteArrayInputStream(input.getBytes()))));
        } catch (IOException ioException) {
            fail("Should not throw IOException");
        } catch (DukeException dukeException) {
            fail("Should not throw exception");
        }
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
        try {
            duke.processCommands(new BufferedReader(
                    new InputStreamReader(new ByteArrayInputStream(input.getBytes()))));
        } catch (IOException ioException) {
            fail("Should not throw IOException");
        } catch (DukeException dukeException) {
            fail("Should not throw exception");
        }
        String expected = "";
        assertEquals(expected, output.toString(),
                "Immediate exit: No output should be present");
    }

    @ParameterizedTest
    @MethodSource("generateOneTodoTask")
    @DisplayName("Duke: Test for delete command")
    void processCommands_deleteCommand_removeItemFromList(Task task) {
        String input = "todo " + task.getDescription() + NEWLINE +
                "delete 1" + NEWLINE +
                "list" + NEWLINE + "bye";
        try {
            duke.processCommands(new BufferedReader(
                    new InputStreamReader(new ByteArrayInputStream(input.getBytes()))));
        } catch (IOException e) {
            fail("Should not throw IOException");
        } catch (DukeException e) {
            fail("Should not throw DukeException");
        }
        StringBuilder expected = new StringBuilder();
        // Add task
        expected.append(HORIZONTAL_DIVIDER)
                .append(INDENTATION).append("Got it. I've added this task:").append(NEWLINE)
                .append(INDENTATION)
                .append(String.format("  [T][%s] %s", taskNotDoneIcon, task.getDescription()))
                .append(NEWLINE)
                .append(INDENTATION).append("Now you have 1 tasks in the list.").append(NEWLINE)
                .append(HORIZONTAL_DIVIDER);
        // Delete task
        expected.append(HORIZONTAL_DIVIDER)
                .append(INDENTATION).append("Noted. I've removed this task:").append(NEWLINE)
                .append(INDENTATION)
                .append(String.format("  [T][%s] %s", taskNotDoneIcon, task.getDescription()))
                .append(NEWLINE)
                .append(INDENTATION).append("Now you have 0 tasks in the list.").append(NEWLINE)
                .append(HORIZONTAL_DIVIDER);
        // List out tasks (Should be empty)
        expected.append(HORIZONTAL_DIVIDER)
                .append(INDENTATION).append("Here are the tasks in your list:").append(NEWLINE)
                .append(HORIZONTAL_DIVIDER);
        assertEquals(expected.toString(), output.toString(), "Task should be deleted");
    }

    @Test
    @DisplayName("Duke: Test for list command")
    void processCommands_listCommand_listStoredItems() {
        String input = "todo read book" + NEWLINE +
                "todo return book" + NEWLINE +
                "list" + NEWLINE + "bye";
        try {
            duke.processCommands(new BufferedReader(
                    new InputStreamReader(new ByteArrayInputStream(input.getBytes()))));
        } catch (IOException ioException) {
            fail("Should not throw IOException");
        } catch (DukeException dukeException) {
            fail("Should not throw DukeException");
        }
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
        String taskCommand = "todo " + taskDescription;
        String input = taskCommand + NEWLINE +
                "list" + NEWLINE +
                "done 1" + NEWLINE +
                "list" + NEWLINE + "bye";
        try {
            duke.processCommands(new BufferedReader(
                    new InputStreamReader(new ByteArrayInputStream(input.getBytes()))));
        } catch (IOException ioException) {
            fail("Should not throw IOException");
        } catch (DukeException dukeException) {
            fail("Should not throw DukeException");
        }
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
                INDENTATION + "Goodbye friend." + NEWLINE +
                HORIZONTAL_DIVIDER;
        assertEquals(expected, output.toString(), "Should print goodbye message");
    }
}