package com.duke.bot;

public class Todo extends Task {
    public Todo(String title, boolean isDone) {
        super(title, isDone);
    }

    public Todo(String title) {
        this(title, false);
    }

    @Override
    public Task setDone(boolean isDone) {
        return new Todo(getTitle(), isDone);
    }

    @Override
    public String toString() {
        return String.format(
                "[T][%s] %s",
                isDone() ? "\u2713" : "\u2717",
                getTitle()
        );
    }
}
