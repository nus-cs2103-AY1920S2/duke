import java.io.FileNotFoundException;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private boolean newUser;

    public Duke(String filepath) {
        storage = new Storage(filepath);
        try {
            taskList = storage.getTaskFromMemory();
            newUser = false;
        } catch (FileNotFoundException e) {
            taskList = new TaskList();
            newUser = true;
        }
    }

    public void run() {
        Ui.hello();
        if (newUser) {
            Ui.newUser();
        } else {
            Ui.oldUser();
        }
        Parser p = new Parser();
        String command = Ui.readCommand();
        while (!command.toLowerCase().equals("bye")) {
            p.parseAndExecute(command, taskList, storage);
            storage.writeTaskToMemory(taskList);
            command = Ui.readCommand();
        }
        Ui.bye();
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}