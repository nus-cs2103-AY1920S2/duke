public class Deadline extends Task {

    private String time;

    /**
     *  This method updates the action and time accordingly for Deadline.
     * @param description This is the action to be taken.
     * @param time This is the time for which that action has to happen.
     */
    public Deadline(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    /*Override the toString method for Deadline*/
    public String toString() {
        return "[D]" + super.toString() + "| by: " + time ;
    }

    /**
     * This method indicates that a new Deadline Task has been added to the ArrayList in Store.
     */
    public String Output() {
        return "Got it. I've added this task: \n" + " [D]" + super.toString() + "| by: " + time;
    }

    /**
     * This method return the time of this Deadline Task.
     * @return String This is the time for the deadline.
     */
    public String getTime() {
        return time;
    }
}