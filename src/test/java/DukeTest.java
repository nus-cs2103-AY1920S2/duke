import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public static void dummyTest() {
        assertEquals(2, 2);
    }
    @Test
    public static void uiTest() {
        Duke duke = new Duke("./" + "data/duke.txt");
        //assertEquals("Hello! I'm Duke" + "\n" + "What can I do for you?", duke.run());

    }

    public static void main(String[] args) {
        uiTest();
    }
}