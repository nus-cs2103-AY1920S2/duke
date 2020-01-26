package com.duke.bot;

public class Task {
    private String title;
    private boolean isDone;

    public Task(String title) {
        this.title = title;
        isDone = false;
    }

    public String getTitle() {
        return title;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }
}
