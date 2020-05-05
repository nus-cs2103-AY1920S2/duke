import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Adds an event to the taskList
 */
public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    String taskName;
    LocalDate date;

    /**
     * Creates an EventCommand
     * @param argumentText supplied by the user
     * @throws DukeException if either part of the command is empty
     */
    EventCommand(String argumentText) throws DukeException, DateTimeParseException {
        String[] eventParts = argumentText.split(" /at ");
        if (eventParts[0].equals("") || eventParts[1].equals("")) {
            throw new DukeException("The description of an event cannot be empty");
        } else {
            this.taskName = eventParts[0];
            this.date = LocalDate.parse(eventParts[1]);
        }
    }

    /**
     * Creates an event and adds it to the taskList
     * @return CommandResult with the success message
     */
    @Override
    public CommandResult execute() {
        Task newEvent = new Event(taskName, false, date);
        taskList.addToList(newEvent);
        return new CommandResult(getAddSuccessMessage(newEvent));
    }
}
