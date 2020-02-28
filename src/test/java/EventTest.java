import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {
    @Test
    void write() {
        Task event = new Event("return book", LocalDate.parse("2020-12-10"));
        String expectedOutput = "[E][\u2718] return book (at: Dec 10 2020)";
    }
}
