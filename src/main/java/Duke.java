import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        boolean isRunning  = true;
        Scanner scanner = new Scanner(System.in);
        System.out.println("____________________________________________________________\n" +
                " Hello! I'm Duke\n" +
                " What can I do for you?\n" +
                "____________________________________________________________");

        while (isRunning) {
            String input = scanner.nextLine();
            if (input.toLowerCase().equals("bye")) {
                System.out.println("____________________________________________________________\n" +
                        " Bye. Hope to see you again soon!\n" +
                        "____________________________________________________________");
                isRunning = false;
            } else {
                System.out.println("____________________________________________________________\n" +
                        " " + input + "\n" +
                        "____________________________________________________________");
            }
        }
    }
}
