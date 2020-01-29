package com.duke.bot;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private LocalDate dateBy;

    public Deadline(String title, boolean isDone, LocalDate dateBy) {
        super(title, isDone);
        this.dateBy = dateBy;
    }

    public Deadline(String title, LocalDate dateBy) {
        this(title, false, dateBy);
    }

    public LocalDate getDateBy() {
        return dateBy;
    }

    @Override
    public Deadline setDone(boolean isDone) {
        return new Deadline(getTitle(), isDone, dateBy);
    }

    @Override
    public String toString() {
        return String.format(
                "[D][%s] %s (by: %s)",
                isDone() ? "\u2713" : "\u2717",
                getTitle(),
                dateBy.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
        );
    }
}
