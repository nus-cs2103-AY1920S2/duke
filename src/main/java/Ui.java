import java.util.Scanner;

/**
 * Ui class that handles user interactions.
 */
public class Ui {
    private Scanner sc;

    /**
     * Creates a Ui object.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * Displays a string to bid goodbye to the user and exits the program.
     */
    public String showBye() {
        return "Bye~ Hope to see you again soon!";
    }

    /**
     * Displays acknowledgement whenever a task is added.
     *
     * @param toAdd Task that will be added.
     */
    public String printAdd(Task toAdd) {
        String temp = "Got it. I've added this task:" + "\n" + toAdd + "\n"
                + "Now you have " + TaskList.size() + " "
                + (TaskList.size() == 1 ? "task" : "tasks")
                + " in the list.";
        return temp;
    }

    /**
     * Displays acknowledgement whenever a task is deleted.
     *
     * @param toDelete Task that will be deleted.
     */
    public String printDelete(Task toDelete) {
        String temp = "Noted. I've removed this task:" + "\n" + toDelete + "\n"
                + "Now you have " + TaskList.size() + " "
                + (TaskList.size() == 1 ? "task" : "tasks")
                + " in the list.";
        return temp;
    }

    /**
     * Displays acknowledgement whenever a task is done.
     *
     * @param number An Integer representation of the task index that is to be done.
     * @param tasks A TaskList object that contains ArrayList of Task.
     */
    public String printDone(int number, TaskList tasks) {
        String temp = "Nice! I've marked this task as done:" + "\n" + "\t"
                + tasks.getList().get(number - 1);
        return temp;
    }

    /**
     * Displays acknowledgement whenever user tries to search for a task.
     *
     * @param keyWord A string representation of what the user is trying to search in the list of tasks.
     */
    public String printFind(String keyWord) {
        int index = 1;
        boolean runOnce = true;
        String temp = "";
        for (int i = 0; i < TaskList.size(); i++) {
            if (TaskList.getList().get(i).getDescription().toLowerCase().contains(keyWord)) {
                if (runOnce) {
                    temp += "Here are the matching tasks in your list:\n";
                    runOnce = false;
                }
                temp += index + "." + TaskList.getList().get(i) + "\n";
                index++;
            }
        }
        if (index == 1) {
            temp += "There are no matching tasks in your list! Please try something else!";
        }
        return temp;
    }

    /**
     * Displays all the Task objects in the TaskList.
     *
     * @param tasks A TaskList object that contains ArrayList of Task.
     */
    public String listTask(TaskList tasks) {
        if (tasks.getList().size() == 0) {
            return "There are no task in the list! FREEDOM!";
        }
        String temp = "Here are the " + (tasks.getList().size() == 1 ? "task" : "tasks") + " in your list:\n";
        for (int i = 1; i <= tasks.getList().size(); i++) {
            temp += i + "." + tasks.getList().get(i - 1) + "\n";
        }
        return temp;
    }
}
