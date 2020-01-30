import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;

public class EventTest {
    @Test
    public void EventTest(){
        LocalDate date = LocalDate.parse("2020-04-25");
        Event task = new Event("CS2103T Exam", date);
        assertEquals("[E][X] CS2103T Exam (at: 25 Apr 2020)", task.toString());
    }
}