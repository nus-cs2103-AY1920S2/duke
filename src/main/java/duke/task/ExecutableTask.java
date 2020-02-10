package duke.task;

public abstract  class ExecutableTask implements Task {

    public String getDoneString() {
        if (isDone()) {
            return "[\u2713]";
        } else {
            return "[\u2613]";
        }
    }

}
