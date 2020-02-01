/** Represents a Task which will happen at a certain date and time. */
public class Event extends Task {

    protected TaskDate tdStart;
    protected TaskDate tdEnd;

    /**
     * Constructor for Event object.
     *
     * @param desc Task description.
     * @param tdStart TaskDate event start date and time.
     * @param tdEnd TaskDate event end date and time.
     */
    public Event(String desc, TaskDate tdStart, TaskDate tdEnd) {
        super(desc);
        this.tdStart = tdStart;
        this.tdEnd = tdEnd;
    }

    @Override
    public String toString() {
        String temp = tdEnd.toString();
        if (tdStart.getDate().equals(tdEnd.getDate())) {
            temp = tdEnd.getTime();
        }
        return "[E]" + super.toString() + " (at: " + tdStart + " to " + temp + ")";
    }
}