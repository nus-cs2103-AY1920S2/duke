package jiachen.duke;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EventTaskTest {

    @Test
    void testInvalidConstructor() {
        Assertions.assertThrows(
            InvalidDukeFormatException.class,
            () -> {
                new EventTask("", "");
            });
    }

    @Test
    void testInvalidDateConstructor() {
        Assertions.assertThrows(
            InvalidDukeFormatException.class,
            () -> {
                new EventTask("desc", "invalid date");
            });
    }

    @Test
    void testConstructor() {
        Assertions.assertDoesNotThrow(
            () -> {
                new EventTask("this is a discription", "01/12/2020 0700");
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
            EventTask task = new EventTask("hellololooloo there", "01/12/2020 0700");
            assertEquals("[E][✘] hellololooloo there (at: Dec 1 2020)", task.toString());
        } catch (InvalidDukeFormatException e) {
            e.printStackTrace();
        }
    }

    @Test
    void format() {
        try {
            EventTask task = new EventTask("hellololooloo there", "01/12/2020 0700");
            assertEquals(task.format(), "E | 0 | hellololooloo there | 01/12/2020 0700");
        } catch (InvalidDukeFormatException e) {
            e.printStackTrace();
        }
    }
}
