package duke.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;

import duke.exception.DuchessException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

/**
 * JUnit test class for {@code Storage}.
 */
public class StorageTest {
    /**
     * Tests the {@code load} method when the directory of the save file does not
     * exist.
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
     * Tests the {@code load} method when the save file itself does not exist.
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
        // Preparing list of active tasks
        Task[] taskArray = new Task[3];
        taskArray[0] = new ToDo("Task number 1");
        taskArray[1] = new Event("Task number 2", "2-4pm", true, LocalDateTime.now());
        taskArray[2] = new Deadline("Task number 3", LocalDateTime.now());

        // Preparing list of archived tasks
        Task[] archiveArray = new Task[3];
        archiveArray[0] = new ToDo("Archived task number 1", true, LocalDateTime.now());
        archiveArray[1] = new Event("Archived task number 2", "2-4pm", true, LocalDateTime.now());
        archiveArray[2] = new Deadline("Archived Task number 3", LocalDateTime.now(), true, LocalDateTime.now(), true);

        // Writing the files
        FileWriter fileWriter = new FileWriter("storageTestThree/data.json");
        Gson gson = new Gson();
        StorageContainer storageContainer = new StorageContainer(taskArray, archiveArray);
        fileWriter.write(gson.toJson(storageContainer, StorageContainer.class));
        fileWriter.close();

        // Testing
        Storage storageThree = new Storage("storageTestThree/data.json");
        ArrayList<ArrayList<Task>> loadedTasks = storageThree.load();

        // Checking if all active tasks are correctly retrieved
        ArrayList<Task> taskArrayList = loadedTasks.get(0);
        assertEquals(3, taskArrayList.size());
        for (int i = 0; i < 3; i++) {
            assertEquals(taskArray[i].getDescription(), taskArrayList.get(i).getDescription());
            assertEquals(taskArray[i].isCompleted(), taskArrayList.get(i).isCompleted());
            assertEquals(taskArray[i].getCompletionTime(), taskArrayList.get(i).getCompletionTime());
        }
        assertTrue(taskArrayList.get(1) instanceof Event);
        assertEquals(((Event) taskArray[1]).getTimeFrame(), ((Event) taskArrayList.get(1)).getTimeFrame());
        assertTrue(taskArrayList.get(2) instanceof Deadline);
        assertEquals(((Deadline) taskArray[2]).getDeadline(), ((Deadline) taskArrayList.get(2)).getDeadline());

        // Checking if all archived tasks are correctly retrieved
        ArrayList<Task> archiveArrayList = loadedTasks.get(1);
        assertEquals(3, archiveArrayList.size());
        for (int i = 0; i < 3; i++) {
            assertEquals(archiveArray[i].getDescription(), archiveArrayList.get(i).getDescription());
            assertEquals(archiveArray[i].isCompleted(), archiveArrayList.get(i).isCompleted());
            assertEquals(archiveArray[i].getCompletionTime(), archiveArrayList.get(i).getCompletionTime());
        }
        assertTrue(archiveArrayList.get(1) instanceof Event);
        assertEquals(((Event) archiveArray[1]).getTimeFrame(), ((Event) archiveArrayList.get(1)).getTimeFrame());
        assertTrue(archiveArrayList.get(2) instanceof Deadline);
        assertEquals(((Deadline) archiveArray[2]).getDeadline(), ((Deadline) archiveArrayList.get(2)).getDeadline());
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
        taskList.addTask(new Event("Task number 2", "2-4pm", true, LocalDateTime.now()));
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
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDo("Go for a run"));
        taskList.addTask(new Event("Movie", "5-7pm"));
        taskList.addTask(new Deadline("Exercise", LocalDateTime.now(), true, LocalDateTime.now(), true));
        Storage storageSix = new Storage("storageTestSix/data.json");
        storageSix.save(taskList);
        ArrayList<ArrayList<Task>> loadedTasks = storageSix.load();
        TaskList loadedTaskList = new TaskList(loadedTasks.get(0), loadedTasks.get(1));
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
        ArrayList<String> folders = new ArrayList<>(Arrays.asList("storageTestOne", "storageTestTwo",
                "storageTestThree", "storageTestFour", "storageTestSix"));
        deleteDirectory("storageTestFour/oneMoreFolder");
        for (String folder : folders) {
            deleteDirectory(folder);
        }
    }

    private static void deleteDirectory(String path) throws DuchessException {
        Path rootPath = Paths.get(path);
        try (Stream<Path> walk = Files.walk(rootPath)) {
            walk.sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
        } catch (IOException e) {
            throw new DuchessException("Failed to delete directory!");
        }
    }
}
