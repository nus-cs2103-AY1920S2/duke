class Event extends Task {
    private String place;

    public Event(String task) {
        // Pass the description of the task as the argument to the parent constructor
        super(task.split("/at")[0]);
        String[] arr = task.split("/at");
        this.place = arr[1].trim();
    }

    public String getPlace() {
        return this.place;
    }

    @Override
    public String toString() {
        String description = super.getDescription();
        String status = super.getStatusIcon();
        String place = this.getPlace();

        return "[E]" + "[" + status + "] " + description + "at: " + place;
    }
}
