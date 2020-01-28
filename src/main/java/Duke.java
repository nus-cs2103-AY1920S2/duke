import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Duke {
    String filePath = "/Users/Simon/Documents/duke/src/main/java/saved.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) throws IOException{
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.displayLoadError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.displayIntro();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readInput();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parseCommand(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.displayError(e.getMessage());
            } catch (IOException e) {
                ui.displaySaveError();
            } finally {
                ui.showLine();
            }
        }
    }

    static ArrayList<Task> list = new ArrayList<>();

    private static void printFileContents(String filePath, ArrayList<Task> list) throws FileNotFoundException {
        File f = new File(filePath); // new file for given file path
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String nextLine = s.nextLine();
            String[] split = nextLine.split("|");
            String indicator = split[0];
            switch (indicator) {
                case "D":
                    Deadline deadline = new Deadline(split[2], split[3]);
                    if (split[1].equals("Y")) {
                        deadline.setCheck();
                    }
                    list.add(deadline);
                    break;

                case "E":
                    Event event = new Event(split[2], split[3]);
                    if (split[1].equals("Y")) {
                        event.setCheck();
                    }
                    list.add(event);
                    break;

                case "T":
                    Todo todo = new Todo(split[2]);
                    if (split[1].equals("Y")) {
                        todo.setCheck();
                    }
                    list.add(todo);
                    break;

                default:
                    break;
            }
        }
    }

    public static void main(String[] args) throws IOException{
        new Duke("/Users/Simon/Desktop/Y2S2/CS2103T/duke/src/main/java/duke.txt").run();
    }


}



