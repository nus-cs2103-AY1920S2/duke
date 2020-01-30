class Deadline extends Task {
    private String deadline;

    public Deadline(String task) {
        // Split the input task string by the delimiter \by
        // first element in arr will be the description of the task, second elem will be the deadline, both are String
        // Pass the description as the argument to the constructor
        super(task.split("/by")[0]);
        String[] arr = task.split("/by");
        this.deadline = arr[1];
    }

    public String getDeadline(){
        return this.deadline;
    }

    @Override
    public String toString() {
        String description = super.getDescription();
        String status = super.getStatusIcon();
        String deadline = this.getDeadline();

        return "[D]" + "[" + status + "] " + description + " - " + deadline;

    }
}
