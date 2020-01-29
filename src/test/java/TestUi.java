import duke.Ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUi {
    private final PrintStream systemOutput = System.out;
    private final PrintStream systemError = System.err;

    private final ByteArrayOutputStream testOutput = new ByteArrayOutputStream();
    private final ByteArrayOutputStream testError = new ByteArrayOutputStream();

    private final Ui ui = new Ui();

    @BeforeEach
    public void setup() {
        System.setOut(new PrintStream(testOutput));
        System.setErr(new PrintStream(testError));
    }

    @Test
    public void testShowGoodbye() {
        ui.showGoodbye();
        String expectedOutput = String.format(""
                + "     Bye! Please give a review if you like this program!\n\n");

        assertEquals(testOutput.toString(), expectedOutput);
    }

    @AfterEach
    public void flush() {
        System.setOut(systemOutput);
        System.setErr(systemError);
    }
}
