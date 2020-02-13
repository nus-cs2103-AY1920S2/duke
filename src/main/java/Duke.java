import java.util.Scanner;
import java.time.LocalDate;

/**
 * Represent the main driving class Duke.
 */
public class Duke {

    protected Ui ui;
    protected Storage storage;
    protected Tasklist tasklist;

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
            String keyword = parsed[0];
            if (keyword.equals("bye")) {
                this.storage.writeFile(this.tasklist.mylist);
                message = message + ui.printMessage("Bye. Hope to see you again soon!");
            } else if (keyword.equals("list")) {
                message = message + ui.printMessage("Here are the task in your list");
                message = message + tasklist.printList();
            } else if (keyword.equals("done")) {
                int taskNumber = Integer.valueOf(parsed[1]);
                this.tasklist.markDone(taskNumber);
                message = message + ui.printMessage("Nice! I've marked this task as done:");
                message = message + ui.printMessage("" + taskNumber + ". " + this.tasklist.getTask(taskNumber));
            } else if (keyword.equals("delete")) {
                int taskNumber = Integer.valueOf(parsed[1]);
                message = message + ui.printMessage("Noted. I've removed this task");
                message = message + ui.printMessage("" + this.tasklist.getTask(taskNumber));
                this.tasklist.removeTask(taskNumber);
                message = message + ui.printMessage("Now you have " + this.tasklist.getSize() + " in the list.");
            } else if (keyword.equals("find")) {
                this.tasklist.findKeyword(parsed[1]);
            } else if (keyword.equals("todo") || keyword.equals("deadline") || keyword.equals("event")) {
                if (parsed.length <= 1) {
                    throw new DukeException("I think u need more arguments");
                } else {
                    String word2 = parsed[1];
                    String[] parsed2 = TextParser.mySecondParser(word2);
                    if (keyword.equals("todo")) {
                        this.tasklist.addTask(new Todo(parsed2[0]));
                    } else if (keyword.equals("deadline")) {
                        this.tasklist.addTask(new Deadline(parsed2[0], LocalDate.parse(parsed2[1])));
                    } else if (keyword.equals("event")) {
                        this.tasklist.addTask(new Event(parsed2[0], LocalDate.parse(parsed2[1])));
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
        } finally {
            message = message + ui.printLine();
        }
        return message;
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    protected String getResponse(String input) {
        return this.run(input);
    }

}
