package main.java;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor
     * @param description
     */
    public Task(String description) {
        if(description.contains("["+"\u2713"+"]") || description.contains("["+"\u2718"+"]"))
         this.description = description;
        else 
        this.description = "["+"\u2718"+"]" + description;
        this.isDone = false;
    }

    /**
     * getter for description
     * @return String description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Marks this task as done
     */
    public String markAsDone() {
        this.isDone = true;
        this.description = this.description.replace("["+"\u2718"+"]", "["+"\u2713"+"]");
        Ui ui = new Ui(this.getDescription());
        return ui.doneTask(this);
    }

    public String toString() {
        return description;
    }
}