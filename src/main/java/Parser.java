public class Parser {
    private int type;
    private String cmd;
    private Task task;

    public Parser(String command) throws DukeException {
        if (command.equals("bye")|command.equals("list")|command.equals("done")|command.equals("todo")
                |command.equals("deadline")|command.equals("event")|command.equals("delete")) {
            this.cmd = command.toLowerCase();
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I dont know what that means :-(");
        }
    }

    public Parser(String content, Parser command) throws DukeException {
        if (content.equals(new String())) {
            throw new DukeException("☹ OOPS!!! The description of a "
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

    public String getCommand() {
        return this.cmd;
    }

    public Task getTask() {
        return this.task;
    }
}
