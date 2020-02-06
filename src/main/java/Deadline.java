/**
 * Class Deadline to represent a Deadline Task. A Deadline
 * consists of a description and a endTime.
 */
public class Deadline extends Task {

    /**
     * Creates a new Deadline with {@code description} and specified {@code endTime}.
     *
     * @param description the description/details of our task
     * @param endTime end time
     */
    public Deadline(String description, String endTime) {
        super(description, endTime);
    }

    /**
     * Retrieves our class type to differentiate Deadlines among the generic Task instances.
     *
     * @return "D" the initial for our Deadline Class
     */
    @Override
    public String getTypeName() {
        return "D";
    }

    /**
     * returns a String representation of a Deadline instance.
     *
     * @return String String representation
     */
    @Override
    public String toString() {
        return "[D]" + super.toString();
    }

    /**
     * returns a String representation of a Deadline instance in our database.
     *
     * @return String a Deadline representation in our database
     */
    @Override
    public String toStringFile() {
        return "D" + " | " + super.toStringFile();
    }
}
