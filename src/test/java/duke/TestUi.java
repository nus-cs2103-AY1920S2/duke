package duke;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUi {
    private Ui ui;

    @BeforeEach
    public void setup() {
        ui = new Ui();
    }

    @Test
    public void testShowGoodbye() {
        ui.showGoodbye();
        String expectedOutput = String.format("Bye! Please give a review if you like this program!");

        assertEquals(ui.getResponse().toString(), expectedOutput);
    }

    @AfterEach
    public void tearDown() {
        ui.clear();
    }
}
