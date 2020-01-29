package duke.pack;

import java.util.ArrayList;

/**
 * represents list of all the tasks
 */
public class TaskList {
    protected ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * adds given task to the list
     * @param task the task to be added
     */
    public void addTask(Task task) {
        list.add(task);
    }

    /**
     * deletes given task from list
     * @param taskNum integer value of the task to be deleted
     * @return the deleted task
     */
    public Task deleteTask(int taskNum) {
        return list.remove(taskNum - 1);
    }

    public int getSize() {
        return list.size();
    }

    public ArrayList<Task> getList() {
        return list;
    }

    public Task markAsDone(int taskNum) {
        list.get(taskNum - 1).markAsDone();
        return list.get(taskNum - 1);
    }

    /**
     * prints all the tasks in the list
     */
    public void printList() {
        System.out.println("    Here are your tasks:");

        for (int i = 1; i <= list.size(); i++) {
            System.out.println("    " + i + ". " + list.get(i - 1));
        }
    }


}
