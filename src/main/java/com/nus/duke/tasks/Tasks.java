package com.nus.duke.tasks;

public class Tasks {
    public enum TASK_STATUS {
        INCOMPLETE,
        COMPLETE,
    }

    private String name;
    private TASK_STATUS status;

    public Tasks(String name) {
        this(name, TASK_STATUS.INCOMPLETE);
    }

    public Tasks(String name, TASK_STATUS status) {
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return this.name;
    }

    public TASK_STATUS getStatus() {
        return this.status;
    }

    public void changeStatus(TASK_STATUS status) {
        this.status = status;
    }
}
