package duke.task;

public abstract  class ExecutableTask implements Task {

    public String getDoneString() {
        if (isDone()) {
            return "[✓]";
        } else {
            return "[✗]";
        }
    }

}
