package duke.main;

public class Duke {
    enum Command {
        BYE, DEADLINE, DELETE, DONE, EVENT, LIST, TODO, CALENDAR, CLEAR
    }
    private Storage storage;
    private TaskList taskList;
    private Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (Exception e) {
            Ui.showLoadingError();
            taskList = new TaskList();
        }
    }
    public void run() {
        Ui.start();
        while (Parser.parseCommand(Ui.getInput(), taskList));
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
