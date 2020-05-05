import seedu.java.util.Deadline;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class DeadlineTest {
    @Test
    void testGetTiming() {
        assertEquals("/by 2019-07-01", new Deadline("Submit assignment", "/by 2019-07-01").getTiming().toString());
    }
}