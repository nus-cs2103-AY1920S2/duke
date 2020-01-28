package duke;

import duke.task.Task;

import java.util.Scanner;

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

    /**
     * Prints the specified message and the tasks in the specified TaskList.
     * @param tasks The TaskList that contains the tasks to be printed.
     */
    public void showMatchingTasks(TaskList tasks) {
        if (tasks.isEmpty()) {
            showToUser("There are no matching tasks in your list.");
        } else {
            showLine();
            print("Here are the matching tasks in your list:");
            for (Task task : tasks.getTasks()) {
                print("  " + task);
            }
            showLine();
            print("");
        }
    }

    public void showGetTasks(TaskList filteredTasks) {
        showMatchingTasks(filteredTasks);
    }

    /**
     * Prints the tasks with the specified keyword.
     * @param filteredTasks The TaskList that contains tasks with the specified keyword.
     */
    public void showFindTasks(TaskList filteredTasks) {
        showMatchingTasks(filteredTasks);
    }
}
