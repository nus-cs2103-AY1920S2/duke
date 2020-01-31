package duke.ui;

import duke.task.Task;
import duke.task.Todo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * <h1>UiTest Class</h1>
 * Test for the Ui class
 *
 * @author  Eng Xuan En
 */
public class UiTest {
    OutputStream os;

    /**
     * Initialise before every test. Set the output to os.
     */
    @BeforeEach
    public void init() {
        os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);
    }

    /**
     * Reset the output to System.out after every test.
     */
    @AfterEach
    public void cleanUp() {
        PrintStream originalOut = System.out;
        System.setOut(originalOut);
    }

    /**
     * Test if the greet method print out the expected message or not.
     */
    @Test
    public void greetMethod_shouldProduceExpectedMessage() {
        Ui ui = new Ui();
        String expected = "    ____________________________________________________________\n"
                + "     Hello! I'm \n"
                + "                    ____        _        \n"
                + "                   |  _ \\ _   _| | _____ \n"
                + "                   | | | | | | | |/ / _ \\\n"
                + "                   | |_| | |_| |   <  __/\n"
                + "                   |____/ \\__,_|_|\\_\\___|\n"
                + "    \n" + "     What can I do for you?\n"
                + "    ____________________________________________________________\n";
        ui.greet();
        Assertions.assertEquals(expected, os.toString());
    }

    /**
     * Test if the method, replyDone, give the correct output to the user or not.
     */
    @Test
    public void deleteTask_shouldGiveTheCorrectOutput() {
        Ui ui = new Ui();
        Task task = new Todo("borrow book");
        String expected = "    ____________________________________________________________\n"
                + "     Nice! I've marked this task as done:\n"
                + "         [T][\u2718] borrow book\n"
                + "    ____________________________________________________________\n";
        ui.replyDone(task);
        Assertions.assertEquals(expected, os.toString());
    }
}
