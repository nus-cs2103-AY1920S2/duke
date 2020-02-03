package app.core.tasks;

import app.util.Date;
import app.exceptions.WrongDateTimeFormatException;

/**
 * This class contains the information of a Task.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    
    String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }
    
    void setDone() {
        this.isDone = true;
    }
    
    /**
     * Creates a Task based on the format stored in storage
     * @param data Storage data
     * @return A new Task object
     */
    public static Task fromStorage(String data) {
        try {
            String[] parsedArgs = data.split(",");
            String type = parsedArgs[0];

            Task output;
            if (type.equals("D")) {
                Date date = Date.fromStorage(parsedArgs[3]);
                output = new DeadlineTask(parsedArgs[1], date);
            } else if (type.equals("E")) {
                Date date = Date.fromStorage(parsedArgs[3]);
                output = new EventTask(parsedArgs[1], date);
            } else {
                output = new Task(parsedArgs[1]);
            }

            if (Boolean.parseBoolean(parsedArgs[2])) {
                output.setDone();
            }
            
            return output;
        } catch (WrongDateTimeFormatException e) {
            return null;
        }
    }
    
    /**
     * Returns a String in the format used to save the task in storage
     * @return a String in the storage format
     */
    public String toStorage() {
        return String.format("T,%s,%b", this.description, this.isDone);
    }

    /**
     * Returns a String representation of the Task
     * @return a String representation of the Task
     */
    @Override
    public String toString() {
        return String.format("[T][%s] %s", this.getStatusIcon(), this.description);
    }
}