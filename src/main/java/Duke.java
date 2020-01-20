import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input;

        String greetingMessage = "Hello! I'm Duke\n" +
                "What can I do for you?";
        String endingCommand = "bye";


        System.out.println(greetingMessage);

        while (true) {
            input = sc.nextLine();
            if (input.equals(endingCommand)) {
                break;
            }
            System.out.println(input);
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
