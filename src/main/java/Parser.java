import java.util.Scanner;

/**
 * Represents a Parser which handles commands inputted by the user.
 */

public class Parser {
    private Ui ui;
    private TaskList tasklist;
    private Scanner sc;
    private boolean bye = false;

    /**
     * Function to handle the input by the user. If the input is empty the user will be notified.
     *
     * @param input the input or command entered by the user.
     * @return reply from the bot to the user.
     */
    public String parse(String input) {
        String reply;
        String str = input;

        if (str.isEmpty()) {
            return ui.emptyCmd();
        }
        reply = parseCommands(str);
        if (bye) {
            return reply;
        }
        return reply;
    }


    /**
     * Constructor for Parser that handles all the inputs of the user.
     *
     * @param tasklist the tasklist containing all the tasks.
     */
    public Parser(TaskList tasklist) {
        assert tasklist != null : "Tasklist is null";

        this.tasklist = tasklist;
        this.sc = new Scanner(System.in);
        this.ui = new Ui();
    }
    public String parseCommands(String str) {
        assert str != null : "String for parsing commands is null";

        String reply;

        if (str.equals("bye")) {
            reply = tasklist.bye();
            bye = true;

        } else if (str.equals("list")) {
            reply = tasklist.printList();

        } else if (str.contains("done")) {
            reply = tasklist.done(str);

        } else if (str.contains("todo")) {
            reply = tasklist.todo(str);

        } else if (str.contains("deadline")) {
            reply = tasklist.deadline(str);

        } else if (str.contains("event")) {
            reply = tasklist.event(str);

        } else if (str.contains("delete")) {
            reply = tasklist.delete(str);

        } else if (str.contains("find")) {
            reply = tasklist.find(str);

        } else if (str.contains("details")) {
            reply = tasklist.findDetails(str);

        } else {
            reply = ui.unknownCommand();
        }
        return reply;
    }
}

