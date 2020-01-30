package duke.main;

public class Duke {
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

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }

    public void run() {
        Ui.start();
        while (Parser.parseCommand(Ui.getInput(), taskList)) {

        }
    }

    enum Command {
        BYE, DEADLINE, DELETE, DONE, EVENT, LIST, TODO, CALENDAR, CLEAR
    }
}
