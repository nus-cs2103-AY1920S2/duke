/**
 * Task of type "Event"; Stores the venue.
 */
public class Event extends Task {
    String venue;

    /**
     * Constructor of an instance of Event, set the type as 'E'.
     *
     * @param x The content of the task.
     * @param v The venue of the event.
     */
    public Event(String x, String v) {
        super(x);
        this.type = "[E]";
        this.venue = v;
    }

    /**
     * Generate the format for the task to be printed.
     *
     * @return A string of the format.
     */
    @Override
    public String readyToPrint() {
        return super.readyToPrint() + this.venue;
    }

    /**
     * Generate the format for the task to be saved to the data file.
     *
     * @return A string of the format.
     */
    @Override
    public String readyToSave() {
        return super.readyToSave() + " |" + this.venue.substring(5,this.venue.length() - 1);
    }
}
