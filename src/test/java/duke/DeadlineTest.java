package duke;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeadlineTest {

    @Test
    void testToString() {
        assertEquals("[D][X] Blah (by: Oct 10 2019)", new Deadline("Blah", "2019-10-10").toString());
    }

    @Test
    void saveFormat() {
        assertEquals("D , 0 , Blah , 2019-10-10", new Deadline("Blah", "2019-10-10").saveFormat());
    }
}