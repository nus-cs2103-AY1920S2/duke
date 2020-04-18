import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exceptions.DukeException;


/**
 * CS2103T AY 19/20 S2
 * Individual Project
 *
 * @author Yap Dian Hao
 * @since 2020-01-30
 * <p>
 * Duke is a personal chatbot assistant to help you organize your tasks. Especially helpful
 * for people who love to procrastinate. Or procrastinate to procrastinate.
 * </p>
 */
public class Duke {

    private Storage storage;
    private TaskList taskList;
    private static Ui ui;
    private String filepath;

    public Duke() {
        this.filepath = "duke.txt";
        this.ui = new Ui();
        this.storage = new Storage(this.filepath);
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            System.out.println(e);
        }
    }

    public String getResponse(String input) {
        String toReturn = ui.start(this.taskList, input);
        storage.write(this.taskList.getAllTasks());
        return toReturn;
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        ui.showLine();
        String input = ui.getInput();
        while (!input.equals("bye")) {
            System.out.println(duke.getResponse(input));
            input = ui.getInput();
        }
        System.out.println("Bye! See you again!");
    }
}