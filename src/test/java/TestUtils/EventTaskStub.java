package TestUtils;

public class EventTaskStub {
    private static final String DEFAULT_DESCRIPTION = "cs2103 lecture";
    private static final String DEFAULT_TIME = "week 3 tutorial";

    private String description;
    //set the at as a string as the LocalDateTime object is only tested in task object.
    private String at;

    public EventTaskStub() {
        description = DEFAULT_DESCRIPTION;
        at = DEFAULT_TIME;
    }

    public EventTaskStub setDescription(String description) {
        this.description = description;
        return this;
    }

    public EventTaskStub setTime(String at) {
        this.at = at;
        return this;
    }
}
