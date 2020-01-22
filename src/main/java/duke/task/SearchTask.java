package duke.task;

public class SearchTask extends Task {
    protected int key;

    public SearchTask(int key, boolean isDone, String description, String type) {
        super(isDone, description, type);
    }

    public int getKey() {
        return key;
    }
}
