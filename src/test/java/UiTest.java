import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {
    @Test
    public static void dummyTest() {
        assertEquals(2, 2);
    }
    @Test
    public static void uiTest() {
        Ui ui = new Ui();
        assertEquals("Hello! I'm Duke" + "\n" + "What can I do for you?", ui.runGreeting());

    }

    public static void main(String[] args) {
        uiTest();
    }
}