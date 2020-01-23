public class Parser {
    private Command command;
    private String description;
    private String by;
    private String at;
    private int taskIndex;

    public Parser(String userInput) throws DukeException {
        String[] userInputSplit = userInput.split(" ", 2);
        this.command = this.parseCommand(userInputSplit[0]);
        if (this.command == Command.ADD_DEADLINE) {
            if (userInputSplit.length == 1) {
                throw new DukeException("Sorry! Please provide the description and due date.");
            } else if (!userInputSplit[1].contains("/by")) {
                throw new DukeException("Sorry! Make sure to use the '/by' keyword.");
            }
            String[] instructionSplit = userInputSplit[1].split("/by", 2);
            if (instructionSplit[0].equals("")) {
                throw new DukeException("Sorry! Description of a Deadline must not be empty.");
            } else if (instructionSplit[1].equals("")) {
                throw new DukeException("Sorry! Please provide a due date.");
            }
            this.description = instructionSplit[0].trim();
            this.by = instructionSplit[1].trim();
        } else if (this.command == Command.ADD_EVENT) {
            if (userInputSplit.length == 1) {
                throw new DukeException("Sorry! Please provide the description and due date.");
            } else if (!userInputSplit[1].contains("/at")) {
                throw new DukeException("Sorry! Make sure to use the '/at' keyword.");
            }
            String[] instructionSplit = userInputSplit[1].split("/at", 2);
            if (instructionSplit[0].equals("")) {
                throw new DukeException("Sorry! Description of an Event must not be empty.");
            } else if (instructionSplit[1].equals("")) {
                throw new DukeException("Sorry! Please provide a date range.");
            }
            this.description = instructionSplit[0].trim();
            this.at = instructionSplit[1].trim();
        } else if (this.command == Command.ADD_TODO) {
            if (userInputSplit.length == 1) {
                throw new DukeException("Sorry! Description of a Todo must not be empty.");
            }
            this.description = userInputSplit[1];
        } else if (this.command == Command.MARK_TASK_AS_DONE) {
            if (userInputSplit.length == 1) {
                throw new DukeException("Sorry! Please input a task number.");
            }
            this.taskIndex = Integer.parseInt(userInputSplit[1]) - 1;
        } else if (this.command == Command.NOT_FOUND) {
            throw new DukeException("Sorry! I don't know what you mean!");
        }
    } 

    private Command parseCommand(String command) {
        if (command.equals("bye")) {
            return Command.EXIT_DUKE;
        } else if (command.equals("list")) {
            return Command.LIST_TASKS;
        } else if (command.equals("done")) {
            return Command.MARK_TASK_AS_DONE;
        } else if (command.equals("todo")) {
            return Command.ADD_TODO;
        } else if (command.equals("deadline")) {
            return Command.ADD_DEADLINE;
        } else if (command.equals("event")) {
            return Command.ADD_EVENT;
        }
        return Command.NOT_FOUND;
    }

    public Command getCommand() {
        return this.command;
    }

    public String getDescription() {
        return this.description;
    }

    public String getBy() {
        return this.by;
    }

    public String getAt() {
        return this.at;
    }

    public int getTaskIndex() {
        return this.taskIndex;
    }
}