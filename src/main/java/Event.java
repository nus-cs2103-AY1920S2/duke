class Event extends Task {

    static final String TYPE_SYMBOL = "E";
    private String startAndEndTime;

    Event(String taskDescription, String startAndEndTime) {
        super(taskDescription);
        this.startAndEndTime = startAndEndTime;
    }

    @Override
    public String toStringForSaving() {
        return TYPE_SYMBOL + Task.SEPERATOR
                + (isDone ? TRUE_SYMBOL : FALSE_SYMBOL) + Task.SEPERATOR
                + taskDescription + Task.SEPERATOR
                + startAndEndTime;
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (at: %s)", TYPE_SYMBOL, super.toString(), startAndEndTime);
    }
}
