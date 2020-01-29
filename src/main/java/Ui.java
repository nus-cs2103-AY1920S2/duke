import java.util.Scanner;

public class Ui {
    private Scanner reader;

    public Ui(){
        this.reader = new Scanner(System.in);
    }

    public String getInput() {
        return reader.nextLine();
    }

    public void printBreak() {
        System.out.println("    ______________________________________________________________");
    }

    public void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printBreak();
    }

    public void printGreet() {
        System.out.println("    Hello! I'm Duke");
        System.out.println("    What can I do for you?");
        printBreak();
    }

    public void list(TaskList tasks) {
        printBreak();
        System.out.println("    Here are the tasks in your list:");
        for (int i = 1; i <= tasks.getTaskNumber(); i++) {
            Task currTask = tasks.getTask(i - 1);
            System.out.println("    " + i + "." + currTask);
        }
        printBreak();
    }

    public void add(Task t, TaskList tasks) {
        printBreak();
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + t);
        tasks.addTask(t);
        System.out.println("    Now you have " + tasks.getTaskNumber() + " tasks in the list.");
        printBreak();
    }

    public void markDone(Task currTask) {
        printBreak();
        System.out.println("    Nice! I've marked this task as done:");
        currTask.markAsDone();
        System.out.println("      " + currTask);
        printBreak();
    }

    public void delete(Task currTask, TaskList tasks) {
        printBreak();
        System.out.println("    Noted. I've removed this task:");
        System.out.println("      " + currTask);
        tasks.removeTask(currTask);
        System.out.println("    sNow you have " + tasks.getTaskNumber() + " tasks in the list.");
        printBreak();
    }

    public void printExit() {
        printBreak();
        System.out.println("    Bye. Hope to see you again soon!");
        printBreak();
    }

    public void printException(String message) {
        printBreak();
        System.out.println(message);
        printBreak();
    }

}
