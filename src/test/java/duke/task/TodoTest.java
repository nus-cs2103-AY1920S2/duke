package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TodoTest {
    @Test
    public void testStringConversion() {
        assertEquals("[T][\u2718] Deedoo", new Todo("Deedoo").toString());
    }
}