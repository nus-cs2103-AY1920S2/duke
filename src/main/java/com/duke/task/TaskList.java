package com.duke.task;

import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> t) {
        tasks = t;
    }

    public Task markTask(int tindex) {
        Task out = tasks.get(tindex);
        out.isDone = true;
        return out;
    }

    public Task deleteTask(int tindex) {
        return tasks.remove(tindex);
    }

    public Task getTask(int tindex) {
        return tasks.get(tindex);
    }

    public Task addTask(Task t) {
        tasks.add(t);
        return t;
    }
}
