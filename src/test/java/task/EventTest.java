package task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class EventTest {

    @Test
    public void testEvent() {
        Task event = new Event("IHG", "2020-02-02");
        String expectedText = "E/N/IHG/2020-02-02";
        String actualText = event.toStringTxt();
        assertEquals(expectedText, actualText);
        String expectedText2 = "[E][N] IHG (at: Feb 2 2020)";
        String actualText2 = event.toString();
        assertEquals(expectedText2, actualText2);
    }
}
