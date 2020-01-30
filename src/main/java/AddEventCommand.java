public class AddEventCommand extends AddCommand {
    public AddEventCommand(String description, String timeRange) {
        task = new Event(description, timeRange);
    }
}
