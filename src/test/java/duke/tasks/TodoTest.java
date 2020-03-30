package duke.tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void testToSaveForm() {
        assertEquals("T , N , test\n", new Todo("test").toSaveForm());
    }
}
