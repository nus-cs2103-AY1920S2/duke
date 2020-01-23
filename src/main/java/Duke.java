import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        runDuke();
    }

    public static void greeting() {
        System.out.println("Hello! I'm Duchess");
        System.out.println("What can I do for you?");
    }

    public static void runDuke() {
        greeting();

        Scanner sc = new Scanner(System.in);
        String input;
        Task[] list = new Task[100];
        int numTasks = 0;

        while (!(input = sc.nextLine()).equals("bye")) {
            if (input.equals("list")) {
                printList(list, numTasks);
            } else if (input.substring(0, 4).equals("done")) {
                // use substring to separate "done" and task number
                int taskNum = Integer.parseInt(input.substring(5));
                list[taskNum - 1].markAsDone();
                System.out.println("I've finally done this task:");
                System.out.println(list[taskNum - 1].toString());
            } else {
                Task newTask = new Task(input);
                list[numTasks] = newTask;
                numTasks++;
                System.out.println("added: " + input + "\n");
            }
        }
        System.out.println("See you soon!");
    }

    public static void printList(Task[] list, int numTasks) {
        for (int i = 1; i <= numTasks; i++) {
            System.out.printf("%d. %s\n", i, list[i - 1]);
        }
    }
}
