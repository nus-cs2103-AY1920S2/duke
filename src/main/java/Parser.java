import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * This class serves as a wrapper to parse and tokenize either user inputs or
 * the data stored on the user's hard disks. The tokens are then stored as
 * members of this class.
 */
public class Parser {
    private Command command;
    private String description;
    private boolean isDone;
    private int taskIndex;
    private LocalDate date;

    /**
     * Parses and tokenizes a String from the user.
     * 
     * @param userInput the raw String given by the user
     * @throws DukeException if the command does not exist, or if the syntax of the
     *                       command is incorrect and/or missing essential details
     */
    public void parseUserInput(String userInput) throws DukeException {
        String[] userInputSplit = userInput.trim().split("\\s+", 2);
        this.command = this.parseCommand(userInputSplit[0]);
        if (this.command == Command.ADD_DEADLINE) {
            this.addDeadlineHandler(userInputSplit);
        } else if (this.command == Command.ADD_EVENT) {
            this.addEventHandler(userInputSplit);
        } else if (this.command == Command.ADD_TODO) {
            this.addTodoHandler(userInputSplit);
        } else if (this.command == Command.MARK_TASK_AS_DONE) {
            this.markTaskAsDoneHandler(userInputSplit);
        } else if (this.command == Command.DELETE_TASK) {
            this.deleteTaskHandler(userInputSplit);
        } else if (this.command == Command.NOT_FOUND) {
            this.commandNotFoundHandler(userInputSplit);
        } else if (this.command == Command.FIND_TASKS) {
            this.findTasksHandler(userInputSplit);
        }
    }

    /**
     * Tokenizes the {@code ADD_DEADLINE} command.
     * 
     * @param userInputSplit the array representing the user input string separated
     *                       by white spaces
     * @throws DukeException if any of the task description, due date, or '/by'
     *                       keyword are missing
     */
    private void addDeadlineHandler(String[] userInputSplit) throws DukeException {
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
    }

    /**
     * Tokenizes the {@code ADD_EVENT} command.
     * 
     * @param userInputSplit the array representing the user input string separated
     *                       by white spaces
     * @throws DukeException if any of the task description, due date, or '/at'
     *                       keyword are missing
     */
    private void addEventHandler(String[] userInputSplit) throws DukeException {
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
    }

    /**
     * Tokenizes the {@code ADD_TODO} command.
     * 
     * @param userInputSplit the array representing the user input string separated
     *                       by white spaces
     * @throws DukeException if the task description is missing
     */
    private void addTodoHandler(String[] userInputSplit) throws DukeException {
        if (userInputSplit.length == 1) {
            throw new DukeException("Sorry! Description of a Todo must not be empty.");
        }
        this.description = userInputSplit[1];
    }

    /**
     * Tokenizes the {@code MARK_TASK_AS_DONE} command.
     * 
     * @param userInputSplit the array representing the user input string separated
     *                       by white spaces
     * @throws DukeException if the input task number is missing, or not a number
     */
    private void markTaskAsDoneHandler(String[] userInputSplit) throws DukeException {
        if (userInputSplit.length == 1) {
            throw new DukeException("Sorry! Please input a task number.");
        }
        try {
            this.taskIndex = Integer.parseInt(userInputSplit[1]) - 1;
        } catch (NumberFormatException ex) {
            throw new DukeException("Sorry! Please enter a valid number.");
        }
    }

    /**
     * Tokenizes the {@code DELETE_TASK} command.
     * 
     * @param userInputSplit the array representing the user input string separated
     *                       by white spaces
     * @throws DukeException if the input task number is missing, or not a number
     */
    private void deleteTaskHandler(String[] userInputSplit) throws DukeException {
        if (userInputSplit.length == 1) {
            throw new DukeException("Sorry! Please input a task number.");
        }
        try {
            this.taskIndex = Integer.parseInt(userInputSplit[1]) - 1;
        } catch (NumberFormatException ex) {
            throw new DukeException("Sorry! Please enter a valid number.");
        }
    }

    private void commandNotFoundHandler(String[] userInputSplit) throws DukeException {
        throw new DukeException("Sorry! I don't know what you mean!");
    }

    /**
     * Tokenizes the {@code FIND_TASKS} command.
     * 
     * @param userInputSplit the array representing the user input string separated
     *                       by white spaces
     * @throws DukeException if the input string to find is missing
     */
    private void findTasksHandler(String[] userInputSplit) throws DukeException {
        if (userInputSplit.length == 1) {
            throw new DukeException("Sorry! Please provide something to find.");
        }
        this.description = userInputSplit[1];
    }

    /**
     * Parses and tokenizes a String from the local hard disk.
     * 
     * <p>Unlike the {@code parseUserInput} method, this method does not throw any
     * {@code DukeException} as the data stored on disk is guaranteed to be correct.
     * 
     * @param data the raw String data from the local hard disk
     */
    public void parseDiskData(String data) {
        String[] dataSplit = data.split("\\|");
        assert dataSplit.length >= 3;
        this.command = this.parseCommand(dataSplit[0]);
        this.description = dataSplit[2];
        this.isDone = dataSplit[1].equals("1") ? true : false;
        if (this.command == Command.ADD_DEADLINE) {
            this.date = LocalDate.parse(dataSplit[3].trim());
        } else if (this.command == Command.ADD_EVENT) {
            this.date = LocalDate.parse(dataSplit[3].trim());
        }
    }

    /**
     * Parses and returns the correct {@code Command} enum value from the input
     * string {@code command}.
     * 
     * @param command string to match
     * @return the correct {@code Command} enum value
     */
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