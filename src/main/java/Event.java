import java.time.LocalDate;

/**
 * <h1>Event class</h1>
 * Class to create event task for duke
 */
public class Event extends task {
    private String date;
    private String from;
    private LocalDate ld;

    /**
     * Class Constructor
     * @param name
     * @param date
     */
    public Event (String name, String date) {
        super(name);
        String[] temp = date.split(" ");
        this.date = date;
        this.ld = LocalDate.parse(temp[0]);
        this.from = temp[1];
    }

    /**
     * Method to generate string to save to text file
     * @return String
     */
    @Override
    public String toSave() {
        if (this.isDone()) {
            return "E /n 1 /n " + this.getName() + " /n " + this.date;
        } else {
            return "E /n 0 /n " + this.getName() + " /n " + this.date;
        }
    }

    /**
     * Method handles Snooze for Event class
     * @param newDate
     * @return String
     */
    @Override
    public void snooze(String newDate) {
        String[] temp = newDate.split("/");
        this.ld = LocalDate.parse(temp[0]);
        this.date = temp[0] + " " + temp[1];
        this.from = temp[1];
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
        return "[E]" + super.toString() + " (at: " + ld.getDayOfMonth() + " " + ld.getMonth() + " " + ld.getYear() + " " + from + ")";
    }
}
