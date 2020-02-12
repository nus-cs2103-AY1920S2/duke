package duke.task;

public enum Priority {
    LOW,
    MEDIUM,
    HIGH;

    public static boolean isValidPriority(String s) {
        return (s.equals("LOW") || s.equals("MEDIUM") || s.equals("HIGH"));
    }
}
