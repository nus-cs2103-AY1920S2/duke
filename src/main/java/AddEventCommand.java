/**
 * Represents a command to add an event to the task list,
 * to be executed later.
 */
public class AddEventCommand extends AddCommand {

    /**
     * Creates an AddEventCommand based on the description of the event,
     * and the start and end times.
     *
     * @param description the description of the event
     * @param timeRange the string representing the start and end time
     */
    public AddEventCommand(String description, String timeRange) {
        task = new Event(description, timeRange);
    }
}
