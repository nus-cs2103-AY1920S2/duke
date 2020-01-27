package duke;

import duke.data.Calender;
import duke.tasks.Task;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {

    @Test
    public void printFileIntoList_emptyFile() {
        Storage storageTest = new Storage("./testdata/emptyFile.txt");
        assertEquals(new ArrayList<Task>(), storageTest.printFileIntoList(new Calender()));
    }

    @Test
    public void printFileIntoList_badSeparator() {
        Storage storageTest = new Storage("./testdata/badSeparator.txt");
        assertEquals(new ArrayList<Task>(), storageTest.printFileIntoList(new Calender()));
    }
}
