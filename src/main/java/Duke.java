import java.time.LocalDate;

/**
 * Represent the main driving class Duke.
 */
public class Duke {

    protected Ui ui;
    protected Storage storage;
    protected Tasklist tasklist;

    public static final int COMMAND = 0;
    public static final int NUMBER = 1;
    public static final int FIRST_WORD = 0;
    public static final int SECOND_WORD = 1;
    public static final int TIME = 1;
    public static final String BYE_COMMAND = "bye";
    public static final String LIST_COMMAND = "list";
    public static final String DONE_COMMAND = "done";
    public static final String DEL_COMMAND = "delete";
    public static final String FIND_COMMAND = "find";
    public static final String TODO_COMMAND = "todo";
    public static final String EVENT_COMMAND = "event";
    public static final String DEADLINE_COMMAND = "deadline";
    public static final String STAT_COMMAND = "statistic";

    /**
     * Constructor of Duke.
     * Initialise Ui, Storage and Tasklist
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage("./list.txt");
        this.tasklist = new Tasklist(storage.readFile());
    }

    /**
     * The main logic of the chat bot.
     * @param command typed by the user
     * @return message generated by the bot
     */
    public String run(String command) {
        String message = "";
        try {
            String[] parsed = TextParser.myFirstParser(command);
            String keyword = parsed[COMMAND];

            if (keyword.equals(BYE_COMMAND)
                    || keyword.equals(STAT_COMMAND)
                    || keyword.equals(LIST_COMMAND)) { // commands with no arguments
                if (keyword.equals(BYE_COMMAND)) {
                    message = byeCommand();
                    //System.exit(0);
                } else if (keyword.equals(STAT_COMMAND)) {
                    message = statCommand();
                } else if (keyword.equals(LIST_COMMAND)) {
                    message = listCommand();
                }
            } else if (keyword.equals(DONE_COMMAND)
                    || keyword.equals(DEL_COMMAND)
                    || keyword.equals(FIND_COMMAND)
                    || keyword.equals(TODO_COMMAND)) { // commands with one arguments
                if (parsed.length <= 1) {
                    throw new DukeException("I think u need one more argument");
                }
                if (keyword.equals(DONE_COMMAND)) {
                    int taskNumber = Integer.valueOf(parsed[NUMBER]);
                    assert Integer.valueOf(parsed[NUMBER]) != 0 : " should not be 0";
                    message = doneCommand(taskNumber);
                } else if (keyword.equals(DEL_COMMAND)) {
                    int taskNumber = Integer.valueOf(parsed[NUMBER]);
                    message = delCommand(taskNumber);
                    assert Integer.valueOf(parsed[NUMBER]) != 0 : " should not be 0";
                } else if (keyword.equals(FIND_COMMAND)) {
                    message = findCommand(parsed[SECOND_WORD]);
                } else if (keyword.equals(TODO_COMMAND)) {
                    message = todoCommand(parsed[SECOND_WORD]);
                }
            } else if (keyword.equals(DEADLINE_COMMAND)
                    || keyword.equals(EVENT_COMMAND)) { // commands with two or more argument
                if (parsed.length <= 1) {
                    throw new DukeException("There is no argument");
                }
                String word2 = parsed[SECOND_WORD];
                String[] parsed2 = TextParser.mySecondParser(word2);
                if (parsed2.length <= 1) {
                    throw new DukeException("I think u need one more argument");
                }
                if (keyword.equals(DEADLINE_COMMAND)) {
                    message = deadlineCommand(parsed2[FIRST_WORD], LocalDate.parse(parsed2[TIME]));
                } else if (keyword.equals(EVENT_COMMAND)) {
                    message = eventCommand(parsed2[FIRST_WORD], LocalDate.parse(parsed2[TIME]));
                }
            } else {
                throw new DukeException("I DK how to process this -> " + command); // command not recognised
            }
        } catch (DukeException e) {
            message = message + ui.printMessage(e.getMessage());
        }
        assert message != "" : " message should contain some text";
        return message;
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return this.run(input);
    }

    /**
     * Runs the bye command.
     * Saves the list of task into a file.
     * @return the message generated to inform the user
     */
    private String byeCommand() {
        String message = "";
        storage.writeFile(tasklist.mylist);
        message = ui.printMessage("Bye. I have saved the list to a file! You can exit the program now :)");
        return message;
    }

    /**
     * Runs the statistic command.
     * Create a statistic of task
     * @return message generated to inform the user
     */
    private String statCommand() {
        String message = "";
        message = tasklist.printStatistic();
        return message;
    }

    /**
     * Runs the list command.
     * List the task in the list
     * @return message generated to inform the user
     */
    private String listCommand() {
        String message = "";
        message = message + ui.printMessage("Here are the task in your list");
        message = message + tasklist.printList();
        return message;
    }

    /**
     * Runs the done command.
     * Mark the task as done in the list
     * @param taskNumber in the list
     * @return message generated to inform the user
     * @throws DukeException invalid number
     */
    private String doneCommand(int taskNumber) throws DukeException {
        String message = "";
        if (taskNumber <= 0 || taskNumber > tasklist.getSize()) {
            throw new DukeException("The task number is invalid !!!");
        }
        if (tasklist.getTask(taskNumber).isDone) {
            message = ui.printMessage("It is already been marked as done !!");
        } else {
            tasklist.markDone(taskNumber);
            message = message + ui.printMessage("Nice! I've marked this task as done:");
            message = message + ui.printMessage("" + taskNumber + ". " + tasklist.getTask(taskNumber));
        }
        return message;
    }

    /**
     * Runs the delete command.
     * Remove the task in the list.
     * @param taskNumber in the list
     * @return message generated to inform the user
     * @throws DukeException invalid number
     */
    private String delCommand(int taskNumber) throws DukeException {
        String message = "";
        if (taskNumber <= 0 || taskNumber > tasklist.getSize()) {
            throw new DukeException("The task number is invalid !!!");
        }
        message = message + ui.printMessage("Noted. I've removed this task:");
        message = message + ui.printMessage("" + this.tasklist.getTask(taskNumber));
        tasklist.removeTask(taskNumber);
        message = message + ui.printMessage("Now you have " + this.tasklist.getSize() + " in the list.");
        return message;
    }

    /**
     * Runs the find command.
     * List the tasks that contains the keyword.
     * @param keyword to be searched
     * @return message generated to inform user
     */
    private String findCommand(String keyword) {
        String message = "";
        message = tasklist.findKeyword(keyword);
        return message;
    }

    /**
     * Runs the todoCommand.
     * Create a new todotask and add to the list.
     * @param taskDescription of the task
     * @return message generated to inform user
     */
    private String todoCommand(String taskDescription) {
        String message = "";
        tasklist.addTask(new Todo(taskDescription));
        message = message + ui.printMessage("Got it. I 've added this task:");
        message = message + ui.printMessage("" + this.tasklist.getTask(this.tasklist.getSize()));
        message = message + ui.printMessage("Now you have " + this.tasklist.getSize() + " in the list.");
        return message;
    }

    /**
     * Runs the deadline command.
     * Create a new deadline task and add to the list.
     * @param taskDescription of the task
     * @param date of the task
     * @return message generated to inform user
     */
    private String deadlineCommand(String taskDescription, LocalDate date) {
        String message = "";
        tasklist.addTask(new Deadline(taskDescription, date));
        message = message + ui.printMessage("Got it. I 've added this task:");
        message = message + ui.printMessage("" + this.tasklist.getTask(this.tasklist.getSize()));
        message = message + ui.printMessage("Now you have " + this.tasklist.getSize() + " in the list.");
        return message;
    }

    /**
     * Runs the event command.
     * Create a new event task and add to the list.
     * @param taskDescription of the task
     * @param date of the task
     * @return message generated to inform user
     */
    private String eventCommand(String taskDescription, LocalDate date) {
        String message = "";
        tasklist.addTask(new Event(taskDescription, date));
        message = message + ui.printMessage("Got it. I 've added this task:");
        message = message + ui.printMessage("" + this.tasklist.getTask(this.tasklist.getSize()));
        message = message + ui.printMessage("Now you have " + this.tasklist.getSize() + " in the list.");
        return message;
    }
}
