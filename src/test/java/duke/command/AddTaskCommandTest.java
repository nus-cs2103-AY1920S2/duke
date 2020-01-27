package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class AddTaskCommandTest {
    Ui ui;
    Storage storage;
    ByteArrayOutputStream output;
    String saveFile = "test.txt";
    final String FILE_SEPARATOR = File.separator;
    // Map project path to the directory from which you run your program
    final String PROJECT_ROOT_PATH = Paths.get("").toAbsolutePath().toString();
    String dataDirectoryPath = PROJECT_ROOT_PATH + FILE_SEPARATOR + "data";
    String saveFilePath = dataDirectoryPath + FILE_SEPARATOR + saveFile;
    // String formatting
    final String INDENTATION = Ui.INDENTATION;
    final String HORIZONTAL_BAR = Ui.HORIZONTAL_BAR;
    final String NEWLINE = Ui.NEWLINE;
    final String HORIZONTAL_DIVIDER = INDENTATION + HORIZONTAL_BAR + NEWLINE;

    static Stream<Arguments> generateEmptyState() {
        Task task = new Todo("Read book");
        TaskList tasks = new TaskList();
        return Stream.of(
                Arguments.of(task, tasks));
    }

    void deleteSaveFile() {
        try {
            Files.deleteIfExists(Paths.get(dataDirectoryPath + FILE_SEPARATOR + saveFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    void setUp() {
        ui = new Ui();
        output = new ByteArrayOutputStream();
        storage = new Storage(saveFile);
        System.setOut(new PrintStream(output));
    }

    @AfterEach
    void tearDown() {
        // Delete save file
        deleteSaveFile();
    }

    @ParameterizedTest
    @MethodSource("generateEmptyState")
    void execute(Task task, TaskList tasks) {
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
}