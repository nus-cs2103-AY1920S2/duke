package main.java;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getDate() {
        return this.by.split("/by ")[1];
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() +  by ;
    }
}

