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
        int inputLength = userInput.length();

        if (userInput.equals("list")) {
            printList();
            System.out.println();
            echo(new Scanner(System.in));
        } else if (inputLength >= 4 && userInput.substring(0, 4).equals("done") && userInput.charAt(4) == ' ') {
            int targetIdx = Integer.parseInt(userInput.substring(5,inputLength)) - 1;
            this.tasks.get(targetIdx).markDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.printf("%d. [%s] %s\n\n", targetIdx + 1, tasks.get(targetIdx).getStatusIcon(), tasks.get(targetIdx).getTaskName());
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
            System.out.printf("%d. [%s] %s\n", i + 1, tasks.get(i).getStatusIcon(), tasks.get(i).getTaskName());
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
