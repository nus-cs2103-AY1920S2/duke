import task.Task;

import java.util.ArrayList;

/**
 * this class used a arrayList to store a list of task. It enable user to access to the task easily.
 */
public class TaskList {
    ArrayList<Task> arrTask = new ArrayList<Task>();

    public TaskList() {
    }

    public TaskList(ArrayList<Task> tasks) {
        this.arrTask = tasks;
    }

    /**
     * this method printout all the task stored in arrayList when user enterd list command
     */
    public void printTaskList() {
        for (int i = 0; i < arrTask.size(); i++) {
            System.out.println((i + 1) + ". " + arrTask.get(i).toString());
        }
    }
}
