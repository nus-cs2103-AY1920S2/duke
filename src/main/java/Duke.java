import Task.Deadline;
import Task.Event;
import Task.Todo;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.util.*;

public class Duke {
    public static String taskData = "./data/duke.txt";
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

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

    //to process different requests which is decided by the first token of the message user entered
    public void processRequest(String str)
            throws InvalidKeyException, IllegalArgumentException, EmptyDescriptionException {

        if (str.equals("")) {
            throw new InvalidKeyException("Try to say something to me.");
        }

        StringTokenizer st = new StringTokenizer(str);
        String first = st.nextToken(" ");

        switch (parser.getMessage(first)) {
            //decide which action to be done by the first token
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

    public void run() {
        //setting up
        Scanner sc = new Scanner(System.in);
        boolean exiting = false;

        //welcome message and showing the list to the user
        this.ui.typeSetting("    Hello, I'm Bob. \uD83D\uDC76 \uD83D\uDC76 \uD83D\uDC76\n    " +
                "What can I do for you? \uD83D\uDE03\n");
        this.ui.gettingList(taskList);

        //talking to Bob
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

    public static void main(String[] args) {
        new Duke(taskData).run();
    }
}
