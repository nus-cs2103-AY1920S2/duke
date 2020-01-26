package com.duke.bot;

public class Todo extends Task {
    public Todo(String title) {
        super(title);
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
