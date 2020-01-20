import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String greeting = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(greeting);

        String userInput = sc.nextLine();
        while (!userInput.equals("bye")) {
            System.out.println(userInput);
            userInput = sc.nextLine();
        }
        System.out.print("Bye. Hope to see you again soon!");
    }
}
