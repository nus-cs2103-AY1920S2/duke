package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DukeTest {
    String fileSeparator = File.separator;
    // Map project path to the directory from which you run your program
    String projectRootPath = Paths.get("").toAbsolutePath().toString();
    String dataDirectoryPath = projectRootPath + fileSeparator + "data";
    String saveFile = "test.txt";
    String newline = System.lineSeparator();
    String indentation = "    ";
    String horizontalBar =
            "____________________________________________________________";
    String horizontalDivider = indentation + horizontalBar + newline;
    final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM d yyyy");
    String taskDoneIcon = "\u2713"; // Check mark icon
    String taskNotDoneIcon = "\u2718"; // Cross icon
    String exceptionIcon = "\u2639"; // Sad face icon
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

    void deleteSaveFile() {
        try {
            Files.deleteIfExists(Paths.get(dataDirectoryPath + fileSeparator + saveFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    String greeting() {
        String logo = "  __  __        _____       _           _   " + newline
                + " |  \\/  |      |  __ \\     | |         | |  " + newline
                + " | \\  / |_ __  | |__) |___ | |__   ___ | |_ " + newline
                + " | |\\/| | '__| |  _  // _ \\| '_ \\ / _ \\| __|" + newline
                + " | |  | | |    | | \\ \\ (_) | |_) | (_) | |_ " + newline
                + " |_|  |_|_|    |_|  \\_\\___/|_.__/ \\___/ \\__|" + newline;
        logo += indentation + horizontalBar + newline;
        logo += indentation + "Hello friend. Hello friend?" + newline;
        logo += indentation + "That's lame. Maybe I should give you a name." + newline;
        logo += indentation + "But that's a slippery slope, you're only in my head," + newline;
        logo += indentation + "we have to remember that." + newline;
        logo += indentation + horizontalBar + newline;
        return logo;
    }

    @BeforeEach
    void init() {
        // Delete any save file
        deleteSaveFile();
        duke = new Duke(saveFile);
        output = new ByteArrayOutputStream();
        // Change output stream
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    void cleanUp() {
        deleteSaveFile();
    }

    @Test
    @DisplayName("duke.Duke: Test for invalid command")
    void dukeException_invalidCommand_displayInvalidCommandMessage() {
        String input = "blah" + newline + "bye" + newline;
        duke.run(new BufferedReader(
                new InputStreamReader(new ByteArrayInputStream(input.getBytes()))));
        // Check exception message
        String expected = greeting();
        expected += horizontalDivider + indentation + exceptionIcon
                + " OOPS!!! I'm sorry, but I don't know what that means :-(" + newline
                + horizontalDivider;
        // Add exit message
        expected += horizontalDivider + indentation + "Goodbye friend." + newline
                + horizontalDivider;
        assertEquals(expected, output.toString(), "Should display invalid command message");
    }
}