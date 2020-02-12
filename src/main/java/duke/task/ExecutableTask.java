package duke.task;

public abstract  class ExecutableTask implements Task {

    /**
     * Gets string to display tick or cross.
     * @return tick or cross string
     */
    public String getDoneString() {
        if (isDone()) {
            return "[\u2713]";
        } else {
            return "[\u2613]";
        }
    }
}
