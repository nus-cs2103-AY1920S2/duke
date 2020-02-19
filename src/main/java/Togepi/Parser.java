package Togepi;

import Togepi.Tasks.Task;
import Togepi.Tasks.TaskList;
import javafx.application.Platform;

import java.util.ArrayList;

/** Represents a parser that parses input string
 *    to determine what command is run.
 */
public class Parser {
    private static int counter;
    private ArrayList<Task> tasklist;

    /** Creates a parser object which takes in an arraylist
     *     of tasks which can be modified based on the command
     *     entered and a counter that keeps track of the number
     *     of tasks in the arraylist.
     * @param tasklist is the list of tasks in the file
     * @param counter is the total number of tasks in the file
     */
    public Parser(ArrayList<Task> tasklist, int counter) {
        this.tasklist = tasklist;
        this.counter = tasklist.size();
    }

    /** Filters through the list of valid commands that can be entered to
     *    modify the list of tasks and chooses the right command.
     * @param nextString is the command entered by the user and usually
     *     contains a command and some times a description of the task.
     * @param tasks is the current list of tasks before the modification
     * @return the arraylist of tasks after the modfication
     */
    public static String parseCommand(String nextString, TaskList tasks, Storage storage) throws DukeException {
        Ui ui = new Ui();
        if (nextString.equals("bye")) {
            Platform.exit();
        } else if (nextString.trim().equals("list")) {
            assert counter > 0 : "You can't get a list of tasks if you don't have anything in it!";
            return tasks.listTasks();
        } else if (nextString.trim().equals("help")) {
            return ui.showHelp();
        } else if (nextString.trim().substring(0,4).contains("done")) {
            assert counter > 0 : "You need to have a task to complete it!";
            return tasks.markComplete(nextString, storage);
        } else if (nextString.trim().substring(0,4).contains("find")) {
            return tasks.findTask(nextString);
        } else if (nextString.trim().substring(0,4).contains("todo")) {
            assert nextString.length() > 5 : "You can't just set a todo if you don't have one!";
            counter++;
            return tasks.addTodo(nextString, storage);
        } else if (nextString.trim().substring(0,8).contains("deadline")) {
            assert nextString.length() > 9 : "You can't just set a deadline if you don't have one!";
            counter++;
            return tasks.addDeadline(nextString, storage);
        } else if (nextString.trim().substring(0,5).contains("event")) {
            assert nextString.length() > 6 : "You can't just set an event if you don't have one!";
            counter++;
            return tasks.addEvent(nextString, storage);
        } else if (nextString.trim().substring(0, 6).contains("delete")) {
            assert counter > 0 : "You need to have a task to delete it!";
            counter--;
            return tasks.deleteTask(nextString, storage);
        } else if (nextString.trim().substring(0, 6).contains("filter")) {
            return tasks.filterType(nextString);
        } else if (nextString.trim().substring(0,7).contains("percent")) {
            return tasks.calculatePercent(nextString);
        }
        return ui.showErrorMsg();
    }
}
