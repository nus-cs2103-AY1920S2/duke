import java.util.Scanner;

/**
 *  makes sense of the user command
 */
public class Parser {

    Scanner scan;
    Ui ui = new Ui();

    public Parser() {
        this.scan = new Scanner(System.in);
    }

    /**
     * scanner scan task's instruction and run accordingly
     * @throws DukeException when users input task which bot does not understand
     */
    public void scan() throws DukeException {
        ui.greeting();
        String command = scan.nextLine();
        while (!command.equals("bye")) {
            if (command.contains("find")) {
                TaskList task = new TaskList(command);
                task.find();
                command = scan.nextLine();

            } else if (command.contains("delete")) {
                TaskList task = new TaskList(command);
                task.delete();
                command = scan.nextLine();

            } else if (!command.contains("todo")
                    && !command.contains("deadline")
                    && !command.contains("event")
                    && !command.contains("done")
                    && !command.contains("list")
                    && !command.contains("delete")) {
                throw new DukeException(" ))-: OOPS!!! I'm sorry, "
                        + "but I don't know what that means :-(");

            } else if (command.contains("todo")) {
                TaskList task = new TaskList(command);
                task.toDo();
                command = scan.nextLine();

            } else if (command.contains("deadline")) {
                TaskList task = new TaskList(command);
                task.deadLine();
                command = scan.nextLine();

            } else if (command.contains("event")) {
                TaskList task = new TaskList(command);
                task.event();
                command = scan.nextLine();

            } else if (command.contains("done")) {
                TaskList task = new TaskList(command);
                task.done();
                command = scan.nextLine();

            } else if (command.equals("list")) {
                TaskList task = new TaskList(command);
                task.printList();
                command = scan.nextLine();

            }
        }
        ui.bye();
    }
}
