class Deadline extends Task {

    static final String TYPE_SYMBOL = "D";
    private String deadline;

    Deadline(String taskDescription, String deadline) {
        super(taskDescription);
        this.deadline = deadline;
    }

    @Override
    public String toStringForSaving() {
        return TYPE_SYMBOL + Task.SEPERATOR
                + (isDone ? TRUE_SYMBOL : FALSE_SYMBOL) + Task.SEPERATOR
                + taskDescription + Task.SEPERATOR
                + deadline;
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", TYPE_SYMBOL, super.toString(), deadline);
    }
}
