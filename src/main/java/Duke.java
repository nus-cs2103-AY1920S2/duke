import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private Scanner sc;
    private ArrayList<Task> tasks;

    public Duke() {
        this.sc = new Scanner(System.in);
        this.tasks = new ArrayList<>(100);
    }

    private void intro() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
    }

    private void echo(String input) {
        System.out.println("    " + input);
        this.waitInput();
    }

    private void printTasks() {
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + this.tasks.get(i));
        }
        this.waitInput();
    }

    private void waitInput() {
        String input = sc.nextLine();
        switch (input) {
            case "bye":
                System.out.println("\tBye. Hope to see you again soon!");
                break;
            case "list":
                printTasks();
                break;
            default:
                System.out.println("\tadded: " + input);
                tasks.add(new Task(input));
                this.waitInput();
                break;
        }
    }

    public void run() {
        this.intro();
        this.waitInput();
    }
}
