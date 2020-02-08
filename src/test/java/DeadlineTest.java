import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testStringConversion() {
        Deadline test = new Deadline("Deadline Test",
                LocalDateTime.of(2020, 1, 1, 0, 0));
        assertEquals("[D][\u2717] Deadline Test (by: Jan 1 2020 0000) ", test.toString());
    }

    @Test
    public void testWriteFormatConversion() {
        Deadline test = new Deadline("Deadline Test",
                LocalDateTime.of(2020, 1, 1, 0, 0));
        assertEquals("D|Deadline Test|2020-01-01T00:00|0", test.writeFormat());
    }

    @Test
    public void checkCompletion_incompleteDeadline_makeComplete() {
        Deadline test = new Deadline("Deadline Test",
                LocalDateTime.of(2020, 1, 1, 0, 0));
        test.makeCompleted();
        assertEquals("D|Deadline Test|2020-01-01T00:00|1", test.writeFormat());
    }

    @Test
    public void addTag_anyDeadline_includedInWriteFormat() {
        Deadline test = new Deadline("Deadline Test",
                LocalDateTime.of(2020, 1, 1, 0, 0));
        test.addTag("fun");
        test.addTag("fun");
        assertEquals("D|Deadline Test|2020-01-01T00:00|0|#fun#fun", test.writeFormat());
    }

}
