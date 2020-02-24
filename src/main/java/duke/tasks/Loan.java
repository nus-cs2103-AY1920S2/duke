package duke.tasks;

/**
 * Loan class for Tasks that represent loaning of some value.
 */
public class Loan extends Task {

    /**
     * Value loaned to an individual.
     */
    private final int value;

    /**
     * Constructor for Loan Task.
     *
     * @param borrower person borrowing.
     * @param value value borrowed.
     */
    public Loan(String borrower, int value) {
        super(borrower);
        this.value = value;
    }

    /**
     * Gives a message indicating the Loan Task has been added.
     *
     * @return String indicating the Loan Task has been added.
     */
    @Override
    public String taskAddedMessage() {
        return "==> Added a Loan to " + this.getBorrower() + " with value of "
                + this.getLoanValue();
    }

    /**
     * Provides a String representation of the Loan Task object with tag [L] and its value.
     *
     * @return String representation of the Loan Task with borrower and value.
     */
    @Override
    public String toString() {
        return "[L] " + this.getBorrower() + " (value: " + this.getLoanValue() + ")";
    }

    /**
     * Gets a String representation of the borrower.
     *
     * @return String representation of the borrower.
     */
    public String getBorrower() {
        return getCommand();
    }

    /**
     * Gets the value of the Loan.
     *
     * @return value of the Loan.
     */
    public int getLoanValue() {
        return value;
    }

}
