package duke;

import duke.task.*;
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

import static org.junit.jupiter.api.Assertions.*;

class UiTest {
    Ui ui;
    ByteArrayOutputStream output;
    final String INDENTATION = Ui.INDENTATION;
    final String HORIZONTAL_BAR = Ui.HORIZONTAL_BAR;
    final String NEWLINE = Ui.NEWLINE;
    final String HORIZONTAL_DIVIDER = INDENTATION + HORIZONTAL_BAR + NEWLINE;

    static Stream<Arguments> generateAllTaskTypes() {
        return Stream.of(
                Arguments.of(new Todo("Read book"), 0),
                Arguments.of(new Event("Birthday Party", "2020-01-01"), 0),
                Arguments.of(new Deadline("Finish Coding Project", "2020-01-27"), 0));
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
        tasks.add(new Todo("read book"));
        tasks.add(new Event("Chinese New Year", "2020-01-25"));
        tasks.add(new Deadline("Finish project", "2020-04-20"));
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
        StringBuilder expected = new StringBuilder(HORIZONTAL_DIVIDER +
                INDENTATION + "Here are the tasks in your list:" + NEWLINE);
        int taskCount = 1;
        for (Task task : tasks) {
            expected.append(INDENTATION)
                    .append(taskCount).append(".").append(task.toString())
                    .append(NEWLINE);
            taskCount++;
        }
        expected.append(HORIZONTAL_DIVIDER);
        // Execute test function
        ui.listTasks(tasks);
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
    @MethodSource("generateAllTaskTypes")
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
    @MethodSource("generateAllTaskTypes")
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