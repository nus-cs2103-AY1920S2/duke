import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testTaskDate() {
        Deadline d = new Deadline("deadline", "31/01/2020 1800");
        assertEquals("31/01/2020 1800", d.getBy());
    }

    @Test
    public void testDate() {
        Deadline d = new Deadline("deadline", "2/12/2019 1800");
        assertEquals("Dec 2 2019 6 PM", d.getDate("2/12/2019 1800"));
    }
}
