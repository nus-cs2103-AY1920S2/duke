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
        System.out.println();
    }

    private void echo(Scanner sc) {
        String userCommand = sc.next();

        switch (userCommand) {
            case "list":
                this.printList();
                System.out.println();
                sc = sc.useDelimiter("\\p{javaWhitespace}+");
                echo(sc);
                break;

            case "done":
                int targetIdx = sc.nextInt() - 1;
                this.tasks.get(targetIdx).markDone();
                System.out.println("Good job! I've marked this task as done:");
                System.out.printf("%d. %s\n", targetIdx + 1, tasks.get(targetIdx).toString());
                System.out.println();
                sc = sc.useDelimiter("\\p{javaWhitespace}+");
                echo(sc);
                break;

            case "todo":
                String userInput = sc.nextLine();
                TodoTask todoTask = TodoTask.createTodoTask(userInput);
                tasks.add(todoTask);
                System.out.println("Added: " + todoTask.toString());
                System.out.printf("Now you have %d task(s) on your list.\n", tasks.size());
                System.out.println();
                //echo(new Scanner(System.in);
                sc = sc.useDelimiter("\\p{javaWhitespace}+");
                echo(sc);
                break;
            case "deadline":
                sc = sc.useDelimiter("\\s*/by\\s*|\\n");
                String userInput1 = sc.next();
                String userDue1 = sc.next();
                DeadlineTask deadlineTask = DeadlineTask.createDeadlineTask(userInput1, userDue1);
                tasks.add(deadlineTask);
                System.out.println("Added: " + deadlineTask.toString());
                System.out.printf("Now you have %d task(s) on your list.\n", tasks.size());
                System.out.println();
                sc = sc.useDelimiter("\\p{javaWhitespace}+");
                echo(sc);
                break;
            case "event":
                sc = sc.useDelimiter("\\s*/at\\s*|\\n");
                String userInput2 = sc.next();
                String userDue2 = sc.next();
                EventTask eventTask = EventTask.createEventTask(userInput2, userDue2);
                tasks.add(eventTask);
                System.out.println("Added: " + eventTask.toString());
                System.out.printf("Now you have %d task(s) on your list.\n", tasks.size());
                System.out.println();
                sc = sc.useDelimiter("\\p{javaWhitespace}+");
                echo(sc);
                break;

            case "bye":
                Duke.printByeMsg();
                break;
            default:
                break;
        }
    }

    private void printList() {
        System.out.println("Here is your list of tasks:");
        for (int i = 0; i < tasks.size(); ++i) {
            Task task = tasks.get(i);
            System.out.printf("%d. %s\n", i + 1, task.toString());
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
