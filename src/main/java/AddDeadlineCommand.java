public class AddDeadlineCommand extends AddCommand {
    public AddDeadlineCommand(String description, String time) {
        task = new Deadline(description, time);
    }
}
