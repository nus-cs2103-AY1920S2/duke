/**
 * Duke is a chat bot program that builds a to do list. Current functions include:
 * list, delete, done, todo, deadline, event, bye.
 *
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for main class for the program.
     * @param filePath relative path of the file that the data of to do list is saved in.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Main driver for Duke program.
     */
    public void run() {
        ui.welcomeMessage();
        tasks.showCurrentTasks();
        ui.showLineBreak();
        boolean isFinished = false;
        while (!(isFinished)) {
            try {
                String inputFromUser = ui.handleInput();
                Command c = Parser.parse(inputFromUser);
                if (c == null) {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-( ☹ OOPS!!!");
                }
                tasks.runCommand(c);
                if (c.getCommand().equals("bye")) {
                    isFinished = true;
                }
            } catch (DukeException e) {
                System.out.println("Invalid command received: " + e.getMessage());
            } finally {
                this.storage.save(tasks);
            }
        }
        ui.terminateMessage();
    }

    public static void main(String[] args) {
        Duke duke = new Duke("./data/duke.txt");
        duke.run();
    }
}
