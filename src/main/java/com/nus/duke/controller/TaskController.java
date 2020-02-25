package com.nus.duke.controller;

import com.nus.duke.exception.*;
import com.nus.duke.dao.DAOFactory;
import com.nus.duke.dao.DAOInterface;
import com.nus.duke.storage.FileStorage;
import com.nus.duke.storage.StorageInterface;
import com.nus.duke.tasks.DisplayTaskFormatter;
import com.nus.duke.tasks.Tasks;
import java.util.List;
import java.util.stream.Collectors;

public class TaskController {
    private DAOInterface dataObj = DAOFactory.getOrCreate();
    private StorageInterface storage = new FileStorage();

    private boolean setTask(Tasks task, Tasks.TASK_STATUS lvl) {
        this.dataObj.set(task, lvl);
        return true;
    }

    private boolean setTask(Tasks task, Tasks.TASK_CATEGORY cat) {
        this.dataObj.set(task, cat);
        return true;
    }

    private List<String> tasksToString(List<Tasks> tasks) {
        List<String> formattedTasks = tasks.stream()
                                            .map(eachTask -> DisplayTaskFormatter.stringify(eachTask))
                                            .collect(Collectors.toList());
        return formattedTasks;
    }

    /**
     * Initializes a new Tasks object
     *
     * @param   name    Task description
     * @return          Tasks object
     */
    public Tasks createNewTask(String name) {
        Tasks task = new Tasks(name);
        this.dataObj.add(task);
        return task;
    }

    /**
     * Getter method for seeking a task based on exact name matching
     *
     * @param   name    Task name
     * @return  Tasks   Tasks object
     */
    public Tasks getTask(String name) throws TaskNotFoundException {
        Tasks taskObj = dataObj.search(name);
        if (taskObj == null) throw new TaskNotFoundException("Error! Task does not exist");
        return taskObj;
    }

    /**
     * Searches for
     *
     * @param   filterString    Task description
     * @return                  Tasks names as a list of strings
     */
    public List<String> filterTasks(String filterString) {
        List<Tasks> tasks = dataObj.filter(filterString);
        return this.tasksToString(tasks);
    }

    /**
     * Returns a list of all tasks as their name
     *
     * @return                  Tasks names as a list of strings
     */
    public List<String> getAllTasks() {
        List<Tasks> tasks = dataObj.getAll();
        return this.tasksToString(tasks);
    }

    /**
     * Marks / unmarks a task
     *
     * @param   name            Tasks name
     * @param   isMark          Task is marked / unmarked
     * @return                  Tasks names as a list of strings
     */
    public boolean setMark(String name, boolean isMark) throws MarkException, TaskNotFoundException {
        if (name == null) {
            throw new MarkException("Error! Can only mark a defined task");
        } else {
            Tasks t = this.getTask(name);
            Tasks.TASK_STATUS status = isMark ? Tasks.TASK_STATUS.COMPLETE : Tasks.TASK_STATUS.INCOMPLETE;
            return this.setTask(t, status);
        }
    }

    /**
     * Creates a new TODO task
     *
     * @param   name            Tasks name
     * @return                  Creation succeeded / failed
     */
    public boolean newTodo(String name) throws TodoException {
        if (name == null) {
            throw new TodoException("Error! The description of a todo cannot be empty.");
        } else {
            Tasks t = this.createNewTask(name);
            return this.setTask(t, Tasks.TASK_CATEGORY.TODO);
        }
    }

    /**
     * Creates a new Deadline task
     *
     * @param   name            Tasks name
     * @return                  Creation succeeded / failed
     */
    public boolean newDeadline(String name) throws DeadlineException {
        if (name == null) {
            throw new DeadlineException("Error! The description of a todo cannot be empty.");
        } else {
            Tasks t = this.createNewTask(name);
            return this.setTask(t, Tasks.TASK_CATEGORY.DEADLINE);
        }
    }

    /**
     * Creates a new Event task
     *
     * @param   name            Tasks name
     * @return                  Creation succeeded / failed
     */
    public boolean newEvent(String name) throws EventException {
        if (name == null) {
            throw new EventException("Error! The description of a todo cannot be empty.");
        } else {
            Tasks t = this.createNewTask(name);
            return this.setTask(t, Tasks.TASK_CATEGORY.EVENT);
        }
    }

    /**
     * Saves stored tasks data out to a text file
     *
     * @return                  Persistance succeeded / failed
     */
    public boolean persist() {
        final String fileLocation = "/Users/johan.kok/Desktop/nus/CS2103/duke/src/main/java/resources/storage/save.txt";
        storage.save(fileLocation);
        return true;
    }

    /**
     * Deletes an existing task from storage
     *
     * @params  name            Tasks name
     * @return                  Deletion succeeded / failed
     */
    public boolean removeTask(String name) throws DeleteTaskException, TaskNotFoundException {
        if (name == null) {
            throw new DeleteTaskException("Error! Can only mark a defined task");
        } else {
            Tasks t = this.getTask(name);
            return this.dataObj.delete(t);
        }
    }
}
