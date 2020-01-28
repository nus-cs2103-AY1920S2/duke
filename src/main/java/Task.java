package main.java;

public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor
     * @param description
     */
    public Task(String description) {
        this.description = description;
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
     * getter for status icon
     * @return String status icon
     */
    public String getStatusIcon() {
        return (isDone ? "["+"\u2713"+"]" : "["+"\u2718"+"]"); //return tick or X symbols
    }

    /**
     * Marks this task as done
     */
    public void markAsDone() {
        this.isDone = true;
        Ui ui = new Ui(this.getDescription());
        ui.doneTask(this);
    }

    /**
     * deletes task
     */
    public void deleteTask() {
        System.out.println("-------------------------------------------------------------");
        System.out.println("Noted, I have deleted this task:");
        System.out.println(this);
    }

    
    public String toString() {
        return this.getStatusIcon() + " " + description;
    }

    //...
}