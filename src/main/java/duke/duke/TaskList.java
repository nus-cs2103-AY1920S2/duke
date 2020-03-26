package duke.duke;

import java.util.ArrayList;

/**
 * Creates TaskList object.
 */
public class TaskList {
    ArrayList<Task> lst;

    public TaskList() {
        this.lst = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> lst) {
        this.lst = lst;
    }

    /**
     * Adds task to TaskList.
     * @param task from Task from Duke
     */
    public void addTask(Task task) {
        lst.add(task);
    }

    /**
     * Deletes task from TaskList.
     * @param task from Task from Duke
     * @throws DukeException when there is no Task to be deleted
     */
    public void deleteTask(Task task, TasksNum tasks) throws DukeException {
        if (lst.size() == 0) {
            throw new DukeException("No task to delete");
        }
        lst.remove(task);
        if (!task.getStatus()) {
            tasks.subNum();
        }
    }

    /**
     * Retrieves task from TaskList.
     * @param counter integer from Duke
     * @return specified Task
     */
    public Task getTask(int counter) {
        return lst.get(counter);
    }

    /**
     * Retrieves size of TaskList.
     * @return integer size size of TaskList
     */
    public int getSize() {
        return lst.size();
    }

}

