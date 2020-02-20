import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class DukeTest {
    private Ui uiDispay = new Ui();
    private Storage fileStorage =  new Storage();
    private Parser commandParser = new Parser();


    @Test
    public void byeCommadTest(){
        commandParser = new Parser("bye",uiDispay,fileStorage);
        assertEquals("  --------------\n" +
                "    Bye. Hope to see you again\n" +
                "  --------------",uiDispay.returnExitsMessage());
    }

    @Test
    public void todoCommandTest() throws Exception {
        commandParser = new Parser("todo read book",uiDispay,fileStorage);

        ArrayList<Task> arrTask = new ArrayList<Task>();
        String filePath = "duke.txt";
        File f = new File(filePath);
        assertEquals("----------------- \n" +
                "   Got it. I've added this task:\n" +
                "   [T][✗] read book \n" +
                "   Now you have 1 tasks in the list.\n" +
                "-----------------",commandParser.todoTaskCommand("todo read book",arrTask,uiDispay,f));
    }


    @Test
    public void doneCommandTest() throws Exception {
        commandParser = new Parser("done 1",uiDispay,fileStorage);
        ArrayList<Task> arrTask = new ArrayList<Task>();
        assertEquals("  Sorry there is no any task for you to do the command",commandParser.doneCommand("done 1",arrTask,uiDispay));
    }



}
