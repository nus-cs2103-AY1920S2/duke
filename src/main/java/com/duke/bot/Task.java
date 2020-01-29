package com.duke.bot;

public abstract class Task {
    private String title;
    private boolean isDone;

    public Task(String title, boolean isDone) {
        this.title = title;
        this.isDone = isDone;
    }

    public String getTitle() {
        return title;
    }

    public boolean isDone() {
        return isDone;
    }

    public abstract Task setDone(boolean isDone);
}
