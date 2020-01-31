import java.io.IOException;
import java.util.Scanner;

public class Duke {
    /**
     * The project is a product named Duke, a Personal Assistant Chatbot
     * that helps a person to keep track of various things.
     */
    static void printOutput(String output) {
        System.out.println("\t____________________________________________________________\n"
                + output + "\n\t____________________________________________________________");
    }

    public static void main(String[] args) {
        boolean isRunning = true;
        System.out.println(Output.START);
        printOutput(Output.HELLO);
        Scanner sc = new Scanner(System.in);
        FileSaver.readFromFile();

        while (isRunning) {
            try {
                String input = sc.nextLine();
                String output = Controller.readInput(input);
                if (output.equals(Output.BYE)) {
                    isRunning = false;
                }
                printOutput(output);
            } catch (DukeException e) {
                System.err.println(e);
            }
        }
        FileSaver.saveFile();
    }
}

