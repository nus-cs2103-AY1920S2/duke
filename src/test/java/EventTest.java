import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {
    @Test
    public void EventTesting() {
        Event myEventTest = new Event("Eating", "2019-09-09 0900");
        assertEquals(myEventTest.toString(), "[E][N] Eating (at: Sep 9 2019 9:00am)");
    }
}
