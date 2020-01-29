import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.FileOutputStream;
import java.time.LocalDate;

public class Duke {
    static Ui ui = new Ui();
    static Parser parser = new Parser();
    static Storage storage = new Storage();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        processlist(sc);
    }

    private static void processlist(Scanner sc) throws IllegalInstructionException, NumberFormatException, DateTimeParseException {
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
