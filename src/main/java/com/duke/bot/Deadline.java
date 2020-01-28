package com.duke.bot;

public class Deadline extends Task {
    private String dateBy;

    public Deadline(String title, boolean isDone, String dateBy) {
        super(title, isDone);
        this.dateBy = dateBy;
    }

    public Deadline(String title, String dateBy) {
        super(title);
        this.dateBy = dateBy;
    }

    public String getDateBy() {
        return dateBy;
    }

    @Override
    public String toString() {
        return String.format(
                "[D][%s] %s (by: %s)",
                isDone() ? "\u2713" : "\u2717",
                getTitle(),
                dateBy
        );
    }
}
