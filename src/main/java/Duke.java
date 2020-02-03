import task.Deadline;
import task.Event;
import task.Todo;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.util.StringTokenizer;

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
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        try {
            Duke duke = new Duke(taskData);
            return duke.processRequest(input);
        } catch (InvalidKeyException | IllegalArgumentException | EmptyDescriptionException
                | IOException e) {
            return e.toString();
        }
    }

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

    public Duke() {

    }

    /**
     * processes different requests which is decided by the first token of the message user entered.
     * @param str the first token of the message user entered.
     * @throws InvalidKeyException if the first token entered is not a valid command.
     * @throws IllegalArgumentException if the tokens entered after the first token are not correctly formatted.
     * @throws EmptyDescriptionException if the user only entered the first token.
     */
    public String processRequest(String str)
            throws InvalidKeyException, IllegalArgumentException, EmptyDescriptionException, IOException {

        if (str.equals("")) {
            throw new InvalidKeyException("Try to say something to me.");
        }

        StringTokenizer st = new StringTokenizer(str);
        String first = st.nextToken(" ");
        String output = "";

        switch (parser.getMessage(first)) {
        case DONE:
            parser.checkDescription(str, "done".length());
            output += taskList.markDone(str);
            this.storage.rewriteFile(taskList);
            break;

        case DELETE:
            parser.checkDescription(str, "delete".length());
            output += taskList.delete(str);
            this.storage.rewriteFile(taskList);
            break;

        case FIND:
            parser.checkDescription(str, "find".length());
            output += taskList.find(str);
            break;

        case TODO:
            parser.checkDescription(str, "todo".length());
            Todo td = new Todo(st.nextToken("").substring(1));
            output += taskList.addTask(td);
            this.storage.rewriteFile(taskList);
            break;

        case DEADLINE:
            parser.checkDescription(str, "deadline".length());
            String[] strings = parser.stringSplitting(st);
            Deadline ddl = new Deadline(strings[0], strings[1]);
            output += taskList.addTask(ddl);
            this.storage.rewriteFile(taskList);
            break;

        case EVENT:
            parser.checkDescription(str, "event".length());
            String[] strings2 = parser.stringSplitting(st);
            Event ev = new Event(strings2[0], strings2[1]);
            output += taskList.addTask(ev);
            this.storage.rewriteFile(taskList);
            break;

        case LIST:
            output += this.ui.gettingList(taskList);
            break;

        default:
            throw new InvalidKeyException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return output;
    }

    /**
     * sets up the bot, shows greeting messages and then the user is able to interact with the bot.
     */
    public String run(String str) {
        //Scanner sc = new Scanner(System.in);
        boolean isexiting = false;
        String output = "";
        this.ui.greet(taskList);

        //String str = sc.nextLine();
        while (!isexiting) {
            //check if the user want to exit
            while (!str.equals("bye")) {
                if (str.equals("list")) {
                    //print out the whole list
                    return this.ui.gettingList(taskList);
                } else {
                    //update the list of tasks
                    try {
                        output += processRequest(str);
                        this.storage.rewriteFile(taskList);
                        return output;
                    } catch (InvalidKeyException | IllegalArgumentException | EmptyDescriptionException
                            | IOException e) {
                        System.err.println(e);
                    }
                }
                //str = sc.nextLine();
            }

            //exit confirmation
            ui.bye();

            if (str.equals("y")) {
                //confirm to leave and leaving message
                isexiting = true;
                return ui.typeSetting("    Bye. Hope to see you again soon! \uD83D\uDE1E\n");
            } else {
                //not leaving and continue to interact with Bob
                return ui.typeSetting("    I know you are the best! \uD83D\uDE06\n    Then, what's next?\n");
                //str = sc.nextLine();
            }
        }
        return output;
    }

//    /**
//     * runs the whole program.
//     */
    //public static void main(String[] args) {
    //    new Duke(taskData).run();
    //}
}
