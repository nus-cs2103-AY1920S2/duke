import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
    }

    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
        System.exit(0);
    }

    public String readCommand() {
        String command = sc.nextLine();
        return command;
    }

    public void printAdd(Task t) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + t);
        System.out.println("Now you have " + TaskList.size() + " " + (TaskList.size() == 1 ? "task" : "tasks") + " in the list.");
    }

    public void printDelete(Task toDelete) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + toDelete);
        System.out.println("Now you have " + TaskList.size() + " " + (TaskList.size() == 1 ? "task" : "tasks") + " in the list.");
    }

    public void printDone(int number, TaskList tasks) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + tasks.getList().get(number - 1));
    }

    public void listTask(TaskList tasks) {
        System.out.println("Here are the " + (tasks.getList().size() == 1 ? "task" : "tasks") + " in your list:");
        for (int i = 1; i <= tasks.getList().size(); i++) {
            System.out.println(i + "." + tasks.getList().get(i - 1));
        }
    }
}
