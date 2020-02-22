import java.io.IOException;
import java.util.List;

/**
 *  makes sense of the user command
 */
public class Parser {

    private Ui ui = new Ui();
    private Storage storage = new Storage("duke.txt");

    public Parser() throws IOException {
    }

    /**
     * scanner scan task's instruction and run accordingly
     * @throws DukeException when users input task which bot does not understand
     */
    public String scan(String command) throws DukeException {
        String toPrint = "";

            if(command.startsWith("bye", 0)) {
                TaskList allTask = new TaskList();
                List<Task> tasks = allTask.getDoneTasks();
                storage.load(tasks);
                toPrint = ui.bye();

            } else if (command.startsWith("view", 0)) {
                TaskList schedule = new TaskList(command);
                toPrint = schedule.view();

            } else if (command.startsWith("date", 0)) {
                TaskList schedule = new TaskList(command);
                toPrint = schedule.date();

            } else if (command.startsWith("find", 0)) {
                TaskList task = new TaskList(command);
                toPrint = task.find();

            } else if (command.startsWith("delete", 0)) {
                TaskList task = new TaskList(command);
                toPrint = task.delete();

            } else if (!command.startsWith("view", 0)
                    && !command.startsWith("todo", 0)
                    && !command.startsWith("deadline", 0)
                    && !command.startsWith("event", 0)
                    && !command.startsWith("done", 0)
                    && !command.startsWith("list", 0)
                    && !command.startsWith("delete", 0)) {
                throw new DukeException(" ))-: OOPS!!! I'm sorry, "
                        + "but I don't know what that means :-(");

            } else if (command.startsWith("todo", 0)) {
                TaskList task = new TaskList(command);
                toPrint = task.toDo();

            } else if (command.startsWith("deadline", 0)) {
                TaskList task = new TaskList(command);
                toPrint = task.deadLine();

            } else if (command.startsWith("event", 0)) {
                TaskList task = new TaskList(command);
                toPrint = task.event();

            } else if (command.startsWith("done", 0)) {
                TaskList task = new TaskList(command);
                toPrint = task.done();

            } else if (command.startsWith("list", 0)) {
                TaskList task = new TaskList(command);
                toPrint = task.printList();


            }
        return toPrint;
    }
}
