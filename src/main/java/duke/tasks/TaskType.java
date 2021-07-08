package duke.tasks;

/**
 * An enum class to represent the available types of tasks that can be created.
 */
public enum TaskType {
    TODO("T"),
    EVENT("E"),
    DEADLINE("D");

    private String shortForm;

    TaskType(String shortForm) {
        this.shortForm = shortForm;
    }

    public String toString() {
        return this.shortForm;
    }

}