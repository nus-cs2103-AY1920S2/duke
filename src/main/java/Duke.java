/**
 * The driver class for the Duke program.
 * Reads user input and adds, deletes, updates and saves the user's tasks accordingly.
 */
public class Duke {
    private Ui ui;
    private Storage store;
    private TaskList taskList;
    private Parser parser;

    public Duke(String fileName) {
        // Initialize Ui
        this.ui = new Ui();
        // Initialize storage
        this.store = new Storage(fileName);
        // Initialize taskList
        this.taskList =  new TaskList(this.store.loadFromFilePath());
        this.parser = new Parser();
    }

    public void run() {
        ui.sayHello();
        boolean endInput = false;
        while (!endInput) {
            try {
                String input = ui.readInput();
                Command command = parser.parse(input);
                command.execute(taskList, ui, store);
                endInput = command.isExit();
            } catch (DukeException e) {
                // Print any error that is thrown
                ui.printLine();
                System.out.println(e.getMessage());
                ui.printLine();
            } catch (NullPointerException ne) {
                ui.flagWrongCommand();
            }
        }
        ui.sayBye();
    }
    public static void main(String[] args) {
        new Duke("list.txt").run();
    }

}

