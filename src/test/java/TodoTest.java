import org.junit.jupiter.api.Test;

import static  org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {

    @Test
    public void testStringConversion() {
        assertEquals("[T][\u2718] say hihi", new Todo("say hihi").toString());
    }

    @Test
    public void testDataConversion() {
        assertEquals("T|0|say hihi", new Todo("say hihi").getData());
    }
}
