package duke.tasks;

import duke.DukeException;

public class Task {

    protected String description;
    protected boolean isDone;

    /**
     * Constructor for the Task class
     * @param description String containing the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    /**
     * Gets the status icons, depending on whether the task has been completed:
     * "Y" is returned if the task is completed
     * "N" is returned if the task is incomplete
     * @return String for the status icons
     */
    public String getStatusIcon() {
        return (isDone ? "Y" : "N"); // as ticks and X don't parse well on powershell
        //return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }


    /**
     * Sets the status of this task to done (Default: not done)
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Formats this object as a String to be written into the data file
     * @return String in the format isDone:;:description
     */
    public String toDataString() {
        return (isDone ? "1" : "0") + ":;:" + this.description;
    }

    public Task update(String[] updateStrArr) throws DukeException {
        for (String updateStr : updateStrArr) {
            String[] attrToChange = updateStr.split("=");
            String attr = attrToChange[0].strip();
            String newValue = attrToChange[1].strip();
            if (attr.equalsIgnoreCase("description")) {
                this.description = newValue;
            } else if (attr.equalsIgnoreCase("done")) {
                if (newValue.equalsIgnoreCase("true")) {
                    this.isDone = true;
                } else if (newValue.equalsIgnoreCase("false")) {
                    this.isDone = false;
                }
            }
        }
        return this;
    }

    /**
     * Formats this object as a String to be printed out
     * @return String
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }
}