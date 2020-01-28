package jiachen.duke;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeadlineTaskTest {

    @Test
    void testInvalidConstructor() {
        Assertions.assertThrows(InvalidDukeFormatException.class, () -> {
            new DeadlineTask("", "");
        });
    }

    @Test
    void testInvalidDateConstructor() {
        Assertions.assertThrows(DateTimeParseException.class, () -> {
            new DeadlineTask("desc", "invalid date");
        });
    }

    @Test
    void testConstructor() {
        Assertions.assertDoesNotThrow(() -> {
            new DeadlineTask("this is a discription", "01/12/2020 0700");
        });
    }

    @Test
    void testToString() {
        try {
            DeadlineTask task = new DeadlineTask("hellololooloo there", "01/12/2020 0700");
            assertEquals(task.toString(), "[D][✘] hellololooloo there (by: Dec 1 2020)");
        } catch (InvalidDukeFormatException e) {
            e.printStackTrace();
        }
    }

    @Test
    void format() {
        try {
            DeadlineTask task = new DeadlineTask("hellololooloo there", "01/12/2020 0700");
            assertEquals(task.format(), "D | 0 | hellololooloo there | 01/12/2020 0700");
        } catch (InvalidDukeFormatException e) {
            e.printStackTrace();
        }
    }
}