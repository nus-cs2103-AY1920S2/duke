package task;

public enum Constant {
    TODO("[T]"),
    EVENT("[E]", "/at"),
    DEADLINE("[D]", "/by");

    private final String type;
    private final String timeDelimiter;

    Constant(String type) {
        this.type = type;
        this.timeDelimiter = "";
    }

    Constant(String type, String timeDelimiter) {
        this.type = type;
        this.timeDelimiter = timeDelimiter;
    }

    public String getTimeDelimiter() {
        return this.timeDelimiter;
    }

    public String getType() {
        return this.type;
    }
}
