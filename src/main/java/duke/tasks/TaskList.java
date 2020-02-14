package duke.tasks;

import duke.exceptions.DukeBadIndexException;
import duke.util.Ui;
import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> list) {
        assert !list.isEmpty();
        this.list = list;
    }

    public ArrayList<Task> getList() {
        return list;
    }

    public int numTasks() {
        return list.size();
    }

    public int getTaskIndex(Task t) {
        return getList().indexOf(t) + 1;
    }

    /**
     * Method to add a task into the task list.
     * @param task task to be added into list.
     */
    public void addTask(Task task) {
        getList().add(task);
    }

    /**
     * Method to mark task at a specified index as done.
     * @param index of task to be marked as done.
     * @throws DukeBadIndexException if the index is negative or out of range.
     */
    public void setDone(int index) throws DukeBadIndexException {
        if (index > numTasks()) {
            throw new DukeBadIndexException(index);
        }
        getList().get(index - 1).setDone();
    }

    /**
     * Method to delete task at a specified index.
     * @param index of task to be deleted.
     * @throws DukeBadIndexException if the index is negative or out of range.
     */
    public void deleteTask(int index) throws DukeBadIndexException {
        if (index > numTasks() - 1) {
            throw new DukeBadIndexException(index);
        }

        Task t = getList().get(index - 1);
        getList().remove(t);
    }

    /**
     * Method to search for tasks in a task list that have a task name that matches a keyword.
     * @param keyword to be searched for.
     * @return TaskList containing tasks with task name that contains keyword.
     */
    public TaskList search(String keyword) {
        ArrayList<Task> results = new ArrayList<>();
        for (Task t : list) {
            if (t.getTaskName().contains(keyword)) {
                results.add(t);
            }
        }
        return new TaskList(results);
    }

    /**
     * Checks if task list contains task task.
     * @param task to be checked
     * @return
     */
    public boolean isDuplicate(Task task) {
        boolean isDuplicate = false;
        for (Task e : getList()) {
            if (e.equals(task)) {
                isDuplicate = true;
            }
        }
        return isDuplicate;
    }

    @Override
    public String toString() {
        String toPrint = "";
        if (numTasks() == 0) {
            toPrint = Ui.setBorder("You have nothing on your list.");
        } else {
            StringBuilder msg = new StringBuilder("Here is your list:\n");
            for (Task e : list) {
                msg.append("\t(").append(getTaskIndex(e)).append(") ").append(e.toString()).append("\n");
            }
            toPrint += Ui.setBorder(msg.toString());
        }
        return toPrint;
    }

}
