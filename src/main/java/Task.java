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
     * getter for status icon
     * @return String status icon
     */
    //public String getStatusIcon() {
      //  return (isDone ? "["+"\u2713"+"]" : "["+"\u2718"+"]"); //return tick or X symbols
    //}

    /**
     * Marks this task as done
     */
    public String markAsDone() {
        this.isDone = true;
        this.description = this.description.replace("["+"\u2718"+"]", "["+"\u2713"+"]");
        Ui ui = new Ui(this.getDescription());
        return ui.doneTask(this);
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
        return description;
    }

    //...
}