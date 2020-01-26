import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UiTest {
    Ui ui;
    ByteArrayOutputStream output;
    final String INDENTATION = Ui.INDENTATION;
    final String HORIZONTAL_BAR = Ui.HORIZONTAL_BAR;
    final String NEWLINE = Ui.NEWLINE;
    final String HORIZONTAL_DIVIDER = INDENTATION + HORIZONTAL_BAR + NEWLINE;

    static Stream<Arguments> generateTaskForDeletion() {
        return Stream.of(
                Arguments.of(new Todo("Read book"), 0),
                Arguments.of(new Event("Birthday Party", "2020-01-01"), 0),
                Arguments.of(new Deadline("Finish Coding Project", "2020-01-27"), 0));
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
    @DisplayName("Ui: Test for Greeting message")
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
    }

    @Test
    void unableToReadUserInput() {
    }

    @Test
    void commandNotFound() {
    }

    @Test
    void listTasks() {
    }

    @Test
    void showExceptionMessage() {
    }

    @Test
    void printTaskAddition() {
    }

    @Test
    void markTaskAsDone() {
    }

    @ParameterizedTest
    @MethodSource("generateTaskForDeletion")
    void printTaskDeletion(Task task, int totalTasks) {
        String expected = HORIZONTAL_DIVIDER +
                INDENTATION + "Noted. I've removed this task:" + NEWLINE +
                INDENTATION + "  " + task.toString() + NEWLINE +
                INDENTATION + "Now you have " + totalTasks + " tasks in the list." + NEWLINE +
                HORIZONTAL_DIVIDER;
        ui.printTaskDeletion(task, totalTasks);
        assertEquals(expected, output.toString());
    }

    @Test
    @DisplayName("Duke: Test for Goodbye message")
    void goodbye() {
        ui.goodbye();
        String expected = HORIZONTAL_DIVIDER +
                INDENTATION + "Goodbye friend." + NEWLINE +
                HORIZONTAL_DIVIDER;
        assertEquals(expected, output.toString(), "Should print goodbye message");
    }
}