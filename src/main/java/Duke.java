import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private Ui ui;
    private Parser parser;
    private Storage storage;

    static boolean break_checker = false;

    public static void main(String[] args) throws DukeException, IOException {
        new Duke("data/fruits.txt").run();
    }

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser(storage);
    }

    private void run() throws DukeException, IOException {
        Scanner sc = new Scanner(System.in);


        // Welcome message for the user
        String welcome_message = "____________________________________________________________\n" +
                "Hello! I'm Duke\n" + "What can I do for you today?\n" +
                "____________________________________________________________";

        // Lines are for in between the two words
        System.out.println(welcome_message);

        while (sc.hasNext()) {
            String input = sc.nextLine();

            parser.understand_user_input(input);
            if(break_checker) {
                break;
            }
        }
    }



}