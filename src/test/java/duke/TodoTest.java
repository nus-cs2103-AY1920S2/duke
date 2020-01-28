package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TodoTest {

    @Test
    void testToString() {
        assertEquals("[T][\u2713] Blah", new Todo("Blah", true).toString());
    }
}