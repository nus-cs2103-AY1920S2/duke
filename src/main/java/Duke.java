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
     * The main logic of the chatbot.
     */
    public String run(String command) {
        String message = "";
        try {
            String[] parsed = TextParser.myFirstParser(command);
            String keyword = parsed[COMMAND];
            assert keyword != "" : "Should contain a keyword else command is a empty string";
            if (keyword.equals(BYE_COMMAND)) {
                message = byeCommand();
            } else if (keyword.equals(STAT_COMMAND)) {
                message = statCommand();
            } else if (keyword.equals(LIST_COMMAND)) {
                message = listCommand();
            } else if (keyword.equals(DONE_COMMAND)) {
                int taskNumber = Integer.valueOf(parsed[NUMBER]);
                this.tasklist.markDone(taskNumber);
                message = message + ui.printMessage("Nice! I've marked this task as done:");
                message = message + ui.printMessage("" + taskNumber + ". " + this.tasklist.getTask(taskNumber));
            } else if (keyword.equals(DEL_COMMAND)) {
                int taskNumber = Integer.valueOf(parsed[NUMBER]);
                message = message + ui.printMessage("Noted. I've removed this task");
                message = message + ui.printMessage("" + this.tasklist.getTask(taskNumber));
                this.tasklist.removeTask(taskNumber);
                message = message + ui.printMessage("Now you have " + this.tasklist.getSize() + " in the list.");
            } else if (keyword.equals(FIND_COMMAND)) {
                this.tasklist.findKeyword(parsed[SECOND_WORD]);
            } else if (keyword.equals(TODO_COMMAND) || keyword.equals(DEADLINE_COMMAND) || keyword.equals(EVENT_COMMAND)) {
                if (parsed.length <= 1) {
                    throw new DukeException("I think u need more arguments");
                } else {
                    String word2 = parsed[SECOND_WORD];
                    String[] parsed2 = TextParser.mySecondParser(word2);
                    if (keyword.equals(TODO_COMMAND)) {
                        this.tasklist.addTask(new Todo(parsed2[FIRST_WORD]));
                    } else if (keyword.equals(DEADLINE_COMMAND)) {
                        this.tasklist.addTask(new Deadline(parsed2[FIRST_WORD], LocalDate.parse(parsed2[TIME])));
                    } else if (keyword.equals(EVENT_COMMAND)) {
                        this.tasklist.addTask(new Event(parsed2[FIRST_WORD], LocalDate.parse(parsed2[TIME])));
                    }
                    message = message + ui.printMessage("Got it. I 've added this task:");
                    message = message + ui.printMessage("" + this.tasklist.getTask(this.tasklist.getSize()));
                    message = message + ui.printMessage("Now you have " + this.tasklist.getSize() + " in the list.");
                }
            } else {
                throw new DukeException("I DK how to process this -> " + command);
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
     * @return the message generated to inform the user
     */
    private String byeCommand() {
        String message = "";
        storage.writeFile(tasklist.mylist);
        message = ui.printMessage("Bye. I have saved the list to a file! You can exit the program now");
        return message;
    }

    /**
     * Runs the statistic command.
     * @return message generated to inform the user
     */
    private String statCommand() {
        String message = "";
        message = tasklist.printStatistic();
        return message;
    }

    private String listCommand() {
        String message = "";
        message = message + ui.printMessage("Here are the task in your list");
        message = message + tasklist.printList();
        return message;
    }

}
