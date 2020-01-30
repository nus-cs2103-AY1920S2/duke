import java.util.Scanner;

public class Ui {
    private Scanner scan;

    public Ui() {
        this.scan = new Scanner(System.in);
    }

    public boolean hasNextInput() {
        return scan.hasNext();
    }

    public String getNextInput() {
        return scan.nextLine();
    }

    public void showLoadingError() {
        System.out.println("Ooops! Cannot find file...");
    }

    public void print() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
    }

    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void printTasks(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= tasks.getSize(); i++) {
            System.out.println(i + ".  " + tasks.getTask(i - 1));
        }
    }

    public void printUnknownCommand() throws CommandNotFoundException {
        throw new CommandNotFoundException("We don't recognise this command!!!!");
    }

    public void acknowledgeDone(TaskList tasks, int index) throws InvalidIndexException {
        if (tasks.getSize() <= index || index < 0) {
            throw new InvalidIndexException("This index does not match any task in our list!! Try again...");
        }
        tasks.getTask(index).finishTask();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.getTask(index));
    }

    public void acknowledgeTodo(TaskList tasks, Task todo) {
        System.out.println("Got it. I've added this task:");
        System.out.println("   " + todo);
        System.out.println("Now you have " + tasks.getSize() + " task(s) in the list.");
    }

    public void acknowledgeDeadline(TaskList tasks, Task deadline) {
        System.out.println("Got it. I've added this task:");
        System.out.println("   " + deadline);
        System.out.println("Now you have " + tasks.getSize() + " task(s) in the list.");
    }

    public void acknowledgeEvent(TaskList tasks, Task event) {
        System.out.println("Got it. I've added this task:");
        System.out.println("   " + event);
        System.out.println("Now you have " + tasks.getSize() + " task(s) in the list.");

    }

    public void acknowledgeDelete(TaskList tasks, int index) throws InvalidIndexException {
        if (tasks.getSize() <= index || index < 0) {
            throw new InvalidIndexException("This index does not match any task in our list!! Try again...");
        }
        System.out.println("Noted. I've removed this task:");
        System.out.println(tasks.getTask(index));
        Task targetedTask = tasks.getTask(index);
        tasks.remove(targetedTask);
        System.out.println("Now you have " + tasks.getSize() + " tasks in the list");
    }
}
