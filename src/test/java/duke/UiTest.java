package duke;

import duke.exception.DukeException;
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
    private Ui ui;
    private ByteArrayOutputStream output;
    private static String doneStatusIcon = "\u2713"; // Check mark icon
    private static String incompleteStatusIcon = "\u2718"; // Cross mark icon
    private String indentation = Ui.INDENTATION;
    private String horizontalBar = Ui.HORIZONTAL_BAR;
    private String newline = Ui.NEWLINE;
    private String horizontalDivider = indentation + horizontalBar + newline;

    private static Stream<Arguments> generateAllTaskTypes() {
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

    private static Stream<Arguments> generateAllTaskTypesWithZeroTotalTasks() {
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

    private static Stream<Arguments> generateDukeExceptions() {
        return Stream.of(
                Arguments.of(new DukeException("Invalid Task Number given!")),
                Arguments.of(new DukeException("Invalid task number given for deletion...")),
                Arguments.of(new DukeException(""))
        );
    }

    private static Stream<Arguments> generateOneTaskList() {
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
        String expected = indentation + s + newline;
        ui.printTextWithIndentation(s);
        assertEquals(expected, output.toString());
    }

    @Test
    @DisplayName("duke.Ui: Test for Greeting message")
    void greet() {
        ui.greet();
        StringBuilder expected = new StringBuilder();
        expected.append("  __  __        _____       _           _   ").append(newline);
        expected.append(" |  \\/  |      |  __ \\     | |         | |  ").append(newline);
        expected.append(" | \\  / |_ __  | |__) |___ | |__   ___ | |_ ").append(newline);
        expected.append(" | |\\/| | '__| |  _  // _ \\| '_ \\ / _ \\| __|").append(newline);
        expected.append(" | |  | | |    | | \\ \\ (_) | |_) | (_) | |_ ").append(newline);
        expected.append(" |_|  |_|_|    |_|  \\_\\___/|_.__/ \\___/ \\__|").append(newline);
        expected.append(horizontalDivider);
        expected.append(indentation).append("Hello friend. Hello friend?").append(newline);
        expected.append(indentation).append("That's lame. Maybe I should give you a name.").append(newline);
        expected.append(indentation).append("But that's a slippery slope, you're only in my head,").append(newline);
        expected.append(indentation).append("we have to remember that.").append(newline);
        expected.append(horizontalDivider);
        assertEquals(expected.toString(), output.toString(), "Should display greeting message");
    }

    @Test
    void unableToReadUserInput() {
        String expected = horizontalDivider
                + indentation + "Unable to read user input..." + newline
                + horizontalDivider;
        ui.unableToReadUserInput();
        assertEquals(expected, output.toString());
    }

    @Test
    void commandNotFound() {
        String expected = horizontalDivider
                + indentation + "404 Not Found... Are you there?" + newline
                + horizontalDivider;
        ui.commandNotFound();
        assertEquals(expected, output.toString());
    }

    @ParameterizedTest
    @MethodSource("generateOneTaskList")
    void listTasks(TaskList tasks) {
        String listHeaderInformation = "Here are the tasks in your list:";
        StringBuilder expected = new StringBuilder(horizontalDivider
                + indentation + listHeaderInformation + newline);
        int taskCount = 1;
        for (Task task : tasks) {
            expected.append(indentation)
                    .append(taskCount).append(".").append(task.toString())
                    .append(newline);
            taskCount++;
        }
        expected.append(horizontalDivider);
        // Execute test function
        ui.listTasks(tasks, listHeaderInformation);
        assertEquals(expected.toString(), output.toString());
    }

    @ParameterizedTest
    @MethodSource("generateDukeExceptions")
    void showExceptionMessage_dukeException(Exception exception) {
        ui.showExceptionMessage(exception);
        String expected = horizontalDivider
                + indentation + exception.getMessage() + newline
                + horizontalDivider;
        assertEquals(expected, output.toString());
    }

    @ParameterizedTest
    @MethodSource("generateAllTaskTypesWithZeroTotalTasks")
    void printTaskAddition_allTaskTypes(Task task, int totalTasks) {
        String taskInfo = task.toString();
        String expected = horizontalDivider
                + indentation + "Got it. I've added this task:" + newline
                + indentation + "  " + taskInfo + newline
                + indentation + "Now you have " + totalTasks + " tasks in the list." + newline
                + horizontalDivider;
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
        String expected = horizontalDivider
                + indentation + "Noted. I've removed this task:" + newline
                + indentation + "  " + task.toString() + newline
                + indentation + "Now you have " + totalTasks + " tasks in the list." + newline
                + horizontalDivider;
        ui.printTaskDeletion(task, totalTasks);
        assertEquals(expected, output.toString());
    }

    @Test
    @DisplayName("duke.Duke: Test for Goodbye message")
    void goodbye() {
        ui.goodbye();
        String expected = horizontalDivider
                + indentation + "Goodbye friend." + newline
                + horizontalDivider;
        assertEquals(expected, output.toString(), "Should print goodbye message");
    }
}