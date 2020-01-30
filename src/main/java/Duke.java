import java.util.Scanner;
import java.io.IOException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    public static int pendingTask = 0;
    public static Scanner sc = new Scanner(System.in);

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            System.out.println("error somewhere");
        }
    }

    public void run() throws IOException {
        ui.printOpeningScreen();
        Parser parser = new Parser(tasks);
        String input = "";
        while ( ! (input = sc.nextLine()).equals ("bye")) {
            ui.commandBreak();
            parser.parse(input);
            ui.commandBreak();
        }
        storage.save(tasks);
        ui.closingScreen();
    }

    public static void main(String[] args) throws IOException {
        new Duke("data/duke.txt").run();
    }
}