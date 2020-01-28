import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testOne() {
        Deadline deadline = new Deadline("return book", "02-02-2020 1600", false);
        assertEquals(deadline.getDescription(), "return book");
    }
}