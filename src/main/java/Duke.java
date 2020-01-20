import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int nextIndex = 0;

        String greeting = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(greeting);

        String userInput = sc.nextLine();
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                for (int i = 0; i < nextIndex; i++) {
                    System.out.println(i+1 + tasks[i].toString());
                }
            } else if (userInput.length() > 5 && userInput.substring(0, 4).equals("done")){
                int index = Integer.valueOf(userInput.substring(5));
                tasks[index].markDone();
                System.out.println("Nice! I've marked this task as done:\n  "
                        + tasks[index].toString());
            } else {
                tasks[nextIndex] = new Task(userInput);
                nextIndex++;
                System.out.println("added: " + userInput);
            }
            userInput = sc.nextLine();
        }
        System.out.print("Bye. Hope to see you again soon!");
    }
}