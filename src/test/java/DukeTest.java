import static org.junit.jupiter.api.Assertions.*;
import helper.Command;
import helper.Parser;
import helper.Ui;
import org.junit.jupiter.api.Test;
import task.Task;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class DukeTest {
    private Ui uiDispay = new Ui();
    private Parser commandParser = new Parser();
    private Command command = new Command();

    @Test
    public void byeCommadTest() throws Exception {
        commandParser = new Parser(uiDispay);
        command = commandParser.parse("bye");
        ArrayList<Task> tList = new ArrayList<>();
        assertEquals("  --------------\n" +
                "    Bye. Hope to see you again\n" +
                "  --------------",command.execute(tList,uiDispay));
    }

    @Test
    public void todoCommandTest() throws Exception {
        commandParser = new Parser(uiDispay);
        command = commandParser.parse("todo read book");
        ArrayList<Task> tList = new ArrayList<>();
        assertEquals("----------------- \n" +
                "   Got it. I've added this task:\n" +
                "   [T][NoDone] read book  tag:\n" +
                "   Now you have 1 tasks in the list.\n" +
                "-----------------  ",command.execute(tList,uiDispay));
    }

    @Test
    public void eventCommandTest() throws Exception{
        commandParser = new Parser(uiDispay);
        command = commandParser.parse("event project meeting /at 2019-10-15");
        ArrayList<Task> tList = new ArrayList<>();
        assertEquals("----------------- \n" +
                "   Got it. I've added this task:\n" +
                "     [E][NoDone] project meeting (Tue, Oct 15 2019)\n" +
                "   Now you have 1 tasks in the list.\n" +
                "-----------------  ",command.execute(tList,uiDispay));
    }

    @Test
    public void deadlineCommandTest() throws Exception {
        commandParser = new Parser(uiDispay);
        command = commandParser.parse("deadline return book /by 2019-11-15");
        ArrayList<Task> tList = new ArrayList<>();
        assertEquals("----------------- \n" +
                "   Got it. I've added this task:\n" +
                "     [D][NoDone] return book (Fri, Nov 15 2019)\n" +
                "   Now you have 1 tasks in the list.\n" +
                "-----------------  ",command.execute(tList,uiDispay));
    }

    @Test
    public void doneCommandTest() throws Exception {
        commandParser = new Parser(uiDispay);
        command = commandParser.parse("done 1");
        ArrayList<Task> tList = new ArrayList<>();
        assertEquals("  Sorry there is no any task for you to do the command",command.execute(tList,uiDispay));
    }
    
}
