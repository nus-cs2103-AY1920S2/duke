import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] tasks = new String[100];
        int nextIndex = 0;
        String greeting = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(greeting);

        String userInput = sc.nextLine();
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                for (int i = 0; i < nextIndex; i++) {
                    System.out.println(i+1 + ". " + tasks[i]);
                }
            } else {
                tasks[nextIndex] = userInput;
                nextIndex++;
                System.out.println("added: " + userInput);
            }
            userInput = sc.nextLine();
        }
        System.out.print("Bye. Hope to see you again soon!");
    }
}
