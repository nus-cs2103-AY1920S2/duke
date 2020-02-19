import static org.junit.jupiter.api.Assertions.assertEquals;

import Backend.Cache;
import Backend.Exceptions.DukeException;
import Backend.TaskList;
import Backend.Objects.Task.Task;
import Backend.Switcher;

import Backend.Parsers.Parser;

public class DukeTest {

    TaskList taskList = new TaskList();
    Cache cache = new Cache();
    Switcher switcher = new Switcher(taskList, cache);

    public static void main(String[] args){

        DukeTest dukeTest = new DukeTest();

        try {
            dukeTest.markTaskAsDoneTest();
            dukeTest.addToListTest();
            parseSavedLineTest();
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public void addToListTest() throws DukeException {

        switcher.res("todo go home");
        switcher.res("deadline duke project /by 2020-01-31");
        switcher.res("event no sleep /at 2020-01-30");

        assertEquals( taskList.getList().get(0).toString(), "1.[T][N] go home");
        assertEquals( taskList.getList().get(1).toString(), "2.[D][N] duke project (by: 2020-01-31)");
        assertEquals( taskList.getList().get(2).toString(), "3.[E][N] no sleep (at: 2020-01-30)");
    }

    public void markTaskAsDoneTest() throws DukeException {

        switcher.res("done 2");

        assertEquals(taskList.getList().get(1).toString(), "[Y] duke project (by: 2020-01-31)");

    }

    public static void parseSavedLineTest() throws DukeException {

        String line = "2.[D][N] duke project (by: 2020-01-31)";

        Task task = Parser.parseSavedTask(line, 2);

        assertEquals( task.toString(), line);

    }

}
