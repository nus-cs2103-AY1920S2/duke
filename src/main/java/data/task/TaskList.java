package data.task;

import data.exceptions.TaskNotFoundException;
import data.task.Task;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TaskList implements Iterable<Task> {
    /** contains the real list that stores the tasks*/
    private final ArrayList<Task> internalList = new ArrayList<>();

    /**
     * Constructs empty task list.
     */
    public TaskList() {}

    /**
     * Checks if the list contains an equivalent task as the given description.
     * @param toCheck the task to-check
     * @return true if the task exists
     */
    public boolean contains(Task toCheck) {
        for (Task p : internalList) {
            if (p.isSameTask(toCheck)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the task list
     */
    public List<Task> getInternalList() {
        return internalList;
    }

    /**
     * Adds a task to the list.
     * @param toAdd the task to-add
     */
    public void add(Task toAdd)  {
        internalList.add(toAdd);
    }

    /**
     * Clears all tasks in list.
     */
    public void clear() {
        internalList.clear();
    }

    /**
     * Adds a task to the list.
     * @param toRemove the task to-remove
     * @throws TaskNotFoundException if the to-remove task does not exist
     */
    public void remove(Task toRemove) throws TaskNotFoundException{
        final boolean personFoundAndDeleted = internalList.remove(toRemove);
        if (!personFoundAndDeleted) {
            throw new TaskNotFoundException();
        }
    }

    /**
     * @return the next-to-add task index
     */
    public int getNextTaskIndex(){
        return internalList.size();
    }

    @Override
    public Iterator<Task> iterator() {
        return internalList.iterator();
    }

}
