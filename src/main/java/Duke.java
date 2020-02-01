import java.io.*;
import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e);
            tasks = new TaskList();
        }
    }

    public void run() {
        Scanner sc = new Scanner(System.in);

        ui.showStart();

        boolean isLooping = true;
        while (isLooping) {
            try {
                String input = sc.nextLine();
                Command comm = Parser.parseCommand(input);

                int index;

                switch (comm) {
                    case BYE:
                        isLooping = false;

                        ui.showExit();
                        break;
                    case LIST:

                        ui.showList(tasks);
                        break;
                    case DONE:
                        index = Parser.parseIndex(input);
                        tasks.done(index);
                        storage.save(tasks);

                        ui.showDone(tasks.get(index));
                        break;
                    case DELETE:
                        index = Parser.parseIndex(input);
                        Task deleted = tasks.delete(index);
                        storage.save(tasks);

                        ui.showDelete(deleted);
                        break;
                    default:
                        Task newTask = Parser.parseTask(input);
                        tasks.add(newTask);
                        storage.save(tasks);

                        ui.showAdd(newTask);
                        break;
                }
            } catch (DukeException e) {
                ui.showError(e);
            }
        }
    }
    public static void main(String[] args) throws FileNotFoundException{
        new Duke("save.txt").run();
    }
}
