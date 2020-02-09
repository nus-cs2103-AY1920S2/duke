package com.duke.tag;

import com.duke.task.Task;

import java.util.ArrayList;
import java.util.List;

public class Tag {
    String tagName;
    List<Task> tasks = new ArrayList<>();

    public Tag(String name) {
        tagName = name;
    }

    public String getTagName() {
        return tagName;
    }

    public void addTaskToTag(Task task) {
        if (!tasks.contains(task)) {
            tasks.add(task);
        }
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void removeTaskFromTag(Task task) {
        tasks.remove(task);
    }
}
