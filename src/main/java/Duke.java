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
        String[] list = new String[100];
        int numTasks = 0;

        while (!(input = sc.nextLine()).equals("bye")) {
            if (input.equals("list")) {
                printList(list, numTasks);
            } else {
                list[numTasks] = input;
                numTasks++;
                System.out.println("added: " + input + "\n");
            }
        }
        System.out.println("See you soon!");
    }

    public static void printList(String[] list, int numTasks) {
        for (int i = 1; i <= numTasks; i++) {
            System.out.printf("%d. %s\n", i, list[i - 1]);
        }
    }
}
