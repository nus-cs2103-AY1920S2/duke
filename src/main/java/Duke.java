import Task.Deadline;
import Task.Event;
import Task.Todo;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.util.*;

/**
 * Represents a Duke bot. This is also the main class of duke project.
 */
public class Duke {
    public static String taskData = "./data/duke.txt";
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    /**
     * constructs a Duke bot instance.
     * @param filePath the file path where the bot stores its data collected from the user in.
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();
        this.parser = new Parser();
        try {
            this.taskList = new TaskList(storage.start());
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    /**
     * processes different requests which is decided by the first token of the message user entered.
     * @param str the first token of the message user entered.
     * @throws InvalidKeyException if the first token entered is not a valid command.
     * @throws IllegalArgumentException if the tokens entered after the first token are not correctly formatted.
     * @throws EmptyDescriptionException if the user only entered the first token.
     */
    public void processRequest(String str)
            throws InvalidKeyException, IllegalArgumentException, EmptyDescriptionException {

        if (str.equals("")) {
            throw new InvalidKeyException("Try to say something to me.");
        }

        StringTokenizer st = new StringTokenizer(str);
        String first = st.nextToken(" ");

        switch (parser.getMessage(first)) {
            case DONE:
                parser.checkDescription(str, "done".length());
                taskList.markDone(str);
                break;

            case DELETE:
                parser.checkDescription(str, "delete".length());
                taskList.delete(str);
                break;

            case TODO:
                parser.checkDescription(str, "todo".length());
                Todo td = new Todo(st.nextToken("").substring(1));
                taskList.addTask(td);
                break;

            case DEADLINE:
                parser.checkDescription(str, "deadline".length());
                String[] strings = parser.stringSplitting(st);
                Deadline ddl = new Deadline(strings[0], strings[1]);
                taskList.addTask(ddl);
                break;

            case EVENT:
                parser.checkDescription(str, "event".length());
                String[] strings2 = parser.stringSplitting(st);
                Event ev = new Event(strings2[0], strings2[1]);
                taskList.addTask(ev);
                break;

            default:
                throw new InvalidKeyException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * sets up the bot, shows greeting messages and then the user is able to interact with the bot.
     */
    public void run() {
        Scanner sc = new Scanner(System.in);
        boolean exiting = false;

        this.ui.greet(taskList);

        String str = sc.nextLine();
        while (!exiting) {
            //check if the user want to exit
            while (!str.equals("bye")) {
                if (str.equals("list")) {
                    //print out the whole list
                    this.ui.gettingList(taskList);
                } else {
                    //update the list of tasks
                    try {
                        processRequest(str);
                        this.storage.rewriteFile(taskList);
                    } catch (InvalidKeyException | IllegalArgumentException |  EmptyDescriptionException
                            | IOException e) {
                        System.err.println(e);
                    }
                }
                str = sc.nextLine();
            }

            //exit confirmation
            ui.bye();

            if (sc.nextLine().equals("y")) {
                //confirm to leave and leaving message
                exiting = true;
                ui.typeSetting("    Bye. Hope to see you again soon! \uD83D\uDE1E\n");
            } else {
                //not leaving and continue to interact with Bob
                ui.typeSetting("    I know you are the best! \uD83D\uDE06\n    Then, what's next?\n");
                str = sc.nextLine();
            }
        }
    }

    /**
     * runs the whole program.
     */
    public static void main(String[] args) {
        new Duke(taskData).run();
    }
}
