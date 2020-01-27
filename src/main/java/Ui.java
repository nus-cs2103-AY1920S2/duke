import java.util.Scanner;
import java.time.LocalDate;

public class Ui {
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public String readCommand() {
        return sc.nextLine().trim();
    }

    public void print(String message) {
        System.out.println("    " + message);
    }

    public void showLine() {
        print("____________________________________________________________");
    }

    public void showToUser(String... message) {
        showLine();
        for (String m : message) {
            print(m);
        }
        showLine();
        print("");
    }

    public void showWelcome() {
        showToUser(" ____        _        ", 
                "|  _ \\ _   _| | _____ ", 
                "| | | | | | | |/ / _ \\", 
                "| |_| | |_| |   <  __/", 
                "|____/ \\__,_|_|\\_\\___|\n", 
                "Hello! I'm Duke", 
                "What can I do for you?");
    }

    public void showExit() {
        showToUser("Bye. Hope to see you again soon!");
        sc.close();
    }

    public void showError(String message) {
        showToUser("OOPS!!!" + message);
    }

    public void showTaskList(TaskList tasks) throws DukeException {
        if (tasks.isEmpty()) {
            showToUser("There are no tasks in the list.");
        } else {
            showLine();
            print("Here are the tasks in your list:");
            for (int i = 1; i <= tasks.size(); i++) {
                print(i + "." + tasks.get(i));
            }
            showLine();
            print("");
        }
    }

    public void showAddTask(Task task, TaskList tasks) {
        showToUser("Got it. I've added this task:", "  " + task, 
                "Now you have " + tasks.size() + " tasks in the list.");
    }

    public void showDoneTask(Task task, TaskList tasks) {
        showToUser("Nice! I've marked this task as done: ", "  " + task);
    }

    public void showDeleteTask(Task task, TaskList tasks) {
        showToUser("Noted. I've removed this task: ", "  " + task, 
                "Now you have " + tasks.size() + " tasks in the list.");
    }

    public void showGetTasks(TaskList filteredTasks, LocalDate date) {
        if (filteredTasks.isEmpty()) {
            showToUser("There are no tasks on " + date + ".");
        } else {
            showLine();
            print("Here are the tasks on " + date + ":");
            for (Task task : filteredTasks.getTasks()) {
                print("  " + task);
            }
            showLine();
            print("");
        }
    }
}
