package data;

import data.exceptions.StorageOperationException;
import data.exceptions.TaskNotFoundException;
import data.task.Task;
import data.task.TaskList;
import storage.LoadFile;
import storage.StorageFile;

/**
 * Represents the taskManager system. Contains the data of the all tasks
 */
public class TaskManager {

    private StorageFile storager = new StorageFile();
    private LoadFile loader = new LoadFile();
    /* the tasks list within the system */
    private TaskList allTasks;
    private TaskList qualifiedTasks;

    public StorageFile getStorager() {
        return storager;
    }

    public LoadFile getLoader() {
        return loader;
    }

    /**
     * Creates an empty taskManager taskList.
     */
    public TaskManager() throws StorageOperationException {
        this.allTasks = new TaskList();
        this.qualifiedTasks = new TaskList();
        loader.readDom(allTasks);
    }

    /**
     * Parses the given arguments string as a single index number.
     *
     * @return the task list
     */
    public TaskList getTaskList() {
        return allTasks;
    }


    public TaskList getQualifiedTasks() {
        return qualifiedTasks;
    }

    /**
     * Search a key word int the whole list
     */
    public TaskList searchTask(String toSearch) {
        for (Task task:allTasks
             ) {
            if (task.getTaskDescription().indexOf(toSearch) != -1){
                qualifiedTasks.add(task);
            }
        }
        return qualifiedTasks;
    }

    /**
     * clear the qualified task list after 'find'
     * @param qualifiedTasks
     */
    public void clearQualifiedTasks(TaskList qualifiedTasks) {
        qualifiedTasks.clear();
    }

    /**
     * Adds a task to the task list.
     */
    public void addTask(Task toAdd) {
        allTasks.add(toAdd);
    }

    /**
     * check if contains the task
     *
     * @param toSearch target task
     */
    public boolean containsTask(Task toSearch) {
        return allTasks.contains(toSearch);
    }

    /**
     *
     * @param toRemove target task
     * @throws TaskNotFoundException if target task does not exist
     */
    public void removeTask(Task toRemove) throws TaskNotFoundException {
        allTasks.remove(toRemove);
    }


    /**
     * clears all tasks from the task list.
     * call by clear function
     */
    public void clear() {
        allTasks.clear();
    }

    /**
     * compare this task with other task
     *
     * @param other target task
     * @return true if tasks are equal
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskManager // instanceof handles nulls
                && this.allTasks.equals(((TaskManager) other).allTasks));
    }

    /**
     * Set target index task as done.
     * @param doneId target index
     * @throws TaskNotFoundException if target task does not exist
     */
    public void doneTask(int doneId) throws TaskNotFoundException {
        //find the task
        allTasks.getInternalList().get(doneId).setDone(true);
    }

    public void updateTaskIndex(){
        for (int index = 1; index <= allTasks.getInternalList().size(); index++) {
            allTasks.getInternalList().get(index - 1).setTaskIndex(index);
        }
    }
}
