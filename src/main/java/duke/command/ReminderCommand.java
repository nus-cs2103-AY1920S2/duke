package duke.command;

import duke.exception.InvalidCommandException;
import duke.task.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a ReminderCommand.
 * Used to execute the reminder command.
 */
public class ReminderCommand implements Command {
    private static final String ALL_REMINDER_MESSAGE = "Here are the tasks expiring in the next 7 days:\n";
    private static final String DEADLINE_REMINDER_MESSAGE = "Here are the deadlines due in the next 7 days:\n";
    private static final String EVENT_REMINDER_MESSAGE = "Here are the events coming up in the next 7 days:\n";
    private static final String NO_EXPIRING_TASK_MESSAGE = "Hurray! there are no expiring task in the next 7 days!\n";
    private static final String NO_EXPIRING_DEADLINE_MESSAGE = "Hurray! there are no deadlines in the next 7 days!\n";
    private static final String NO_EXPIRING_EVENTS_MESSAGE = "There are no events in the next 7 days!\n";
    private static final String INVALID_ARGUMENT_ERROR_MESSAGE = "HEY!!! I only can give reminders for"
            + " 'all', 'deadline' or 'event'\n";
    private static final int DAYS_FROM_TODAY = 7;

    /** String argument for the command. */
    private String arg = "";

    /**
     * Constructs a new ReminderCommand.
     *
     * @param arg argument for the reminder command.
     */
    public ReminderCommand(String arg) {
        this.arg = arg;
    }

    /**
     * Executes the reminder command.
     *
     * @param tasks TaskList object that contains the tasks of the application.
     * @param ui Ui object for the command to interact with the user.
     * @param storage storage object for the retrieval/saving of tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            if (arg.equals("all")) {
                String relevantTasks = tasks.findAllExpiringTasks(DAYS_FROM_TODAY);
                if (relevantTasks.equals("")) {
                    ui.addMessage(NO_EXPIRING_TASK_MESSAGE);
                } else {
                    ui.addMessage(ALL_REMINDER_MESSAGE + relevantTasks);
                }
            } else if (arg.equals("deadline")) {
                String relevantDeadlines = tasks.findExpiringDeadlines(DAYS_FROM_TODAY);
                if (relevantDeadlines.equals("")) {
                    ui.addMessage(NO_EXPIRING_DEADLINE_MESSAGE);
                } else {
                    ui.addMessage(DEADLINE_REMINDER_MESSAGE + relevantDeadlines);
                }
            } else if (arg.equals("event")) {
                String relevantEvents = tasks.findExpiringEvents(DAYS_FROM_TODAY);
                if (relevantEvents.equals("")) {
                    ui.addMessage(NO_EXPIRING_EVENTS_MESSAGE);
                } else {
                    ui.addMessage(EVENT_REMINDER_MESSAGE + relevantEvents);
                }
            } else {
                throw new InvalidCommandException(INVALID_ARGUMENT_ERROR_MESSAGE);
            }
        } catch (InvalidCommandException e) {
            ui.addMessage(e.getMessage());
        }
    }
}
