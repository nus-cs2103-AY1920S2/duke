package lcduke;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testStringOutput() {
        Task task1 = new Deadline("description", "2019-08-01 23:08");
        assertEquals("[D][" + Character.toString((char)45) + "] description (by: "
                + "Aug 1 2019 23:08" + ")", task1.toString());
    }
}
