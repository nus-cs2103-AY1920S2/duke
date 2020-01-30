import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import Task.DukeException;
import Task.TaskList;
import Task.Task;

import Utils.Parser;

public class DukeTest {

    public static void main(String[] args){
        try {
            markTaskAsDoneTest();
            addToListTest();
            parseSavedLineTest();
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public static void addToListTest() throws DukeException {

        TaskList taskList = new TaskList();

        taskList.addTask("todo go home");
        taskList.addTask("deadline duke project /by 2020-01-31");
        taskList.addTask("event no sleep /at 2020-01-30");

        assertEquals( taskList.list.get(0).toString(), "1.[T][N] go home");
        assertEquals( taskList.list.get(1).toString(), "2.[D][N] duke project (by: 2020-01-31)");
        assertEquals( taskList.list.get(2).toString(), "3.[E][N] no sleep (at: 2020-01-30)");
    }

    public static void markTaskAsDoneTest() {

        Task task = new Task("Homework", 0);

        task.markAsDone();

        assertEquals(task.toString(), "[Y] Homework");

    }

    public static void parseSavedLineTest(){

        String line = "2.[D][N] duke project (by: 2020-01-31)";

        Task task = Parser.parseTask(line, 2);

        assertEquals( task.toString(), line);

    }

}
