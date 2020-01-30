/**
 * the class containing the main function to run the programme.
 */
public class Duke {
    private static Ui ui = new Ui();
    public static TaskList taskList = new TaskList();
    private static Storage storage = new Storage("data/duke.txt");
    private static Parser parser = new Parser();

    public static void main(String[] args) {
        ui.start();
        storage.retrieveInfo();
        parser.parse();
        storage.updateInfo();
    }
}
