package duke.main;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {

    @Test
    public void exitDukeTest() {
        UiHandler ui = new UiHandler();
        assertEquals(ui.isExit(), false);
        ui.exitDuke();
        assertEquals(ui.isExit(), true);
    }

    @Test
    public void getResponseTest() {
        UiHandler ui = new UiHandler();
        ui.setResponse("hello");
        assertEquals(ui.getResponse(), "hello");
    }
}
