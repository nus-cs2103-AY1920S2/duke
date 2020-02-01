import seedu.java.util.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


public class DeadlineTest{
    @Test
    void testConvertTiming(){
        assertEquals("2019-07-01", new Deadline("Submit assignment", "/by 2019-07-01").getTiming().toString());
    }
}