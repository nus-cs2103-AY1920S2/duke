package duke.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    @Test
    public void testToSaveForm() {
        assertEquals("D , N , test , 2020-02-20\n", new Deadline("test", "2020-02-20").toSaveForm());
    }
}
