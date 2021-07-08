package duke;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class DeadlineTest {
//    @Test
//    public void toString_validInput_success() {
//        assertEquals("[D][✘] test description (date: 20 APRIL 2015)",
//                new Deadline("test description", LocalDate.of(2015,04, 20)).toString());
//    }

    @Test
    public void toFileFormat_validInput_success() {
        assertEquals("D | 0 | test description | 20/04/2015",
                new Deadline("test description", LocalDate.of(2015, 04, 20)).toFileFormat());
    }
}