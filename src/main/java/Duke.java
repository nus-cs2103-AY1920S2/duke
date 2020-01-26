import duke.command.Command;
import duke.task.TaskList;
import duke.util.Storage;
import duke.interaction.Ui;
import duke.interaction.Parser;

public class Duke {

    private TaskList taskList;
    private Storage storage;

    public static void main(String[] args) {
        // Init Duke
        Duke d = new Duke();
        // Greet user on start
        Ui.greet();
        // Duke's behaviour loop
        d.loop();
    }

    Duke() {
        storage = new Storage();
        taskList = new TaskList();
        storage.load(taskList);
    }

    private void exit() {
        Ui.sayBye();
        storage.saveTaskListToFile(taskList);
    }

    private void loop() {
        boolean is_exiting = false;
        Command c;
        do {
            String fullCommand = Ui.readCommand();
            c = Parser.parse(fullCommand);
            if (c != null) {
                c.execute(taskList, storage);
                is_exiting = c.isExit();
            }
        } while (!is_exiting);

        exit();
    }
}
