import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class EventTest {
    @Test
    public void testEventString() {
        String inputString = "dinner /at formosa";
        String expectedString = "[E][" + "\u2718" + "] " + "dinner at: formosa";
        assertEquals(expectedString, new Event(inputString).toString());
    }
}
