package dukeproj;

import dukeproj.data.Schedule;
import dukeproj.tasks.Task;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {

    @Test
    public void printFileIntoList_emptyFile() {
        Storage storageTest = new Storage("./testdata/emptyFile.txt");
        assertEquals(new ArrayList<Task>(), storageTest.printFileIntoList(new Schedule()));
    }

    @Test
    public void printFileIntoList_badSeparator() {
        Storage storageTest = new Storage("./testdata/badSeparator.txt");
        assertEquals(new ArrayList<Task>(), storageTest.printFileIntoList(new Schedule()));
    }
}
