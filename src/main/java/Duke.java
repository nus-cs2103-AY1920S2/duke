import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser = new Parser();

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.greetUser();
        //Read input
        Scanner s = new Scanner(System.in);

        //Parse the input command
        String command = "";
        while (!Parser.isBye) {
            command = s.nextLine();
            Parser.addList(tasks);
            Parser.parse(command);
        }

        storage.writeBack("/duke/out/duke.txt", tasks);
    }

    public static void main(String[] args) throws DukeException {
        Duke duke = new Duke("/duke/out/duke.txt");
        duke.run();
    }
}
