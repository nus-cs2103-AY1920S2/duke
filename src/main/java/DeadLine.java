import java.time.LocalDate;

/**
 * <h1>Deadline class</h1>
 * Class to create deadline task for duke
 */
public class DeadLine extends task {
    private String date;
    private LocalDate ld;

    /**
     * Class Constructor
     * @param name
     * @param end
     */
    public DeadLine (String name, String end) {
        super(name);
        this.date = end;
        this.ld = LocalDate.parse(end);
    }

    /**
     * Method to generate string to save to text file
     * @return String
     */
    @Override
    public String toSave() {
        if (this.isDone()) {
            return "D /n 1 /n " + this.getName() + " /n " + this.date;
        } else {
            return "D /n 0 /n " + this.getName() + " /n " + this.date;
        }
    }

    /**
     * Handles Snooze function for deadlines
     * @param newDate
     * @return String
     */
    @Override
    public void snooze(String newDate) {
        this.ld = LocalDate.parse(newDate);
        this.date = newDate;
    }

    /**
     * Return false cause this is toDo;
     * @return
     */
    @Override
    public boolean isToDo() {
        return false;
    }

    /**
     * Generic to print function
     * @return String
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + ld.getDayOfMonth() + " " + ld.getMonth() + " " + ld.getYear() + ")";
    }
}
