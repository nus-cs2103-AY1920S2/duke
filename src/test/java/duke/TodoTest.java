package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TodoTest {

    @Test
    void testToString() {
        assertEquals("[T][O] Blah", new Todo("Blah", true).toString());
    }
}