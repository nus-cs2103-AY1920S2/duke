import tasks.Task;

import java.util.ArrayList;

/**
 * The Ui class deals with interactions with the user.
 */
public class Ui {
    TaskList taskList = new TaskList();

    /**
     * Generates a welcome message.
     * @return The welcome message.
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
     * Generates a delete message.
     * @param task The deleted task.
     * @return The delete message.
     */
    public String deleteMessage(Task task) {
        return "Noted. I've removed this task:\n" + task + "\nNow you have " + (taskList.numOfTasks() - 1) + " "
                + (taskList.numOfTasks() == 1? "task" : "tasks") + " in the list.";
    }

    /**
     * Generates the add message.
     * @param task The newly added task.
     * @return The add message.
     */
    public String addMessage(Task task) {
        return "Got it. I've added this task:\n" + task + "\nNow you have " + taskList.numOfTasks() + " "
                + (taskList.numOfTasks() == 1? "task" : "tasks") + " in the list.";
    }


    /**
     * Generates a done message.
     * @param task The task that is done.
     * @return The done message.
     */
    public String doneMessage(Task task) {
        return "Nice! I've marked this task as done: \n" + task;
    }

    /**
     * Generates the string for the task list.
     * @return The string of tasks.
     */
    public String printList() {
        String result;
        if (taskList.numOfTasks() == 0) {
            return "Gr8! You currently have no tasks!";
        }
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

    /**
     * Generates the string for the task array.
     * @param arr Array of tasks.
     * @return The string of selected tasks.
     */
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
