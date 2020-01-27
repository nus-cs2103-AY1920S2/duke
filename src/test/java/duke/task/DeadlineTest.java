package duke.task;

import duke.DukeException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.fail;
 
class DeadlineTest {
    @Test
    void constructorTest_validDateTime_success() throws Exception {
        Deadline deadline = new Deadline("return book", "2020-02-02 18:00");
        assertEquals("return book", deadline.description);
        assertEquals("2020-02-02", deadline.date.toString());
        assertEquals("18:00", deadline.time.toString());
    }

    @Test
    void constructorTest_invalidDateTimeFormat_exceptionThrown() {
        try {
            Deadline deadline = new Deadline("return book", "2020-02-02 6pm");
            fail();
        } catch (DukeException e) {
            assertEquals("Incorrect date or time format. Format required: yyyy-mm-dd hh:mm", e.getMessage());
        }
    }

    @Test
    void constructorTest_missingDateTime_exceptionThrown() {
        try {
            Deadline deadline = new Deadline("return book", "2020-02-02");
            fail();
        } catch (DukeException e) {
            assertEquals("Incorrect date or time format. Format required: yyyy-mm-dd hh:mm", e.getMessage());
        }
    }

    @Test
    void constructorTest2() throws Exception {
        Deadline deadline = new Deadline("return book", "2020-02-02 18:00", false);
        assertEquals("return book", deadline.description);
        assertEquals("2020-02-02", deadline.date.toString());
        assertEquals("18:00", deadline.time.toString());
        assertFalse(deadline.isDone);
    }

    @Test
    void getDate() throws Exception {
        Deadline deadline = new Deadline("return book", "2020-02-02 18:00");
        assertEquals("2020-02-02", deadline.getDate().toString());
    }
    
    @Test
    void formatToSave() throws Exception {
        Deadline deadline = new Deadline("return book", "2020-02-02 18:00");
        assertEquals("D | 0 | return book | 2020-02-02 18:00", deadline.formatToSave());
    }

    @Test
    void stringConversion() throws Exception {
        Deadline deadline = new Deadline("return book", "2020-02-02 18:00");
        assertEquals("[D][N] return book (by: 2 Feb 2020 18:00)", deadline.toString());
    }
}
