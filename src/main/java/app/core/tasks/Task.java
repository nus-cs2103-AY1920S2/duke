package app.core.tasks;

import app.util.Date;
import app.exceptions.InvalidDateTimeFormatException;

/**
 * This class contains the information of a Task.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    Task(String description) {
        assert description != null : "Description should not be null";
        this.description = description;
        this.isDone = false;
    }
    
    String getStatusIcon() {
        return (isDone ? "✓" : "✘");
    }

    public String getDescription() {
        return this.description;
    }
    
    void setDone() {
        this.isDone = true;
    }
    
    /**
     * Creates a Task based on the format stored in storage.
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
                output = new TodoTask(parsedArgs[1]);
            }

            if (Boolean.parseBoolean(parsedArgs[2])) {
                output.setDone();
            }
            
            return output;
        } catch (InvalidDateTimeFormatException e) {
            return null;
        }
    }
    
    public abstract String toStorage();

    public abstract String toString();

    public abstract boolean equals(Object other);
}