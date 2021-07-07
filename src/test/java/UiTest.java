import Togepi.Ui;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    @Test
    public void sayByeTest() {
        Ui ui = new Ui();
        String bye = ui.sayBye();
        assertEquals("Bye.", bye);
    }

    @Test
    public void showListMsgTest() {
        Ui ui = new Ui();
        String msg = ui.showListMsg();
        assertEquals("Here are the tasks in your list: ", msg);
    }
}
