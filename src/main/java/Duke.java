import java.io.IOException;
import java.util.Scanner;

/**
 * The Duke program implements an application that is able to keep track of a task list. A user can populate the list
 * with tasks inputted through the standard output, mainly Todo tasks, Deadline tasks and Event tasks, or delete tasks
 * from the list.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    Parser parser;

    /**
     * Creates a Ui object to deal with user interaction , a Storage object to deal with loading or saving tasks and a
     * new TaskList object, loaded with the file storing the task list, if the file exists.
     *
     * @param filePath location where file which contains task list is found or created
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException | IOException exception) {
            ui.printLoadingError();
            tasks = new TaskList();
            storage.updateTaskList(tasks.getList());
        }
        parser = new Parser(tasks);
    }

    /**
     * Generates a response to user input.
     *
     * @param input command by user.
     * @return String that parser returns after parsing user input.
     */
    public String getResponse(String input) {
        return parser.parse(input);
    }

    public Storage getStorage() {
        assert storage != null: "Storage not initialised properly";
        return storage;
    }

    public Ui getUi() {
        assert  ui != null;
        return ui;
    }

    /**
     * Runs the Duke program.
     */
    public void run() {
        ui.printWelcome();
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        while (!command.equals("bye")) {
            System.out.println(parser.parse(command));
            command = sc.nextLine();
        }
        ui.printExitLine();
    }

    // able to run from command line
    public static void main(String[] args) {
        new Duke("./src/main/data/duke.txt").run();
    }
}
