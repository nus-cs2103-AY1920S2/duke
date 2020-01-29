package com.duke.bot.task;

public class Todo extends Task {
    public Todo(String title, boolean isDone) {
        super(title, isDone);
    }

    public Todo(String title) {
        this(title, false);
    }

    @Override
    public Todo setDone(boolean isDone) {
        return new Todo(getTitle(), isDone);
    }

    @Override
    public String toString() {
        return String.format(
                "[T][%s] %s",
                isDone() ? "\u2713" : "\u2717", // \u2713 = tick, \u2717 = cross
                getTitle()
        );
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Todo)) {
            return false;
        }
        return super.equals(obj);
    }
}
