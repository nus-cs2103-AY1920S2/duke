package task;

public enum Constant {
    TODO("[T]"),
    EVENT("[E]", "/at"),
    DEADLINE("[D]", "/by"),
    PERIODTASK("[P]", "/start", "/end");

    public static String[] taskTypes = {"todo", "event", "deadline", "period"};
    public static String[] taskDelimiters = {"/at", "/by", "/start"};
    public static int isDoneIndex = 1;
    public static int descriptionIndex = 2;
    public static int dateIndex = 3;
    public static int timeIndex = 4;
    public static int endDateIndex = 5;
    public static int endTimeIndex = 6;

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
