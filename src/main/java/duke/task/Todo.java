package duke.task;

import duke.DukeException;

/**
 * The type To-do.
 */
public class Todo extends Task {

    /**
     * Instantiates a new To-do.
     *
     * @param description the description
     */
    public Todo(String description) {
        super(description);
    }


    /**
     * From the input given by the user, filter out the commands (To-do)
     * And returns the description of the string.
     * @param s the s
     * @return String without "to-do"
     * @throws DukeException when the user enters an empty description or use the wrong to-do format.
     *
     */

    @Override
    public String formatTasks(String s) throws DukeException {
        // Needed the space after "to do" due to space formatting.

        String[] splitedString = getDescription().split("todo ");

        try {
            if (splitedString[1].length() < 1) {
                // Checks if the to-do is empty a not
            }
            return super.formatTasks(s);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("You cannot leave the description empty");
        } catch (Exception e) {
            throw new DukeException("Please enter the correct format for the TODO");
        }

    }

    /**
     * Gets description of the task. WWithout the date and time
     * return the deadline task description.
     */

    @Override
    public void setDescription(String s) throws DukeException {
        super.setDescription(s);
    }


    @Override
    public String toString() {
        return " [" + TaskCode.T + "]" + super.toString();
    }


}
