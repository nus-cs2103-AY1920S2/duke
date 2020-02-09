package com.nus.duke.controller;

import com.nus.duke.dao.DAOInterface;
import com.nus.duke.dao.InMemDAO;
import com.nus.duke.tasks.Tasks;
import java.util.List;
import java.util.stream.Collectors;

public class TaskController {
    private DAOInterface dataObj = new InMemDAO();

    private String stringify(Tasks t) {
        return String.format("[%s][%s] %s", t.getStatus().toString(),
                                            t.getType().toString(),
                                            t.getName());
    }

    private boolean setTask(Tasks task, Tasks.TASK_STATUS lvl) {
        this.dataObj.set(task, lvl);
        return true;
    }

    public boolean createNewTask(String name) {
        Tasks task = new Tasks(name);
        Boolean result = this.dataObj.add(task);
        return result;
    }

    public Tasks getTask(String name) {
        return dataObj.search(name);
    }

    public boolean checkTask(String name) {
        return this.getTask(name) != null;
    }

    public List<String> getAllTasks() {
        List<Tasks> Tasks = dataObj.getAll();
        List<String> taskString = Tasks.stream()
                                        .map(eachTask -> stringify(eachTask))
                                        .collect(Collectors.toList());
        return taskString;
    }

    public boolean markTask(String name) {
        Tasks t = this.getTask(name);
        return this.setTask(t, Tasks.TASK_STATUS.COMPLETE);
    }

    public boolean unmarkTask(String name) {
        Tasks t = this.getTask(name);
        return this.setTask(t, Tasks.TASK_STATUS.INCOMPLETE);
    }

    public boolean removeTask(String name) {
        return false;
    }
}
