import java.util.ArrayList;

/**
 * The Ui class deals with interactions with the user.
 */
public class Ui {
    TaskList taskList = new TaskList();

    /**
     * Prints a welcome message.
     */
    public String welcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        return "Hello from\n" + logo;
    }

    /**
     * Prints a 'delete' message after a task is deleted.
     * @param task The task to be deleted.
     */
    public String deleteMessage(Task task) {
        return "Noted. I've removed this task:\n" + task + "\nNow you have " + (taskList.numOfTasks() - 1) + " "
                + (taskList.numOfTasks() == 1? "task" : "tasks") + " in the list.";
    }

    /**
     * Prints an 'add' message after a new task is added.
     * @param task The new task that is added.
     */
    public String addMessage(Task task) {
        return "Got it. I've added this task:\n" + task + "\nNow you have " + taskList.numOfTasks() + " "
                + (taskList.numOfTasks() == 1? "task" : "tasks") + " in the list.";
    }

    /**
     * Prints a 'done' message when a task is marked done.
     * @param task The task that is done.
     */
    public String doneMessage(Task task) {
        return "Nice! I've marked this task as done: \n" + task;
    }

    /**
     * Prints the list of tasks.
     */
    public String printList() {
        String result;
        result = "Here are the tasks in your list:\n";
        int count = 1;
        for (int i = 0; i < taskList.numOfTasks(); i++) {
            result = result + count + ". " + taskList.getTask(i) + "\n";
            count++;
        }
        return result;
    }

    /**
     * Prints the error message.
     * @param msg The error message.
     */
    public void showError(String msg) {
        System.out.println(msg);
    }

    public String printSelected(ArrayList<Integer> arr) {
        String result;
        if (arr.isEmpty()) {
            result = "No tasks found";
        } else {
            result = "Here are the matching tasks in your list:\n";
            int count = 1;
            for (int i = 0; i < arr.size(); i++) {
                result = result + count + ". " + taskList.getTask(arr.get(i)) + "\n";
                count++;
            }
        }
        return result;
    }
}
