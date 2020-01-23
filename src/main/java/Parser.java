public class Parser {
    private Command command;
    private String description;
    private String by;
    private String at;
    private int taskIndex;

    public Parser(String userInput) {
        String[] userInputSplit = userInput.split(" ", 2);
        this.command = this.parseCommand(userInputSplit[0]);
        if (this.command == Command.ADD_DEADLINE) {
            String[] instructionSplit = userInputSplit[1].split("/by", 2);
            this.description = instructionSplit[0].trim();
            this.by = instructionSplit[1].trim();
        } else if (this.command == Command.ADD_EVENT) {
            String[] instructionSplit = userInputSplit[1].split("/at", 2);
            this.description = instructionSplit[0].trim();
            this.at = instructionSplit[1].trim();
        } else if (this.command == Command.ADD_TODO) {
            this.description = userInputSplit[1];
        } else if (this.command == Command.MARK_TASK_AS_DONE) {
            this.taskIndex = Integer.parseInt(userInputSplit[1]) - 1;
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