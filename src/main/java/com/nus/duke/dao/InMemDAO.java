package com.nus.duke.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.nus.duke.tasks.Tasks;
import static com.nus.duke.tasks.Tasks.TASK_STATUS;

public class InMemDAO implements DAOInterface {
    private List<Tasks> tasksList = new ArrayList<>();

    @Override
    public boolean add(Tasks task) {
        this.tasksList.add(task);
        return true;
    }

    @Override
    public boolean set(Tasks task, TASK_STATUS status) {
        task.changeStatus(status);
        return true;
    }

    @Override
    public boolean set(Tasks task, Tasks.TASK_CATEGORY category) {
        task.changeCategory(category);
        return true;
    }

    @Override
    public Tasks search(String name) {
        Optional<Tasks> task = this.tasksList
                                    .stream()
                                    .filter(eachTask -> name.equals(eachTask.getName()))
                                    .findFirst();
        return task.isPresent() ? task.get() : null;
    }

    @Override
    public List<Tasks> filter(String condition) {
        List<Tasks> tasks = this.tasksList
                                .stream()
                                .filter(eachTask -> eachTask.getName().contains(condition))
                                .collect(Collectors.toList());
        return tasks;
    }

    @Override
    public boolean delete(Tasks task) {
        this.tasksList.remove(task);
        return true;
    }

    @Override
    public List<Tasks> getAll() {
        return this.tasksList;
    }
}
