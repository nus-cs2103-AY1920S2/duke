package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.DukeStorageDirectoryException;
import duke.exception.DukeStorageFileException;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class AddTaskCommandTest {
    private Ui ui;
    private Storage storage;
    private ByteArrayOutputStream output;
    private String saveFile = "test.txt";
    private String fileSeparator = File.separator;
    // Map project path to the directory from which you run your program
    private String projectRootPath = Paths.get("").toAbsolutePath().toString();
    private String dataDirectoryPath = projectRootPath + fileSeparator + "data";
    private String saveFilePath = dataDirectoryPath + fileSeparator + saveFile;

    private static Stream<Arguments> generateEmptyState() {
        Task task = new Todo("Read book");
        TaskList tasks = new TaskList();
        return Stream.of(
                Arguments.of(task, tasks));
    }

    private static Stream<Arguments> generateTaskListWithOneTask() {
        Task task = new Todo("Read book");
        TaskList tasks = new TaskList();
        tasks.addTask(task);
        return Stream.of(
                Arguments.of(task, tasks));
    }

    private void deleteSaveFile() {
        try {
            Files.deleteIfExists(Paths.get(saveFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    void setUp() {
        ui = new Ui();
        output = new ByteArrayOutputStream();
        try {
            storage = new Storage(saveFile);
        } catch (DukeStorageFileException | DukeStorageDirectoryException e) {
            e.printStackTrace();
        }
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    void tearDown() {
        // Delete save file
        deleteSaveFile();
    }

    @ParameterizedTest
    @MethodSource("generateEmptyState")
    void execute_emptyTaskList_addTask(Task task, TaskList tasks) {
        int tasksCount = tasks.size();
        // Execute function for testing
        Command command = new AddTaskCommand(task);
        command.execute(tasks, ui, storage);
        // Check addition of task is successful
        assertEquals(task.getDescription(), tasks.get(tasksCount).getDescription());
        assertEquals(tasksCount + 1, tasks.size());
        // Check save file
        try (BufferedReader reader = new BufferedReader(new FileReader(saveFilePath))) {
            assertEquals(task.stringToSaveToDisk(), reader.readLine());
        } catch (FileNotFoundException e) {
            fail("Could not open save file");
        } catch (IOException e) {
            fail("Could not read save file");
        }
    }

    @ParameterizedTest
    @MethodSource("generateTaskListWithOneTask")
    void execute_oneTaskInTaskList_addTask(Task task, TaskList tasks) {
        int tasksCount = tasks.size();
        assertEquals(1, tasksCount);
        Command command = new AddTaskCommand(task);
        command.execute(tasks, ui, storage);
        assertEquals(2, tasks.size());
        assertEquals(task.getDescription(), tasks.get(tasksCount).getDescription());
    }
}