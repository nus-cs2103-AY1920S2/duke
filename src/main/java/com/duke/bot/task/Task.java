package com.duke.bot.task;

import java.util.Objects;

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Task)) {
            return false;
        }
        Task other = (Task) obj;
        return isDone == other.isDone && Objects.equals(title, other.title);
    }

    
}
