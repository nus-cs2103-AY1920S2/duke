public class Parser {
    private String input;
    private String done;
    private String command;
    private boolean isStorage;

    /**
     * Creates a Parser object.
     *
     * @param input     User input of a command
     * @param isStorage Is constructor called from Storage class
     */
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

    /**
     * Getter for command.
     *
     * @return String of the command
     */
    public String getCommandString() {
        return this.command;
    }

    /**
     * Gets the command in enum form.
     *
     * @return The command in enum form
     */
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
        case "find":
            return Command.FIND;
        default:
            return Command.INVALID;
        }
    }

    /**
     * Creates a task according to input and whether we are parsing commands from storage.
     *
     * @return The task created
     * @throws IndexOutOfBoundsException Thrown when input is invalid
     */
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

    /**
     * Get relevant index of delete and done commands.
     *
     * @return Index specified in input
     */
    public int getIndex() {
        return Integer.parseInt(this.input.split(" ")[1]) - 1;
    }

    /**
     * Get search term from find command.
     *
     * @return Search term
     */
    public String getSearchTerm() {
        return this.input.split(" ")[1];
    }
}
