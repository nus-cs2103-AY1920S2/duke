package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TodoTest {

    @Test
    void testToString() {
        assertEquals("[T] [n] testing",
                new Todo("testing").toString());
    }

    @Test
    void saveString() {
        assertEquals("T | 0 | testing",
                new Todo("testing").saveString());
    }
}