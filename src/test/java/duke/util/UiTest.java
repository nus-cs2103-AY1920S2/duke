package duke.util;



import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UiTest {

    @Test
    public void testSetBorder() {
        String expected = Ui.BORDER + "\n\ttesting border\n\n" + Ui.BORDER;
        assertEquals(expected, Ui.setBorder("testing border"));
    }
}
