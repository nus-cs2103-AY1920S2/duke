package duke;
import org.junit.Test;

import java.io.OutputStream;
import java.io.PrintStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DukeTest {
    @Test
    public void testTaskList() {
        OutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);


        ToDo task = new ToDo("test");
        TaskList.addTask(task);
        assertEquals("Got it. I've added this task: \n"   + "[T][✗] test\n"  + "Now you have 1 tasks in the list.\n" , os.toString());

    }

    @Test
    public void testUi() {
        OutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os);
        System.setOut(ps);

        Ui ui = new Ui();
        ui.printErrorUnderstanding();
        assertEquals("☹ OOPS!!! I'm sorry, but I don't know what that means :-(" + System.getProperty("line.separator") , os.toString());


    }


}