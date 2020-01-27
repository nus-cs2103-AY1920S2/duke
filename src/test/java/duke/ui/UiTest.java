package duke.ui;

import duke.task.Task;
import duke.task.Todo;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class UiTest {
    OutputStream os;
    @BeforeEach
    public void init() {
        os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);
    }

    @AfterEach
    public void cleanUp(){
        PrintStream originalOut = System.out;
        System.setOut(originalOut);
    }


    @Test
    public void greetMethod_ShouldProduceExpectedMessage() {
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

    @Test
    public void deleteTask_ShouldGiveTheCorrectOutput() {
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
