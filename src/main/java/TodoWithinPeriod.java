/**
 * Represents a todo task that must be done within a specified date period.
 * A <code>Todo</code> object corresponds to a todo task represented by task description.
 */

public class TodoWithinPeriod extends Todo {
    protected String betweenDate;
    protected String AndDate;

    public TodoWithinPeriod(String description, String fromDate, String AndDate) {
        super(description);
        this.betweenDate = Parser.parseTime(fromDate);
        this.AndDate = Parser.parseTime(AndDate);
    }

    public String getBetweenDate() {
        return betweenDate;
    }

    public String getAndDate() {
        return AndDate;
    }
}
