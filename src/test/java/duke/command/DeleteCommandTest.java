package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeStorageDirectoryException;
import duke.exception.DukeStorageFileException;
import duke.task.EventStub;
import duke.task.TaskList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;


class DeleteCommandTest {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;
    private ByteArrayOutputStream output;
    private String saveFile = "test.txt";
    private String fileSeparator = File.separator;
    // Map project path to the directory from which you run your program
    private String projectRootPath = Paths.get("").toAbsolutePath().toString();
    private String dataDirectoryPath = projectRootPath + fileSeparator + "data";
    private String saveFilePath = dataDirectoryPath + fileSeparator + saveFile;
    private static final String doneStatusIcon = "\u2713"; // Check mark icon
    private static final String incompleteStatusIcon = "\u2718"; // Cross mark iconprivate
    private EventStub eventTask = new EventStub("project meeting", "2020-01-01",
            false, "event,0,project meeting,2020-01-01",
            doneStatusIcon, incompleteStatusIcon,
            "[E][" + incompleteStatusIcon + "] project meeting " + "(at: Jan 1 2020)");

    private void deleteSaveFile() {
        try {
            Files.deleteIfExists(Paths.get(saveFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    void setUp() {
        tasks = new TaskList();
        ui = new Ui();
        try {
            storage = new Storage(saveFile);
        } catch (DukeStorageFileException | DukeStorageDirectoryException e) {
            e.printStackTrace();
        }
        // Redirect stdout to own PrintStream
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    void tearDown() {
        // Remove save file
        deleteSaveFile();
        System.setOut(System.out);
    }

    @Test
    void execute_validDeleteCommand() {
        assertEquals(0, tasks.size());
        // Add one task
        tasks.addTask(eventTask);
        assertEquals(1, tasks.size());
        // Create a delete command and execute it
        int taskNumber = 1;
        DeleteCommand deleteCommand = new DeleteCommand(taskNumber);
        deleteCommand.execute(tasks, ui, storage);
        // Check save file, should be empty
        try (BufferedReader reader = new BufferedReader(new FileReader(saveFilePath))) {
            assertNull(reader.readLine());
        } catch (FileNotFoundException e) {
            fail("Could not open save file");
        } catch (IOException e) {
            fail("Could not read save file");
        }
        // Check size of task list
        assertEquals(0, tasks.size());
    }
}