package jiachen.duke;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeadlineTaskTest {

    @Test
    void testInvalidConstructor() {
        Assertions.assertThrows(
            InvalidDukeFormatException.class,
            () -> {
                new DeadlineTask("", "");
            });
    }

    @Test
    void testConstructor() {
        Assertions.assertDoesNotThrow(
            () -> {
                new DeadlineTask("this is a discription", "01/12/2020 0700");
            });
    }

    @Test
    void testEmptyDescription() {
        Assertions.assertThrows(
            InvalidDukeFormatException.class,
            () -> {
                new DeadlineTask("", "01/12/2020 0700");
            });
    }

    @Test
    void testInvalidTimestamp() {
        Assertions.assertThrows(
            InvalidDukeFormatException.class,
            () -> {
                new DeadlineTask("", "01/12/2020 0700");
            });
    }

    @Test
    void testToString() {
        try {
            DeadlineTask task = new DeadlineTask("hellololooloo there", "01/12/2020 0700");
            assertEquals(task.toString(), "[D][✘] hellololooloo there (by: 01/12/2020 0700)");
        } catch (InvalidDukeFormatException e) {
            e.printStackTrace();
        }
    }

    @Test
    void format() {
        try {
            DeadlineTask task = new DeadlineTask("hellololooloo there", "01/12/2020 0700");
            assertEquals("D | 0 | hellololooloo there | 01/12/2020 0700", task.format());
        } catch (InvalidDukeFormatException e) {
            e.printStackTrace();
        }
    }
}
