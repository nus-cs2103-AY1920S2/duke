package duke;

import duke.task.TaskListHistory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DukeTest {
    private String fileSeparator = File.separator;
    // Map project path to the directory from which you run your program
    private String projectRootPath = Paths.get("").toAbsolutePath().toString();
    private String dataDirectoryPath = projectRootPath + fileSeparator + "data";
    private String saveFile = "test.txt";
    private String newline = System.lineSeparator();
    private String indentation = "    ";
    private String horizontalBar =
            "____________________________________________________________";
    private String horizontalDivider = indentation + horizontalBar + newline;
    private String exceptionIcon = "\u2639"; // Sad face icon
    private Duke duke;
    private ByteArrayOutputStream output;

    void deleteSaveFile() {
        try {
            Files.deleteIfExists(Paths.get(dataDirectoryPath + fileSeparator + saveFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String greeting() {
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

    private String exitMessage() {
        return horizontalDivider + indentation + "Goodbye friend." + newline
                + horizontalDivider;
    }

    @BeforeEach
    void init() {
        deleteSaveFile();
        duke = new Duke(saveFile);
        output = new ByteArrayOutputStream();
        // Change output stream
        System.setOut(new PrintStream(output));
        TaskListHistory.reset();
    }

    @AfterEach
    void cleanUp() {
        deleteSaveFile();
    }

    @Test
    @DisplayName("duke.Duke: Test for undo command")
    void undo_emptyTaskList_noAction() {
        String input = "undo" + newline + "bye" + newline;
        duke.run(new BufferedReader(
                new InputStreamReader(new ByteArrayInputStream(input.getBytes()))));
        String expected = greeting();
        expected += horizontalDivider + indentation
                + "Nothing to undo..." + newline
                + horizontalDivider;
        expected += exitMessage();
        assertEquals(expected, output.toString(),
                "Should display correct response for nothing to undo");
    }

    @Test
    @DisplayName("duke.Duke: Test for invalid command")
    void run_invalidCommand_displayInvalidCommandMessage() {
        String input = "blah" + newline + "bye" + newline;
        duke.run(new BufferedReader(
                new InputStreamReader(new ByteArrayInputStream(input.getBytes()))));
        // Check exception message
        String expected = greeting();
        expected += horizontalDivider + indentation + exceptionIcon
                + " OOPS!!! I'm sorry, but I don't know what that means :-(" + newline
                + horizontalDivider;
        // Add exit message
        expected += exitMessage();
        assertEquals(expected, output.toString(), "Should display invalid command message");
    }
}