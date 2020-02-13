package com.nus.duke.controller;

import com.nus.duke.Exception.*;
import com.nus.duke.dao.DAOFactory;
import com.nus.duke.dao.DAOInterface;
import com.nus.duke.parser.Parser;
import com.nus.duke.storage.FileStorage;
import com.nus.duke.storage.StorageInterface;
import com.nus.duke.tasks.Tasks;
import javafx.util.Pair;

import java.util.List;
import java.util.stream.Collectors;

public class TaskController {
    private DAOInterface dataObj = DAOFactory.getOrCreate();
    private StorageInterface storage = new FileStorage();

    private String stringify(Tasks t) {
        StringBuilder strBldr = new StringBuilder();
        strBldr.append(String.format("[%s][%s] ", t.getStatus().toString(), t.getType().toString()));

        if (t.getName().contains("/by")) {
            Pair<String, String> parsedInput = Parser.tokenize(t.getName(), "/by");
            strBldr.append(String.format("%s (by: %s)", parsedInput.getKey(), parsedInput.getValue()));
        }
        else if (t.getName().contains("/at")) {
            Pair<String, String> parsedInput = Parser.tokenize(t.getName(), "/at");
            strBldr.append(String.format("%s (at: %s)", parsedInput.getKey(), parsedInput.getValue()));
        }
        else {
            strBldr.append(String.format("%s", t.getName()));
        }

        return strBldr.toString();
    }

    private boolean setTask(Tasks task, Tasks.TASK_STATUS lvl) {
        this.dataObj.set(task, lvl);
        return true;
    }

    private boolean setTask(Tasks task, Tasks.TASK_CATEGORY cat) {
        this.dataObj.set(task, cat);
        return true;
    }

    public Tasks getTask(String name) throws TaskNotFoundException {
        Tasks taskObj = dataObj.search(name);
        if (taskObj == null) throw new TaskNotFoundException("Error! Task does not exist");
        return taskObj;
    }

    public Tasks createNewTask(String name) {
        Tasks task = new Tasks(name);
        this.dataObj.add(task);
        return task;
    }

    public boolean removeTask(String name) throws DeleteTaskException, TaskNotFoundException {
        if (name == null) {
            throw new DeleteTaskException("Error! Can only mark a defined task");
        } else {
            Tasks t = this.getTask(name);
            return this.dataObj.delete(t);
        }
    }

    public List<String> getAllTasks() {
        List<Tasks> Tasks = dataObj.getAll();
        List<String> taskString = Tasks.stream()
                                        .map(eachTask -> stringify(eachTask))
                                        .collect(Collectors.toList());
        return taskString;
    }

    public boolean setMark(String name, boolean isMark) throws MarkException, TaskNotFoundException {
        if (name == null) {
            throw new MarkException("Error! Can only mark a defined task");
        } else {
            Tasks t = this.getTask(name);
            Tasks.TASK_STATUS status = isMark ? Tasks.TASK_STATUS.COMPLETE : Tasks.TASK_STATUS.INCOMPLETE;
            return this.setTask(t, status);
        }
    }

    public boolean newTodo(String name) throws TodoException {
        if (name == null) {
            throw new TodoException("Error! The description of a todo cannot be empty.");
        } else {
            Tasks t = this.createNewTask(name);
            return this.setTask(t, Tasks.TASK_CATEGORY.TODO);
        }
    }

    public boolean newDeadline(String name) throws DeadlineException {
        if (name == null) {
            throw new DeadlineException("Error! The description of a todo cannot be empty.");
        } else {
            Tasks t = this.createNewTask(name);
            return this.setTask(t, Tasks.TASK_CATEGORY.DEADLINE);
        }
    }

    public boolean newEvent(String name) throws EventException {
        if (name == null) {
            throw new EventException("Error! The description of a todo cannot be empty.");
        } else {
            Tasks t = this.createNewTask(name);
            return this.setTask(t, Tasks.TASK_CATEGORY.EVENT);
        }
    }

    public boolean persist() {
        final String fileLocation = "/Users/johan.kok/Desktop/nus/CS2103/duke/src/main/java/resources/storage/save.txt";
        storage.save(fileLocation);
        return true;
    }
}
