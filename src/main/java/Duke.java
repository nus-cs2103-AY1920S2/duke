import java.io.IOException;
import java.lang.String;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    private TaskList tasks;
    private DukeUi ui;
    private Storage storage;

    private Duke() {
        this.tasks = TaskList.createTaskList();
        storage = Storage.createSrorageFile();
        this.ui = new DukeUi(System.in, System.out);
    }



    private void echo(Scanner sc) {
        String userCommand = sc.next();

        try {
            switch (userCommand) {
            case "list":
                System.out.println(tasks.printList());
                System.out.println();
                sc.useDelimiter("\\p{javaWhitespace}+");
                storage.saveToFile(tasks.printList());
                echo(sc);
                break;

            case "done":
                int targetIdx = sc.nextInt() - 1;
                this.tasks.getTask(targetIdx).markDone();
                System.out.println("Good job! I've marked this task as done:");
                System.out.printf("%d. %s\n", targetIdx + 1, tasks.getTask(targetIdx).toString());
                System.out.println();
                sc.useDelimiter("\\p{javaWhitespace}+");
                storage.saveToFile(tasks.printList());
                echo(sc);
                break;

            case "todo":
                sc.useDelimiter("\\n");
                String userInput = sc.nextLine();
                if (userInput.equals("")) {
                    sc.reset();
                    throw new DukeException("Uh-oh! Description of todo cannot be empty!");
                }
                TodoTask todoTask = TodoTask.createTodoTask(userInput);
                tasks.addTask(todoTask);
                System.out.println("Added: " + todoTask.toString());
                System.out.printf("Now you have %d task(s) on your list.\n", tasks.getSize());
                System.out.println();
                //echo(new Scanner(System.in);
                sc.useDelimiter("\\p{javaWhitespace}+");
                storage.saveToFile(tasks.printList());
                echo(sc);
                break;

            case "deadline":
                sc.useDelimiter("\\s*/by\\s*|\\n");
                String action = sc.next();
                String dateInput = sc.next();
                LocalDate byDate = LocalDate.parse(dateInput);
                DeadlineTask deadlineTask = DeadlineTask.createDeadlineTask(action, byDate);
                tasks.addTask(deadlineTask);
                System.out.println("Added: " + deadlineTask.toString());
                System.out.printf("Now you have %d task(s) on your list.\n", tasks.getSize());
                System.out.println();
                sc.useDelimiter("\\p{javaWhitespace}+");
                echo(sc);
                break;

            case "event":
                sc.useDelimiter("\\s*/at\\s*|\\n");
                action = sc.next();
                dateInput = sc.next();
                LocalDate atDate = LocalDate.parse(dateInput);
                EventTask eventTask = EventTask.createEventTask(action, atDate);
                tasks.addTask(eventTask);
                System.out.println("Added: " + eventTask.toString());
                System.out.printf("Now you have %d task(s) on your list.\n", tasks.getSize());
                System.out.println();
                sc.useDelimiter("\\p{javaWhitespace}+");
                storage.saveToFile(tasks.printList());
                echo(sc);
                break;

            case "bye":
                ui.printByeMsg();
                break;

            case "delete":
                int delIdx = sc.nextInt() - 1;
                if (delIdx >= tasks.getSize() || delIdx < 0) {
                    throw new DukeException("Oops! Target object is out of bounds!");
                }
                Task delTask = tasks.getTask(delIdx);
                tasks.deleteTask(delIdx);
                System.out.printf("Deleted: %s\n\n", delTask.toString());
                storage.saveToFile(tasks.printList());
                echo(sc);
                break;

            default:
                throw new DukeException("Oops! Invalid commmand word, perhaps you would want to try on of the following: todo 2.deadline 3.event 4.list 5.done 6.bye");

            }

        } catch (DukeException ex) {
            System.err.println(ex.getMessage());
            System.out.println();
            echo(sc);
        }
    }


    public static void main(String[] args) {

        Duke duke  = new Duke();

        duke.ui.greet();

        duke.echo(new Scanner(System.in));
    }

}
