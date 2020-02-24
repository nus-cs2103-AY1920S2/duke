/**
 * Class to make sense of the user command.
 */

public class Parser {
    private String cmd;
    private Task task;

    /**
     * Creates a parser object to make sense of the user command.
     * @param command Command given by user.
     * @throws DukeException If command is not bye/list/done/todo/deadline/event/delete/find/update.
     */
    public Parser(String command) throws DukeException {
        boolean isValidCommand = command.equals("bye")|command.equals("list")|command.equals("done")
                |command.equals("todo")|command.equals("deadline")|command.equals("event")
                |command.equals("delete")|command.equals("find")|command.equals("update");

        if (isValidCommand) {
            this.cmd = command.toLowerCase();
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I dont know what that means :-(");
        }
    }

    /**
     * Creates a parser object to organise the details of the task provided by user.
     * @param content Description of task given by user.
     * @param command Command given by user.
     * @throws DukeException If description given by user is empty.
     */
    public Parser(String content, Parser command) throws DukeException {
        if (content.equals(new String())) {
            throw new DukeException("OOPS!!! The description of a "
                    + command.getCommand() + " cannot be empty.");
        } else {
            switch (command.getCommand()) {
            case "todo":
                this.task = new ToDo(content.substring(1));
                break;
            case "event":
                String [] eventArray = content.split(" /at ");
                this.task = new Event(eventArray[0].substring(1), eventArray[1]);
                break;
            case "deadline":
                String [] deadlineArray = content.split(" /by ");
                this.task = new Deadline(deadlineArray[0].substring(1), deadlineArray[1]);
                break;
            default:
                assert false : command.getCommand();
            }
        }
    }

    public boolean isBye() {
        return this.cmd.equals("bye");
    }

    public boolean isList() {
        return this.cmd.equals("list");
    }

    public boolean isDone() {
        return this.cmd.equals("done");
    }

    public boolean isDelete() {
        return this.cmd.equals("delete");
    }

    public boolean isFind() {
        return this.cmd.equals("find");
    }

    public boolean isUpdate() {
        return this.cmd.equals("update");
    }

    public String getCommand() {
        return this.cmd;
    }

    public Task getTask() {
        return this.task;
    }
}
