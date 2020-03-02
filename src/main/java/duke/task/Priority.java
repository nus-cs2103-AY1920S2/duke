package duke.task;

/**
 * Represents the Priority level of a task. There could exist tasks of differing
 * priority level, but a task can only have one priority level at any one time.
 */
public enum Priority {
    LOW,
    MEDIUM,
    HIGH;

    /**
     * Returns true or false depending on whether the user input s is a valid priority.
     * @param s user input priority s, as a string.
     * @return true or false.
     */
    public static boolean isValidPriority(String s) {
        return (s.equals("LOW") || s.equals("MEDIUM") || s.equals("HIGH"));
    }
}
