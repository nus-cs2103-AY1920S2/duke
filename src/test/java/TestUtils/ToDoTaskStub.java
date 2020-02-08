package TestUtils;

public class ToDoTaskStub {
    public static final String DEFAULT_DESCRIPTION = "complete ip increments";

    private String description;

    public ToDoTaskStub() {description = DEFAULT_DESCRIPTION; }

    public ToDoTaskStub setDescription(String description) {
        this.description = description;
        return this;
    }
}
