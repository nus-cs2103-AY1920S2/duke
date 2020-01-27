package duke.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoTest {

    @Test
    void formatSavingName() {
        assertEquals("todo,homework,false\n", new Todo("homework", false).formatSavingName());
    }

    @Test
    void testToString() {
        assertEquals("[T][N] homework", new Todo("homework", false).toString());
    }
}