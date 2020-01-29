public class Duke {
    public static Ui ui = new Ui();
    public static TaskList taskList = new TaskList();
    public static Storage storage = new Storage("duke.txt");
    public static Parser parser = new Parser();

    public static void main(String[] args) {
        ui.start();
        storage.retrieveInfo();
        parser.parse();
        storage.updateInfo();
    }
}