import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void dummyTest() throws IOException {
        Deadline test1 = new Deadline("Run", true, LocalDate.parse("2020-09-09"));

        assertEquals("[D][✓] Run (by: 2020-09-09)", test1.toString());
        assertEquals("D | 1 | Run | 2020-09-09", test1.convert());
    }
}