/**
 *  makes sense of the user command
 */
public class Parser {

    Ui ui = new Ui();

    public Parser() {
    }

    /**
     * scanner scan task's instruction and run accordingly
     * @throws DukeException when users input task which bot does not understand
     */
    public String scan(String command) throws DukeException {
        String toPrint = "";

            if(command.contains(("bye"))) {
                toPrint = ui.bye();
            }
            else if (command.contains("find")) {
                TaskList task = new TaskList(command);
                toPrint =task.find();

            } else if (command.contains("delete")) {
                TaskList task = new TaskList(command);
                toPrint = task.delete();

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
                toPrint = task.toDo();

            } else if (command.contains("deadline")) {
                TaskList task = new TaskList(command);
                toPrint = task.deadLine();

            } else if (command.contains("event")) {
                TaskList task = new TaskList(command);
                toPrint = task.event();

            } else if (command.contains("done")) {
                TaskList task = new TaskList(command);
                toPrint = task.done();

            } else if (command.equals("list")) {
                TaskList task = new TaskList(command);
                toPrint = task.printList();


            }
        return toPrint;
    }
}
