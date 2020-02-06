package duke.task;

/**
 * Used as a model which the key property used to identify the index linked
 * to the existing list of tasks.
 * For instance, the subset of tasks found may not contain all the tasks
 * so an index is required to retrieve the correct task from the list
 */
public class SearchTask extends Task {
    protected int key;

    public SearchTask(int key, boolean isDone, String description, String type) {
        super(isDone, description, type);
        this.key = key;
    }

    public int getKey() {
        return key;
    }
}
