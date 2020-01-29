/**
 * Main driver for the code.
 */
public class Duke {

    private Ui ui;
    private Storage taskStorage;
    private TaskList tasks;
    private Parser parser;

    /**
     * To instantiate the chat bot/ calender.
     */
    public Duke() {

        this.taskStorage = new Storage();
        this.tasks = new TaskList(taskStorage);
        this.ui = new Ui(tasks);
        this.parser = new Parser();
    }

    /**
     * to run the chat bot.
     */
    public void run() {

        ui.showWelcome();
        boolean isExit = false;
        while(!isExit) {
            try {
                String fullCommand = ui.readLine();
                ui.printFormatting();
                Command c = parser.parse(fullCommand);
                c.execute(taskStorage, tasks, ui);
                isExit = c.isExit();

            } catch (DukeException ex) {

                ui.showError(ex);

            } finally {
                ui.printFormatting();

            }
        }

        taskStorage.storeToStorage(tasks.getList());

    }

    /**
     * Main method.
     *
     * @param args arguments
     */
    public static void main(String[] args) {
        new Duke().run();
    }


}












