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
        System.out.println("I'm sorry, but I don't know what that means!");
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
        String[] cmd = input.split("\\s", 2);
        switch (cmd[0].toLowerCase()) {
        case "todo":
            if (cmd.length < 2 || cmd[1].trim().equals("")) {
                System.out.println("\tOOPS! The description of a todo cannot be empty.");
                this.waitInput();
            } else {
                Task newTask = new Todo(cmd[1].trim());
                this.addTask(newTask);
            }
            break;
        case "event":
            if (cmd.length < 2 || cmd[1].trim().equals("")) {
                System.out.println("\tOOPS! The description of a event cannot be empty.");
                this.waitInput();
            } else {
                String[] info = cmd[1].split("/at");
                if (info.length < 2 || info[1].trim().equals("")) {
                    System.out.println("\tOOPS! Please input /at Date Time (e.g. /at Mon 2-4pm)");
                    this.waitInput();
                } else {
                    Task newTask = new Event(info[0].trim(), info[1].trim());
                    this.addTask(newTask);
                }
            }
            break;
        case "deadline":
            if (cmd.length < 2 || cmd[1].trim().equals("")) {
                System.out.println("\tOOPS! The description of a deadline cannot be empty.");
                this.waitInput();
            } else {
                String[] info = cmd[1].split("/by");
                if (info.length < 2 || info[1].trim().equals("")) {
                    System.out.println("\tOOPS! Please input /by Date (e.g. /by Feb 3rd)");
                    this.waitInput();
                } else {
                    Task newTask = new Deadline(info[0].trim(), info[1].trim());
                    this.addTask(newTask);
                }
            }
            break;
        case "delete":
            deleteTask(cmd[1]);
            break;
        case "list":
            printTasks();
            break;
        case "done":
            markAsDone(cmd[1]);
            break;
        case "bye":
            System.out.println("\tBye. Hope to see you again soon!");
            break;
        default:
            echo(input);
            break;
        }
    }

    private void markAsDone(String index) {
        int num = Integer.parseInt(index.trim());
        if (num < 1 || num > this.tasks.size()) {
            System.out.println("\tThe index inputted is not in the list!");
        } else {
            Task toBeDone = this.tasks.get(num - 1);
            toBeDone.toggleIsDone();
            System.out.println("\tGood job! You have completed this task!");
            System.out.println("\t\t" + toBeDone);
        }
        this.waitInput();
    }

    private void addTask(Task task) {
        System.out.println("\tGot it! I've added this task:");
        this.tasks.add(task);
        System.out.println("\t\t" + task);
        System.out.println("\tYou have total of " + this.tasks.size() + " tasks in the list.");
        this.waitInput();
    }

    private void deleteTask(String index) {
        int num = Integer.parseInt(index.trim());
        if (num < 1 || num > this.tasks.size()) {
            System.out.println("\tThe index inputted is not in the list!");
        } else {
            Task deleted = this.tasks.get(num - 1);
            System.out.println("\tOkay! I've removed this task!");
            System.out.println("\t\t" + deleted);
            this.tasks.remove(num - 1);
            System.out.println("\tYou have total of " + this.tasks.size() + " tasks in the list.");
        }
        this.waitInput();
    }

    public void run() {
        this.intro();
        this.waitInput();
    }
}