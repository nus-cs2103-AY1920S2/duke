package duke;

import duke.task.DeadlineStub;
import duke.task.EventStub;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.TodoStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UiTest {
    Ui ui;
    ByteArrayOutputStream output;
    static final String doneStatusIcon = "\u2713";
    static final String incompleteStatusIcon = "\u2718";
    final String INDENTATION = Ui.INDENTATION;
    final String HORIZONTAL_BAR = Ui.HORIZONTAL_BAR;
    final String NEWLINE = Ui.NEWLINE;
    final String HORIZONTAL_DIVIDER = INDENTATION + HORIZONTAL_BAR + NEWLINE;

    static Stream<Arguments> generateAllTaskTypes() {
        return Stream.of(
                Arguments.of(new TodoStub("Read book", false,
                        "todo,0,Read book", doneStatusIcon,
                        incompleteStatusIcon, "[T][" + incompleteStatusIcon + "] Read book")),
                Arguments.of(new EventStub("Birthday Party", "2020-01-01", false,
                        "event,0,Birthday Party,2020-01-01", doneStatusIcon,
                        incompleteStatusIcon,
                        "[E][" + incompleteStatusIcon + "] Birthday Party " + "(at: Jan 1 2020)")),
                Arguments.of(new DeadlineStub("Finish Coding Project", "2020-01-27",
                        false, "deadline,0,2020-01-27", doneStatusIcon,
                        incompleteStatusIcon,
                        "[D][" + incompleteStatusIcon + "] Finish Coding Project (by: 2020-01-27)")));
    }

    static Stream<Arguments> generateAllTaskTypesWithZeroTotalTasks() {
        // Generates a stream of [Task] [task count]
        // [task count] is 0
        return Stream.of(
                Arguments.of(new TodoStub("Read book", false,
                        "todo,0,Read book", doneStatusIcon,
                        incompleteStatusIcon, "[T][" + incompleteStatusIcon + "] Read book"), 0),
                Arguments.of(new EventStub("Birthday Party", "2020-01-01", false,
                        "event,0,Birthday Party,2020-01-01", doneStatusIcon,
                        incompleteStatusIcon,
                        "[E][" + incompleteStatusIcon + "] Birthday Party " + "(at: Jan 1 2020)"), 0),
                Arguments.of(new DeadlineStub("Finish Coding Project", "2020-01-27",
                        false, "deadline,0,Finish Coding Project,2020-01-27",
                        doneStatusIcon, incompleteStatusIcon,
                        "[D][" + incompleteStatusIcon + "] Finish Coding Project (by: Jan 27 2020)"),
                        0));
    }

    static Stream<Arguments> generateDukeExceptions() {
        return Stream.of(
                Arguments.of(new DukeException("Invalid Task Number given!")),
                Arguments.of(new DukeException("Invalid task number given for deletion...")),
                Arguments.of(new DukeException(""))
        );
    }

    static Stream<Arguments> generateOneTaskList() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new TodoStub("Read book", false,
                "todo,0,Read book", doneStatusIcon,
                incompleteStatusIcon, "[T][" + incompleteStatusIcon + "] Read book"));
        tasks.add(new EventStub("Chinese New Year", "2020-01-25", false,
                        "event,0,Chinese New Year,2020-01-25", doneStatusIcon,
                        incompleteStatusIcon,
                        "[E][" + incompleteStatusIcon + "] Birthday Party " + "(at: Jan 25 2020)"));
        tasks.add(new DeadlineStub("Finish project", "2020-04-20", false,
                "deadline,0,Finish project,2020-04-20", doneStatusIcon, incompleteStatusIcon,
                "[D][" + incompleteStatusIcon + "] Finish project (by: Apr 20 2020)"));
        return Stream.of(
                Arguments.of(new TaskList(tasks))
        );
    }

    @BeforeEach
    void setUp() {
        ui = new Ui();
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "test", "    "})
    void printTextWithIndentation(String s) {
        String expected = INDENTATION + s + NEWLINE;
        ui.printTextWithIndentation(s);
        assertEquals(expected, output.toString());
    }

    @Test
    @DisplayName("duke.Ui: Test for Greeting message")
    void greet() {
        ui.greet();
        StringBuilder expected = new StringBuilder();
        expected.append("  __  __        _____       _           _   ").append(NEWLINE);
        expected.append(" |  \\/  |      |  __ \\     | |         | |  ").append(NEWLINE);
        expected.append(" | \\  / |_ __  | |__) |___ | |__   ___ | |_ ").append(NEWLINE);
        expected.append(" | |\\/| | '__| |  _  // _ \\| '_ \\ / _ \\| __|").append(NEWLINE);
        expected.append(" | |  | | |    | | \\ \\ (_) | |_) | (_) | |_ ").append(NEWLINE);
        expected.append(" |_|  |_|_|    |_|  \\_\\___/|_.__/ \\___/ \\__|").append(NEWLINE);
        expected.append(HORIZONTAL_DIVIDER);
        expected.append(INDENTATION).append("Hello friend. Hello friend?").append(NEWLINE);
        expected.append(INDENTATION).append("That's lame. Maybe I should give you a name.").append(NEWLINE);
        expected.append(INDENTATION).append("But that's a slippery slope, you're only in my head,").append(NEWLINE);
        expected.append(INDENTATION).append("we have to remember that.").append(NEWLINE);
        expected.append(HORIZONTAL_DIVIDER);
        assertEquals(expected.toString(), output.toString(), "Should display greeting message");
    }

    @Test
    void showLoadingError() {
        String expected = HORIZONTAL_DIVIDER +
                INDENTATION + "Unable to load storage data..." + NEWLINE +
                HORIZONTAL_DIVIDER;
        ui.showLoadingError();
        assertEquals(expected, output.toString());
    }

    @Test
    void unableToReadUserInput() {
        String expected = HORIZONTAL_DIVIDER +
                INDENTATION + "Unable to read user input..." + NEWLINE +
                HORIZONTAL_DIVIDER;
        ui.unableToReadUserInput();
        assertEquals(expected, output.toString());
    }

    @Test
    void commandNotFound() {
        String expected = HORIZONTAL_DIVIDER +
                INDENTATION + "404 Not Found... Are you there?" + NEWLINE +
                HORIZONTAL_DIVIDER;
        ui.commandNotFound();
        assertEquals(expected, output.toString());
    }

    @ParameterizedTest
    @MethodSource("generateOneTaskList")
    void listTasks(TaskList tasks) {
        String listHeaderInformation = "Here are the tasks in your list:";
        StringBuilder expected = new StringBuilder(HORIZONTAL_DIVIDER +
                INDENTATION + listHeaderInformation + NEWLINE);
        int taskCount = 1;
        for (Task task : tasks) {
            expected.append(INDENTATION)
                    .append(taskCount).append(".").append(task.toString())
                    .append(NEWLINE);
            taskCount++;
        }
        expected.append(HORIZONTAL_DIVIDER);
        // Execute test function
        ui.listTasks(tasks, listHeaderInformation);
        assertEquals(expected.toString(), output.toString());
    }

    @ParameterizedTest
    @MethodSource("generateDukeExceptions")
    void showExceptionMessage_dukeException(Exception exception) {
        ui.showExceptionMessage(exception);
        String expected = HORIZONTAL_DIVIDER +
                INDENTATION + exception.getMessage() + NEWLINE +
                HORIZONTAL_DIVIDER;
        assertEquals(expected, output.toString());
    }

    @ParameterizedTest
    @MethodSource("generateAllTaskTypesWithZeroTotalTasks")
    void printTaskAddition_allTaskTypes(Task task, int totalTasks) {
        String taskInfo = task.toString();
        String expected = HORIZONTAL_DIVIDER +
                INDENTATION + "Got it. I've added this task:" + NEWLINE +
                INDENTATION + "  " + taskInfo + NEWLINE +
                INDENTATION + "Now you have " + totalTasks + " tasks in the list." + NEWLINE +
                HORIZONTAL_DIVIDER;
        // Execute function for testing
        ui.printTaskAddition(task, totalTasks);
        assertEquals(expected, output.toString());
    }

    @ParameterizedTest
    @MethodSource("generateAllTaskTypesWithZeroTotalTasks")
    void markTaskAsDone_allTaskTypes(Task task, int totalTasks) {
        // Check if task is initially marked as undone
        assertFalse(task.getTaskCompletionStatus());
        // Mark task as done
        task.markAsDone();
        // Check if task has been marked as done
        assertTrue(task.getTaskCompletionStatus());
    }

    @ParameterizedTest
    @MethodSource("generateAllTaskTypes")
    void markTaskAsDone(Task task) {
        assertFalse(task.getTaskCompletionStatus());
        task.markAsDone();
        assertTrue(task.getTaskCompletionStatus());
    }

    @ParameterizedTest
    @MethodSource("generateAllTaskTypesWithZeroTotalTasks")
    void printTaskDeletion_allTaskTypes(Task task, int totalTasks) {
        String expected = HORIZONTAL_DIVIDER +
                INDENTATION + "Noted. I've removed this task:" + NEWLINE +
                INDENTATION + "  " + task.toString() + NEWLINE +
                INDENTATION + "Now you have " + totalTasks + " tasks in the list." + NEWLINE +
                HORIZONTAL_DIVIDER;
        ui.printTaskDeletion(task, totalTasks);
        assertEquals(expected, output.toString());
    }

    @Test
    @DisplayName("duke.Duke: Test for Goodbye message")
    void goodbye() {
        ui.goodbye();
        String expected = HORIZONTAL_DIVIDER +
                INDENTATION + "Goodbye friend." + NEWLINE +
                HORIZONTAL_DIVIDER;
        assertEquals(expected, output.toString(), "Should print goodbye message");
    }
}