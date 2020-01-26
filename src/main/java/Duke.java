import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        String workingDir = System.getProperty("user.dir");
        Path savePath = Paths.get(workingDir, "data", "duke.txt");
        storage = new Storage(savePath);
        ui = new Ui("     Hello! I'm Duke\n     What can I do for you?"
                , "     Bye. Hope to see you again soon!");
    }

    public void runUntilExit() {
        TaskList tasks = null;
        boolean isRunning = true;
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch(IOException e){
            ui.printMessage("     Sorry, I could not read the save file.");
        }
        ui.printWelcomeMessage();
        while (isRunning) {
            try {
                Command command = Parser.parseCommand(ui.getUserInput());
                if (command instanceof ExitCommand) {
                    isRunning = false;
                }
                command.execute(tasks, ui, storage);
            } catch (InvalidCommandException e) {
                ui.printException(e);
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.runUntilExit();
    }
}
