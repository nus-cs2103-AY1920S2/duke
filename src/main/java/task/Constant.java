package task;

public enum Constant {
    TODO("[T]"),
    EVENT("[E]", "/at"),
    DEADLINE("[D]", "/by"),
    PERIODTASK("[P]", "/start", "/end");

    public static String[] taskTypes = {"todo", "event", "deadline", "period"};
    private final String type;
    private final String[] timeDelimiter;

    Constant(String type) {
        this.type = type;
        this.timeDelimiter = null;
    }

    Constant(String type, String... timeDelimiter) {
        this.type = type;
        this.timeDelimiter = timeDelimiter;
    }

    public String getTimeDelimiter() {
        return this.timeDelimiter[0];
    }

    public String getEndTimeDelimiter() {
        return this.timeDelimiter[1];
    }

    public String getType() {
        return this.type;
    }
}
