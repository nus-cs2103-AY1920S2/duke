import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    Ui ui;

    static boolean break_checker = false;

    public static void main(String[] args) throws DukeException {
        new Duke().run();
    }


    private void run() throws DukeException{
        Scanner sc = new Scanner(System.in);
        ui = new Ui();


        // Welcome message for the user
        String welcome_message = "____________________________________________________________\n" +
                "Hello! I'm Duke\n" + "What can I do for you today?\n" +
                "____________________________________________________________";

        // Lines are for in between the two words
        System.out.println(welcome_message);

        while (sc.hasNext()) {
            String input = sc.nextLine();
            ui.user_input(input);
            if(break_checker) {
                break;
            }
        }
    }



}