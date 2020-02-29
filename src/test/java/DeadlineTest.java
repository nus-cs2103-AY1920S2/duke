import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
import duke.task.*;

public class DeadlineTest {
    @Test
    public void DeadlineTest(){
        LocalDate date = LocalDate.parse("2020-01-30");
        Deadline deadline = new Deadline("IP Week 3", date);
        deadline.markAsDone();
        assertEquals("[D][O] IP Week 3 (by: 30 Jan 2020)", deadline.toString());
    }
}