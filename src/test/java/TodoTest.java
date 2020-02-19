
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;



public class TodoTest {

    @Test
    public void testsaveToHardDiskFormat() {
        assertEquals("T | 0 | eat dinner ", new Todo("eat dinner ").saveToHardDiskFormat());
        assertEquals("T | 0 | run ", new Todo("run ").saveToHardDiskFormat());
        assertEquals("T | 0 | Sing song ", new Todo("Sing song ").saveToHardDiskFormat());
    }
}
