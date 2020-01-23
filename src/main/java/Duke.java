import java.util.Scanner;

public class Duke {
    private static boolean dukeActive = true;
    private static final Scanner scanner = new Scanner(System.in);
    private static String userInput = "";

    /**
     * Main method. Entry point for the Duke program.
     * @param args Command-line arguments. Unused.
     */
    public static void main(final String[] args) {
        final String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" + "|____/ \\__._|_|\\_\\___|\n";
        final String logoDivider = "++++++++++++++++++++++\n";

        System.out.println(logoDivider + logo + "\n" + logoDivider);

        dukePrompt(new String[]{"Hello! I'm Duke", "What can I do for you?"});
        while (dukeActive) {
            userInput = readUserInput();

            switch (userInput) {
            case "bye":
                dukeActive = false;
                dukePrompt("Bye. Hope to see you again!");
                break;

            default:
                dukePrompt(userInput);
                break;
            }
        }
    }

    private static void dukePrompt(String prompt) {
        System.out.println("Duke: " + prompt);
    }

    private static void dukePrompt(String[] prompts) {
        for (int i = 0; i < prompts.length; i++) {
            if (i == 0) {
                System.out.println("Duke: " + prompts[i]);
            } else {
                System.out.println("      " + prompts[i]);
            }
        }
    }

    private static String readUserInput() {
        System.out.printf("You:  ");
        return scanner.nextLine();
    }
}
