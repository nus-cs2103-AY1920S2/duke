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
        intro();

        Chatbot duke = new Chatbot("Duke");
        Scanner in = new Scanner(System.in);
        String message = "";

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