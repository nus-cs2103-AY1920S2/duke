import java.lang.String;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    private List<Task> tasks;

    private Duke() {
        this.tasks = new ArrayList<>();
    }

    private static void greet() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    private void echo(Scanner sc) {
        String userInput = sc.nextLine();

        if (userInput.equals("list")) {
            printList();
            System.out.println();
            echo(new Scanner(System.in));
        } else if (!userInput.equals("bye")) {
            tasks.add(Task.createTask(userInput));
            System.out.println("added " + userInput);
            System.out.println();
            echo(new Scanner(System.in));
        } else {
            Duke.printByeMsg();
        }
    }

    private void printList() {
        for (int i = 0; i < tasks.size(); ++i) {
            System.out.printf("%d. %s\n", i + 1, tasks.get(i).getTaskName());
        }
    }

    private static void printByeMsg() {
        System.out.println("Bye. Hope to see you again soon! :)");
    }

    public static void main(String[] args) {

        Duke duke  = new Duke();

        Duke.greet();

        duke.echo(new Scanner(System.in));
    }
}
