import java.util.Scanner;

public class Ui {

    void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I am \n" + logo + "\n" + "What can I do for you?");
    }

    void showLine() {
        System.out.println(Duke.LINE);
    }

    String readCommand() {
        Scanner scan = new Scanner(System.in);
        String command = scan.nextLine();
        return command;
    }

    void showError(String message) {
        System.out.println(message);
    }

    void showLoadingError() {
        System.out.println("The current duke.txt file is empty. Your inputs will be saved into an empty text file.");
    }
}
