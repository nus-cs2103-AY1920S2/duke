import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Duke {
    private Storage storage;
    private TaskList tasks = new TaskList();
    private Ui ui;

    /**
     * Initializes Duke and loads the TaskList from the files in the save directory
     * @param filePath
     */
    public Duke(Path filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            storage.loadFromFile(tasks);
        } catch (FileNotFoundException e) {
            System.out.println("    No prior tasklist found...");
        } catch (IOException e) {
            System.out.println("    Existing tasklist cannot be read...");
        } catch (ClassNotFoundException e) {
            System.out.println("    Existing tasklist cannot be read...");
        }
    }
    
    public static void main(String[] args) {
        Path saveDir = Paths.get(System.getProperty("user.dir"), "data.duke");
        new Duke(saveDir).run();
    }

    /** Starts Duke */
    public void run() {       
        ui.out("Hello! I'm Duke", "What can I do for you?");
        input: while (ui.hasNextInput()) {
            String command = ui.getCommand();
            try {
                switch (command.toLowerCase()) {
                case "bye":
                    ui.out("Bye. Hope to see you again soon!");
                    shutdown();
                    break input;
                case "list":
                    ui.out(tasks.list());
                    break;
                case "done":
                    ui.out(tasks.done(Integer.parseInt(ui.getArguments())));
                    storage.saveToFile(tasks);
                    break;
                case "todo":
                    ui.out(tasks.addTodo(ui.getArguments().split("/")));
                    storage.saveToFile(tasks);
                    break;
                case "deadline":
                    ui.out(tasks.addDeadline(ui.getArguments().split(" /by ")));
                    storage.saveToFile(tasks);
                    break;
                case "event":
                    ui.out(tasks.addEvent(ui.getArguments().split(" /at ")));
                    storage.saveToFile(tasks);
                    break;
                case "delete":
                    ui.out(tasks.delete(Integer.parseInt(ui.getArguments())));
                    storage.saveToFile(tasks);
                    break;
                default:
                    ui.out("invalid command:", "  " + command + " " + ui.getArguments(), "please try again");
                    break;
                }
            } catch(IncorrectArgumentException e) {
                ui.out(e.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutdown() {
        ui.close();
    }
}
