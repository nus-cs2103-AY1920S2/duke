import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void todoTask() throws DukeException {
        Todo taskTest = new Todo("testing");
        assertEquals("[T][✘] testing", taskTest.toString());
    }

    @Test
    public void uiTest() {
        Ui ui = new Ui();
        assertEquals("----------------------------------------------", ui.botReplyLine);
    }

}