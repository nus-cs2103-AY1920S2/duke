import duke.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

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
            this.taskList = new TaskList(storage.load());
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

        assert !str.isBlank() : "Your request is missing!!!";
        //assert false : "testing";

        if (str.equals("")) {
            throw new InvalidKeyException("Try to say something to me.");
        }

        StringTokenizer st = new StringTokenizer(str);
        String first = st.nextToken(" ");
        String output = "";

        switch (parser.getMessage(first)) {
        case DONE:
            this.parser.checkDescriptionWhetherExit(str, "done".length());
            int requestNumber = parser.extractRequestNumber(st.nextToken());
            assert requestNumber <= taskList.size() : "The number entered is invalid.";
            this.taskList.markDone(requestNumber);
            output += this.ui.doneMessage(requestNumber, taskList.getTasks());
            this.storage.rewriteFile(taskList);
            break;

        case DELETE:
            this.parser.checkDescriptionWhetherExit(str, "delete".length());
            int requestNumber2 = parser.extractRequestNumber(st.nextToken());
            assert requestNumber2 <= taskList.size() : "The number entered is invalid.";
            Task taskDeleted = this.taskList.delete(requestNumber2);
            output += this.ui.deleteMessage(requestNumber2, taskDeleted, this.taskList.size());
            this.storage.rewriteFile(taskList);
            break;

        case FIND:
            parser.checkDescriptionWhetherExit(str, "find".length());
            output += ui.printMatchingTasks(taskList.find(st.nextToken()));
            break;

        case TODO:
            parser.checkDescriptionWhetherExit(str, "todo".length());
            Todo td = new Todo(st.nextToken("").substring(1));
            taskList.addTask(td);
            output += this.ui.addMessage(td, this.taskList.size());
            this.storage.rewriteFile(taskList);
            break;

        case DEADLINE:
            parser.checkDescriptionWhetherExit(str, "deadline".length());
            String[] strings = parser.stringSplitting(st);
            Deadline ddl = new Deadline(strings[0], parser.getLocalDate(strings[1]));
            taskList.addTask(ddl);
            output += this.ui.addMessage(ddl, this.taskList.size());
            this.storage.rewriteFile(taskList);
            break;

        case EVENT:
            parser.checkDescriptionWhetherExit(str, "event".length());
            String[] strings2 = parser.stringSplitting(st);
            Event ev = new Event(strings2[0], parser.getLocalDate(strings2[1]));
            taskList.addTask(ev);
            output += this.ui.addMessage(ev, this.taskList.size());
            this.storage.rewriteFile(taskList);
            break;

        case LIST:
            output += this.ui.printCurrentList(taskList);
            break;

        case HEY:
            output += this.ui.greet(taskList);
            break;

        default:
            throw new InvalidKeyException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return output;
    }
}
