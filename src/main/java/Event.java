class Event extends Task {
    private String date;

    public Event(String task) {
        // Pass the description of the task as the argument to the parent constructor
        super(task.split("/at")[0]);
        String[] arr = task.split("/at");
        // Get the date from the arr and set the date
        this.date = arr[1];
    }

    public String getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        String description = super.getDescription();
        String status = super.getStatusIcon();
        String date = this.getDate();

        return "[E]" + "[" + status + "] " + description + " (at:" + date + ")";
    }
}
