package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeadlineTest {

    @Test
    void testToString() {
        assertEquals("[D][\u2718] Blah (by: Oct 10 2019)", new Deadline("Blah", "2019-10-10").toString());
    }

    @Test
    void saveFormat() {
        assertEquals("D , 0 , Blah , 2019-10-10", new Deadline("Blah", "2019-10-10").saveFormat());
    }
}