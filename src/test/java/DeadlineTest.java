import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void testDeadline() throws Throwable {

        Deadline deadline = new Deadline("testing","10/10/2020 2359");

        LocalDate date = deadline.get_Date();

        String date_string = date.toString();

        assertEquals("2020-10-10",date_string);


    }
}