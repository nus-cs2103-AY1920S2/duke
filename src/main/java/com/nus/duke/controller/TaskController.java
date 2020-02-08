package com.nus.duke.controller;

import com.nus.duke.dao.DAOInterface;
import com.nus.duke.dao.InMemDAO;
import com.nus.duke.tasks.Tasks;
import com.nus.duke.ui.Greetings;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class TaskController {
    private DAOInterface dataObj = new InMemDAO();

    private String stringify(Tasks t) {
        return String.format("[%s][%s] %s", t.getStatus().toString(),
                                            t.getType().toString(),
                                            t.getName());
    }

    public boolean createNewTask(String name) {
        Tasks task = new Tasks(name);
        return this.dataObj.add(task);
    }

    public boolean listTask(String name) {
        Tasks task = dataObj.search(name);
        String taskString = this.stringify(task);
        Greetings.prettyPrint(taskString);
        return true;
    }

    public boolean listAllTasks() {
        ArrayList<Tasks> Tasks = dataObj.getAll();
        List<String> taskString = Tasks.stream()
                                        .map(eachTask -> stringify(eachTask))
                                        .collect(Collectors.toList());
        Greetings.prettyPrint(taskString);
        return true;
    }

    public boolean removeTask(String name) {
        return false;
    }
}
