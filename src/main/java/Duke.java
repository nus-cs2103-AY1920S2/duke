import java.util.Scanner;

/**
 * Duke is a Personal Assistant Chatbot that helps a person keep track of various things.
 */
public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        while (!input.equals("bye")) {
            System.out.println(input);
            input = scanner.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
