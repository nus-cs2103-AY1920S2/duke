import duke.Storage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StorageTest {
    @Test
    public void testConvertMonthToInt() {
        Storage storage = new Storage("data/duke.txt");
        assertEquals("03", storage.convertMonthToInt("Mar"));
    }

    @Test
    public void testConvertDateFormat() {
        Storage storage = new Storage("data/duke.txt");
        assertEquals("2020-02-02", storage.convertDateFormat("Feb 2 2020"));
    }
}