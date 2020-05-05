import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Adds a deadline to the taskList
 */
public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    String taskName;
    LocalDate date;

    /**
     * creates a DeadlineCommand
     * @param argumentText the arguments supplied by the user
     * @throws DukeException if either part of the command is empty
     */
    DeadlineCommand(String argumentText) throws DukeException, DateTimeParseException {
        String[] deadlineParts = argumentText.split(" /by ");
        if (deadlineParts[0].equals("") || deadlineParts[1].equals("")) {
            throw new DukeException("The description of a deadline must not be empty");
        } else {
            this.taskName = deadlineParts[0];
            this.date = LocalDate.parse(deadlineParts[1]);
        }
    }

    /**
     * Creates a new deadline and adds it to the taskList
     * @return CommandResult with the success message
     */
    @Override
    public CommandResult execute() {
        Task newDeadline = new Deadline(taskName, false, date);
        taskList.addToList(newDeadline);
        return new CommandResult(getAddSuccessMessage(newDeadline));
    }
}
