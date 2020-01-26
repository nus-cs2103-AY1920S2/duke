package com.duke.bot;

public class Event extends Task {
    private String dateAt;

    public Event(String title, String dateAt) {
        super(title);
        this.dateAt = dateAt;
    }
    
    @Override
    public String toString() {
        return String.format(
                "[E][%s] %s (at: %s)",
                isDone() ? "\u2713" : "\u2717",
                getTitle(),
                dateAt
        );
    }
}
