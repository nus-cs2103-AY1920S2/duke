class Event extends Task {

    private String startAndEndTime;

    Event(String taskDescription, String startAndEndTime) {
        super(taskDescription);
        this.startAndEndTime = startAndEndTime;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), startAndEndTime);
    }
}
