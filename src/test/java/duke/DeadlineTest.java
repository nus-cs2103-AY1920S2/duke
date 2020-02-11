package duke;

import duke.task.Deadline;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeadlineTest {

    @Test
    void generateSaveFileEntry_validDeadline_returnsEntry() {
        assertEquals("D | 0 | return book | 2020-01-10\n",
                new Deadline("return book", LocalDate.parse("2020-01-10")).generateSaveFileEntry());
        assertEquals("D | 1 | return book | 2020-10-01\n",
                new Deadline("return book", LocalDate.parse("2020-10-01"), true).generateSaveFileEntry());
    }

    @Test
    void toString_validDeadline_returnsString() {
        assertEquals("[D] [X] return book (by: Jan 10 2020)",
                new Deadline("return book", LocalDate.parse("2020-01-10")).toString());
        assertEquals("[D] [V] return book (by: Oct 1 2020)",
                new Deadline("return book", LocalDate.parse("2020-10-01"), true).toString());
    }
}