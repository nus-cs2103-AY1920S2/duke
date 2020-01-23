import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    // Attributes
    private static ArrayList<String> textList = new ArrayList<>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);

        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");

        Duke.runDuke();
    }

    // Carry out Add, List commands if entered by user
    // Terminates when user gives exit signal
    private static void runDuke() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String nextInput = scanner.nextLine();
            // Validation check
            if (nextInput.equals("bye")) {
                Duke.stopDuke();
                break;
            }
            // Handle List command if any
            if (nextInput.equals("list")) {
                Duke.handleCommandList();
                continue;
            }

            // Handle Add Command
            Duke.handleCommandAdd(nextInput);
        }
    }

    // Print a closing message before stopping Duke
    private static void stopDuke() {
        String closingMessage = "Bye. Hope to see you again soon!";
        System.out.println("    " + closingMessage);
    }

    private static void handleCommandList() {
        int counter = 1;
        for (String s : Duke.textList) {
            System.out.println("    " + counter + ". " + s);
            counter++;
        }
    }

    private static void handleCommandAdd(String newText) {
        Duke.textList.add(newText);
        System.out.println("    added: " + newText);
    }
}
