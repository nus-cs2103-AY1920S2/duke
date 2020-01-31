package duke;

import duke.Ui;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UiTest {

    private String lines = "        ____________________________________________________________";
    private Ui ui = new Ui();

    @Test
    void printByeTest() {
        // For redirection of System.out.println.
        // Allow methods with void to be tested as well.
        OutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);
        ui.printBye();
        assertEquals(lines + System.lineSeparator() + "        Bye. Hope to see you again soon"
                + System.lineSeparator() + lines + System.lineSeparator(), os.toString());
    }


}
