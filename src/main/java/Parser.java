import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Parser {
    private Command command;
    private String description;
    private boolean isDone;
    private int taskIndex;
    private LocalDate date;

    public void parseUserInput(String userInput) throws DukeException {
        String[] userInputSplit = userInput.trim().split("\\s+", 2);
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
            try {
                this.date = LocalDate.parse(instructionSplit[1].trim());
            } catch (DateTimeParseException ex) {
                throw new DukeException("Sorry! Make sure date is in YYYY-MM-DD format (eg. 2020-02-20)");
            }
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
            try {
                this.date = LocalDate.parse(instructionSplit[1].trim());
            } catch (DateTimeParseException ex) {
                throw new DukeException("Sorry! Make sure date is in YYYY-MM-DD format (eg. 2020-02-20)");
            }
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
        } else if (this.command == Command.DELETE_TASK) {
            if (userInputSplit.length == 1) {
                throw new DukeException("Sorry! Please input a task number.");
            }
            this.taskIndex = Integer.parseInt(userInputSplit[1]) - 1;
        } else if (this.command == Command.NOT_FOUND) {
            throw new DukeException("Sorry! I don't know what you mean!");
        } else if (this.command == Command.FIND_TASKS) {
            if (userInputSplit.length == 1) {
                throw new DukeException("Sorry! Please provide something to find.");
            }
            this.description = userInputSplit[1];
        }
    }

    public void parseDiskData(String data) {
        String[] dataSplit = data.split("\\|");
        this.command = this.parseCommand(dataSplit[0]);
        this.description = dataSplit[2];
        this.isDone = dataSplit[1].equals("1") ? true : false;
        if (this.command == Command.ADD_DEADLINE) {
            this.date = LocalDate.parse(dataSplit[3].trim());
        } else if (this.command == Command.ADD_EVENT) {
            this.date = LocalDate.parse(dataSplit[3].trim());
        }
    }

    private Command parseCommand(String command) {
        if (command.equals("bye")) {
            return Command.EXIT_DUKE;
        } else if (command.equals("list")) {
            return Command.LIST_TASKS;
        } else if (command.equals("done")) {
            return Command.MARK_TASK_AS_DONE;
        } else if (command.equals("todo") || command.equals("T")) {
            return Command.ADD_TODO;
        } else if (command.equals("deadline") || command.equals("D")) {
            return Command.ADD_DEADLINE;
        } else if (command.equals("event") || command.equals("E")) {
            return Command.ADD_EVENT;
        } else if (command.equals("delete")) {
            return Command.DELETE_TASK;
        } else if (command.equals("find")) {
            return Command.FIND_TASKS;
        }
        return Command.NOT_FOUND;
    }

    public Command getCommand() {
        return this.command;
    }

    public String getDescription() {
        return this.description;
    }

    public int getTaskIndex() {
        return this.taskIndex;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public LocalDate getDate() {
        return this.date;
    }
}