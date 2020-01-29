package com.duke.bot.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private LocalDate dateAt;

    public Event(String title, boolean isDone, LocalDate dateAt) {
        super(title, isDone);
        this.dateAt = dateAt;
    }

    public Event(String title, LocalDate dateAt) {
        this(title, false, dateAt);
    }

    public LocalDate getDateAt() {
        return dateAt;
    }

    @Override
    public Event setDone(boolean isDone) {
        return new Event(getTitle(), isDone, dateAt);
    }

    @Override
    public String toString() {
        return String.format(
                "[E][%s] %s (at: %s)",
                isDone() ? "\u2713" : "\u2717", // \u2713 = tick, \u2717 = cross
                getTitle(),
                dateAt.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
        );
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Event)) {
            return false;
        }
        Event other = (Event) obj;
        return dateAt.equals(other.getDateAt()) && super.equals(obj);
    }
}
