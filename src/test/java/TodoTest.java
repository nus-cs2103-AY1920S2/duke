import org.junit.jupiter.api.Test;

import duke.task.Todo;

import static  org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void testGetDescription() {
        assertEquals("say hihi", new Todo("say hihi").getDescription());
    }

    @Test
    public void testDataConversion() {
        assertEquals("T|0|say hihi", new Todo("say hihi").getData());
    }
}
