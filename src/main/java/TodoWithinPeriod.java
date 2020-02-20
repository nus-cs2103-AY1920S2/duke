/**
 * Represents a todo task that must be done within a specified date period.
 * A <code>Todo</code> object corresponds to a todo task represented by task description.
 */

public class TodoWithinPeriod extends Todo {
    protected String betweenDate;
    protected String toDate;

    public TodoWithinPeriod(String description, String fromDate, String toDate) {
        super(description);
        this.betweenDate = Parser.parseTime(fromDate);
        this.toDate = Parser.parseTime(toDate);
    }

    public String getBetweenDate() {
        return betweenDate;
    }

    public String getToDate() {
        return toDate;
    }
}
