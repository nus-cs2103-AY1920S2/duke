import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Tests the Storage class.
 */
public class StorageTest {

    @Test
    public void load_testFile_success() {
        ArrayList<String> tasks = new ArrayList<String>();

        tasks.add(new ToDo("borrow book", 'T').obtainTaskInfo());
        tasks.add(new Deadline("CS2103 Submission", 'D',
                LocalDate.parse("2020-01-26"), LocalTime.parse("23:59")).obtainTaskInfo());
        tasks.add(new Event("CNY", 'E',
                LocalDate.parse("2020-01-25"), LocalTime.parse("12:00")).obtainTaskInfo());

        Storage storage = new Storage("./test.txt");
        ArrayList<Task> loadedTasks;

        try {
            loadedTasks = storage.load();
        } catch (FileNotFoundException exception) {
            System.out.println(exception);
            loadedTasks = new ArrayList<>();
        }

        ArrayList<String> loadedTasksInfo = new ArrayList<String>();

        for (int j = 0; j < 3; j++) {
            loadedTasksInfo.add(loadedTasks.get(j).obtainTaskInfo());
        }

        Assertions.assertEquals(tasks, loadedTasksInfo);
    }
}
