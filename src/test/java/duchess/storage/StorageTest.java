package duchess.storage;

import com.google.gson.Gson;
import duchess.exception.DuchessException;
import duchess.task.Deadline;
import duchess.task.Event;
import duchess.task.Task;
import duchess.task.TaskList;
import duchess.task.ToDo;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * JUnit test class for {@code Storage}.
 */
public class StorageTest {
    /**
     * Tests the {@code load} method when the directory of the save
     * file does not exist.
     */
    @Test
    public void load_folderDoesNotExist_exceptionThrown() {
        try {
            Storage storageOne = new Storage("storageTestOne/data.json");
            storageOne.load();
            fail();
        } catch (DuchessException e) {
            assertEquals("Failed to load save file! Creating new save file.", e.getMessage());
            File testFolder = new File("storageTestOne");
            assertTrue(testFolder.exists() && testFolder.isDirectory());
        }
    }

    /**
     * Tests the {@code load} method when the save file itself
     * does not exist.
     */
    @Test
    public void load_saveFileDoesNotExist_exceptionThrown() {
        try {
            File testFolder = new File("storageTestTwo");
            if (!testFolder.exists()) {
                boolean isDirectoryCreated = testFolder.mkdir();
                if (!isDirectoryCreated) {
                    throw new DuchessException("Folder failed to be created!");
                }
            }
            Storage storageTwo = new Storage("storageTestTwo/data.json");
            storageTwo.load();
            fail();
        } catch (DuchessException e) {
            assertEquals("Failed to load save file! Creating new save file.", e.getMessage());
        }
    }

    /**
     * Tests the {@code load} method when the save file exists.
     *
     * @throws IOException      If an error is encountered when writing to the test
     *                          data.json.
     * @throws DuchessException If the storage fails to load the file.
     */
    @Test
    public void load_saveFileExists_success() throws IOException, DuchessException {
        File testFolder = new File("storageTestThree");
        if (!testFolder.exists()) {
            boolean isDirectoryCreated = testFolder.mkdir();
            if (!isDirectoryCreated) {
                throw new DuchessException("Folder failed to be created!");
            }
        }
        FileWriter fileWriter = new FileWriter("storageTestThree/data.json");
        Gson gson = new Gson();
        Task[] taskArray = new Task[3];
        taskArray[0] = new ToDo("Task number 1");
        taskArray[1] = new Event("Task number 2", "2-4pm", true);
        taskArray[2] = new Deadline("Task number 3", LocalDateTime.now());
        fileWriter.write(gson.toJson(taskArray, Task[].class));
        fileWriter.close();
        Storage storageThree = new Storage("storageTestThree/data.json");
        ArrayList<Task> taskArrayList = storageThree.load();
        assertEquals(3, taskArrayList.size());
        for (int i = 0; i < 3; i++) {
            assertEquals(taskArray[i].getDescription(), taskArrayList.get(i).getDescription());
            assertEquals(taskArray[i].isCompleted(), taskArrayList.get(i).isCompleted());
        }
        assertTrue(taskArrayList.get(1) instanceof Event);
        assertEquals(((Event) taskArray[1]).getTimeFrame(), ((Event) taskArrayList.get(1)).getTimeFrame());
        assertTrue(taskArrayList.get(2) instanceof Deadline);
        assertEquals(((Deadline) taskArray[2]).getDeadline(), ((Deadline) taskArrayList.get(2)).getDeadline());
    }

    /**
     * Tests the {@code load} and {@code save} methods for storage initialised with
     * nested directories as the file path.
     *
     * @throws DuchessException If the storage fails to load or save the file.
     */
    @Test
    public void loadAndSave_longFilePathWithoutFolder_exceptionThrown() throws DuchessException {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("Task number 1"));
        taskList.addTask(new Event("Task number 2", "2-4pm", true));
        taskList.addTask(new Deadline("Task number 3", LocalDateTime.now()));
        Storage storageFour = new Storage("storageTestFour/oneMoreFolder/data.json");
        try {
            storageFour.load();
            fail();
        } catch (DuchessException e) {
            assertEquals("Failed to load save file! Creating new save file.", e.getMessage());
            storageFour.save(taskList);
            File saveFile = new File("storageTestFour/oneMoreFolder/data.json");
            assertTrue(saveFile.exists());
        }
    }

    /**
     * Tests the {@code save} methods when an invalid file path is given.
     */
    @Test
    public void save_invalidFilePath_exceptionThrown() {
        try {
            Storage storageFive = new Storage("storageTestFive/data.json");
            TaskList taskList = new TaskList();
            storageFive.save(taskList);
            fail();
        } catch (DuchessException e) {
            assertEquals("Facing difficulties saving your tasks right now.", e.getMessage());
        }
    }

    /**
     * Tests the {@code save} method when a valid file path is given.
     *
     * @throws DuchessException If the storage fails to save.
     */
    @Test
    public void save_validFilePath_success() throws DuchessException {
        File folder = new File("storageTestSix");
        if (!folder.exists()) {
            boolean isDirectoryCreated = folder.mkdir();
            if (!isDirectoryCreated) {
                throw new DuchessException("Folder failed to be created!");
            }
        }
        Storage storageSix = new Storage("storageTestSix/data.json");
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("Go for a run"));
        taskList.addTask(new Event("Movie", "5-7pm"));
        taskList.addTask(new Deadline("Exercise", LocalDateTime.now(), true));
        storageSix.save(taskList);
        TaskList loadedTaskList = new TaskList(storageSix.load());
        assertEquals(3, loadedTaskList.size());
        for (int i = 0; i < 3; i++) {
            assertEquals(taskList.getTask(i).getDescription(), loadedTaskList.getTask(i).getDescription());
            assertEquals(taskList.getTask(i).isCompleted(), loadedTaskList.getTask(i).isCompleted());
        }
        assertTrue(loadedTaskList.getTask(1) instanceof Event);
        assertEquals(((Event) taskList.getTask(1)).getTimeFrame(),
                ((Event) loadedTaskList.getTask(1)).getTimeFrame());
        assertTrue(loadedTaskList.getTask(2) instanceof Deadline);
        assertEquals(((Deadline) taskList.getTask(2)).getDeadline(),
                ((Deadline) loadedTaskList.getTask(2)).getDeadline());
    }

    /**
     * Cleans up folders created when testing Storage.
     *
     * @throws DuchessException If directory fails to be deleted.
     */
    @AfterAll
    public static void cleanUp() throws DuchessException {
        String[] folders = {"storageTestOne", "storageTestTwo", "storageTestThree", "storageTestFour",
                "storageTestSix"};
        deleteDirectory("storageTestFour/oneMoreFolder");
        for (String folder : folders) {
            deleteDirectory(folder);
        }
    }

    private static void deleteDirectory(String path) throws DuchessException {
        Path rootPath = Paths.get(path);
        try (Stream<Path> walk = Files.walk(rootPath)) {
            walk.sorted(Comparator.reverseOrder())
                    .map(Path::toFile)
                    .forEach(File::delete);
        } catch (IOException e) {
            throw new DuchessException("Failed to delete directory!");
        }
    }
}
