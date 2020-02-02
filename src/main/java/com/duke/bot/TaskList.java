package com.duke.bot;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<Task> tasks;

    private TaskList() {
        this(0);
    }

    private TaskList(int n) {
        tasks = new ArrayList<>(n);
    }

    public static TaskList createTaskList() {
        return new TaskList();
    }

    public int getSize() {
        return tasks.size();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void deleteTask(int delIdx) throws DukeException {
        --delIdx;
        if (delIdx >= tasks.size() || delIdx < 0) {
            throw new DukeException("Oops! Target object is out of bounds!");
        }
        Task delTask = tasks.get(delIdx);
        tasks.remove(delIdx);
        System.out.printf("Deleted: %s\n\n", delTask.toString());
    }

    public String printList() {
        String output = "";
        output += ("Here is your list of tasks:\n");
        for (int i = 0; i < tasks.size(); ++i) {
            Task task = tasks.get(i);
            output = output.concat(String.format("%d. %s\n", i + 1, task.toString()));
        }
        return output;
    }

}
