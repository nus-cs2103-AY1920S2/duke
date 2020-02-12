import java.util.Scanner;

/**
 * Represents a Parser which handles commands inputted by the user
 */

public class Parser {
    private Ui ui;
    private TaskList tasklist;
    private Scanner sc;
    private boolean bye = false;

    public String parse(String input) throws Exception {
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


    public Parser(TaskList tasklist) {
        this.tasklist = tasklist;
        this.sc = new Scanner(System.in);
        this.ui = new Ui();
    }
    public String parseCommands(String str) throws Exception {
        //assert
        String reply = ui.lineBarrier();

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

        } else {
            reply = ui.unknownCommand();
        }
        return reply + ui.lineBarrier() + "\n\n";
    }
}

