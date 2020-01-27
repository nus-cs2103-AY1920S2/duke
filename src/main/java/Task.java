package main.java;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public String getStatusIcon() {
        return (isDone ? "["+"\u2713"+"]" : "["+"\u2718"+"]"); //return tick or X symbols
    }

    public void markAsDone() {
        this.isDone = true;
        Ui ui = new Ui(this.getDescription());
        ui.doneTask(this);
    }

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