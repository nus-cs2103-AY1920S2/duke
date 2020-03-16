import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void getDateAt() {
        Event event = new Event("project meeting", LocalDate.now());
        assertEquals(LocalDate.now(), event.getDateAt());
    }
}
