package duke.command;

/**
 * Abstract class for Commands in Duke application
 */
public abstract class Command {
    /**
     * Enum of valid commands
     */
    public enum CommandType {
        BYE, DEADLINE, DELETE, DONE, EVENT, LIST, TODO, CALENDAR, CLEAR, FIND, SNOOZE
    }
}
