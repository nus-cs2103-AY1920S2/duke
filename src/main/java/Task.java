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
        System.out.println("-------------------------------------------------------------");
        System.out.println("Nice I have marked this task as done");
        System.out.println(this.getStatusIcon() + " " + this.getDescription());
        System.out.println();
        System.out.println("-------------------------------------------------------------");
    }

    
    public String toString() {
        return this.getStatusIcon() + " " + description;
    }

    //...
}