package duke.tasks;

import duke.DukeException;

public class Event extends Task {

    protected String at;

    /**
     * Constructor for the Deadline object, a subclass of Task.
     *
     * @param description String containing the description of the task
     * @param at String setting the deadline for this task
     */
    public Event(String description, String at) {
        super(description);
        this.at = at;
    }


    /**
     * Formats this object as a String to be written into the data file.
     *
     * @return String in the format E:;:isDone:;:description:;:at
     */
    @Override
    public String toDataString() {
        return "E:;:" + super.toDataString() + ":;:" + this.at;
    }


    /**
     * Formats this object as a String to be printed out.
     *
     * @return String
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at + ")";
    }


    /**
     * Updates this task object attributes without creating a new object.
     *
     * @param updateStrArr String[] containing data for the update
     * @return the reference of this object
     */
    @Override
    public Task update(String[] updateStrArr) throws DukeException {
        super.update(updateStrArr);
        for (String updateStr : updateStrArr) {
            String[] attrToChange = updateStr.split("=");
            String attr = attrToChange[0].strip();
            String newValue = attrToChange[1].strip();
            if (attr.equalsIgnoreCase("at")) {
                this.at = newValue;
            }
        }
        return this;
    }
}