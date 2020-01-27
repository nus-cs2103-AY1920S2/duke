class Todo extends Task {

    static final String TYPE_SYMBOL = "T";

    Todo(String taskDescription) {
        super(taskDescription);
    }

    @Override
    public String toStringForSaving() {
        return TYPE_SYMBOL + Task.SEPERATOR
                + (isDone ? TRUE_SYMBOL : FALSE_SYMBOL) + Task.SEPERATOR
                + taskDescription;
    }

    @Override
    public String toString() {
        return String.format("[%s]%s", TYPE_SYMBOL, super.toString());
    }
}
