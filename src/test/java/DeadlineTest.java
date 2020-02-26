import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testDeadlineString() {
        String inputString = "homework /by 2020-02-12";
        String expectedString = "[D][" + "\u2718" + "] homework by: Feb 12 2020";
        assertEquals(expectedString, new Deadline(inputString).toString());
    }
}
