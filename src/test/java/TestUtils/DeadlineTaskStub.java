package TestUtils;

public class DeadlineTaskStub {
    private static final String DEFAULT_DESCRIPTION = "form a project group";
    private static final String DEFAULT_DEADLINE = "week 3 tutorial";

    private String description;
    //set the at as a string as the LocalDateTime object is only tested in task object.
    private String by;

    public DeadlineTaskStub() {
        description = DEFAULT_DESCRIPTION;
        by = DEFAULT_DEADLINE;
    }

    public DeadlineTaskStub setDescription(String description) {
        this.description = description;
        return this;
    }

    public DeadlineTaskStub setTime(String by) {
        this.by = by;
        return this;
    }
}
