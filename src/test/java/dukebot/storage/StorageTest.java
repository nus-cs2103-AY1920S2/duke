package dukebot.storage;

import dukebot.exception.DukeException;
import dukebot.tasklist.Deadline;
import dukebot.tasklist.Event;
import dukebot.tasklist.Task;
import dukebot.tasklist.TaskList;
import dukebot.tasklist.Todo;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StorageTest {
    @Test
    void testListSavedIsListLoaded() {
        Storage storage = new Storage("./storageTest.txt");
        LocalDateTime testTime = LocalDateTime.of(2020,1,2,0,0);
        ArrayList<Task> taskArrayList = new ArrayList<Task>();
        TaskList taskList = new TaskList(taskArrayList);
        taskList.addTask(new Todo("test todo"));
        taskList.addTask(new Deadline("test deadline", testTime));
        taskList.addTask(new Event("test event", testTime));
        try {
            storage.saveToFile(taskList);
            assertEquals(taskArrayList, storage.loadFromFile());
        } catch (DukeException e) {
            assert false;
        }
    }

}