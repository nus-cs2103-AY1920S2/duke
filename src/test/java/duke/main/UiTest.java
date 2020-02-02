package duke.main;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {

    @Test
    public void exitDukeTest() {
        Ui ui = new Ui();
        assertEquals(ui.isExit(), false);
        ui.exitDuke();
        assertEquals(ui.isExit(), true);
    }

    @Test
    public void getResponseTest() {
        Ui ui = new Ui();
        ui.setResponse("hello");
        assertEquals(ui.getResponse(), "hello");
    }
}
