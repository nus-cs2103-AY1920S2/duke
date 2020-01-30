import java.io.File;
import java.util.Scanner;

public class Duke {

    /**
     * Display the logo.
     */
    static void intro() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n\n";
        String comment = "Your personal assistant chatbot.\n";
        System.out.println("Hello from\n" + logo + comment);
    }

    /**
     * Main chat logic.
     */
    static void chat() {
        String path = "data" + File.separator + "duke.txt";
        Chatbot duke = new Chatbot("Duke", TaskList.load(path), path);
        Scanner in = new Scanner(System.in);
        String message = "";

        intro();
        duke.say(duke.greet());

        do {
            System.out.print("You:  ");
            message = in.nextLine();
            System.out.println();

            duke.say(duke.reply(message));
        } while (!message.toLowerCase().equals("bye"));

        in.close();
    }

    public static void main(String[] args) {
        chat();
    }
}