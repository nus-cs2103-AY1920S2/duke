package main.java;

public class Event extends Task {
    protected String at;

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }   


    public String getDate() {
        return this.at.split("/at ")[1];
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + at ;
    }
}
