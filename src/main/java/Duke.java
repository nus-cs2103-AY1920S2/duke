import java.io.*;
import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;

    /**
     * Creates a Duke object to run the main program
     * @param filePath specifies the save file of the list of tasks
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            Ui.showError(e);
            tasks = new TaskList();
        }
    }

    /**
     * Runs the main program
     */
    public void run() {
        Scanner sc = new Scanner(System.in);

        Ui.showStart();

        boolean isLooping = true;
        while (isLooping) {
            try {
                String input = sc.nextLine();
                Command comm = Parser.parseCommand(input);

                int index;

                switch (comm) {
                case BYE:
                    isLooping = false;

                    Ui.showExit();
                    break;
                case LIST:

                    Ui.showList(tasks);
                    break;
                case DONE:
                    index = Parser.parseIndex(input);
                    tasks.done(index);
                    storage.save(tasks);

                    Ui.showDone(tasks.get(index));
                    break;
                case DELETE:
                    index = Parser.parseIndex(input);
                    Task deleted = tasks.delete(index);
                    storage.save(tasks);

                    Ui.showDelete(deleted);
                    break;
                default:
                    Task newTask = Parser.parseTask(input);
                    tasks.add(newTask);
                    storage.save(tasks);

                    Ui.showAdd(newTask);
                    break;
                }
            } catch (DukeException e) {
                Ui.showError(e);
            }
        }
    }
    public static void main(String[] args) throws FileNotFoundException{
        new Duke("save.txt").run();
    }
}
