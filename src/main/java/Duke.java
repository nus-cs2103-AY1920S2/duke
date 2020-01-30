import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
public class Duke {
    static Ui ui = new Ui();
    static Parser parser = new Parser();
    static Storage storage = new Storage();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        processList(sc);
    }

    /**
     * This method reads user input and process it.
     */
    private static void processList(Scanner sc) throws IllegalInstructionException, NumberFormatException, DateTimeParseException {
        ui.greeting();
        TaskList list = new TaskList();
        storage.loadTxt(list, ui);
        while (!sc.hasNext("bye")) {
            parser.parse(ui, list, storage, sc.nextLine());
        }
        ui.bye();
        return;
    }
}
