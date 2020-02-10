import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;




public class EventTest {
    @Test
    public void eventTest() {
        assertEquals(new Event("Pass everything", "Aug 6th 2-4pm").toString(), "[E][\u2718] Pass everything (at: Aug 6th 2-4pm)");
    }
}
