public class Parser {
    private String input;
    private String done;
    private String command;
    private boolean isStorage;

    public Parser(String input, boolean isStorage) {
        this.isStorage = isStorage;
        this.input = input;
        if (!isStorage) {
            this.done = "0";
            this.command = input.split(" ")[0];
        } else {
            this.done = input.split(" ")[0];
            this.command = input.split(" ")[1];
        }
    }

    public String getCommandString() {
        return this.command;
    }

    public Command getCommand() {
        switch (this.command) {
        case "bye":
            return Command.BYE;
        case "list":
            return Command.LIST;
        case "done":
            return Command.DONE;
        case "delete":
            return Command.DELETE;
        case "todo":
            return Command.TODO;
        case "deadline":
            return Command.DEADLINE;
        case "event":
            return Command.EVENT;
        default:
            return Command.INVALID;
        }
    }

    public Task createTask() throws IndexOutOfBoundsException {
        String typeRemoved = this.isStorage
                ? this.input.strip().split(" ", 3)[2]
                : this.input.strip().split(" ", 2)[1];

        Task task = this.getCommand() == Command.TODO
                ? new ToDo(typeRemoved)
                : this.getCommand() == Command.DEADLINE
                ? new Deadline(typeRemoved)
                : new Event(typeRemoved);

        if (this.done.equals("1")) {
            task.doTask();
        }

        return task;
    }

    public int getIndex() {
        return Integer.parseInt(this.input.split(" ")[1]) - 1;
    }
}
